import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.ArrayList;

public class NavigationApp extends Application {
    private Vehicle vehicle;
    private ArrayList<Location> locations = new ArrayList<>();
    private ListView<String> locationListView;
    private Label fuelLabel, locationLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        vehicle = new Vehicle(100);
        locations.add(new Location("Home", true));
        locations.add(new Location("Gas Station", true));
        locations.add(new Location("School", false));

        fuelLabel = new Label("Fuel: " + vehicle.getFuel());
        locationLabel = new Label("Current Location: " + vehicle.getCurrentLocation());

        locationListView = new ListView<>();
        updateLocationList();

        Button driveButton = new Button("Drive");
        Button refuelButton = new Button("Refuel");
        Button addLocationButton = new Button("Add Location");

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(locationLabel, fuelLabel, locationListView, driveButton, refuelButton, addLocationButton);

        driveButton.setOnAction(e -> drive());
        refuelButton.setOnAction(e -> refuel());
        addLocationButton.setOnAction(e -> showAddLocation(primaryStage));

        Scene mainScene = new Scene(mainLayout, 300, 400);
        mainScene.getStylesheets().add("style.css");

        primaryStage.setTitle("Navigation Program");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void drive() {
        String selected = locationListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Pick a location first.");
            return;
        }

        for (Location loc : locations) {
            if (loc.getName().equals(selected)) {
                if (vehicle.getFuel() >= 10) {
                    vehicle.setCurrentLocation(loc.getName());
                    vehicle.setFuel(vehicle.getFuel() - 10);
                    updateLabels();
                } else {
                    showAlert("You're out of fuel!");
                }
                break;
            }
        }
    }

    private void refuel() {
        String current = vehicle.getCurrentLocation();
        for (Location loc : locations) {
            if (loc.getName().equals(current) && loc.hasFuel()) {
                vehicle.setFuel(100);
                updateLabels();
                showAlert("All fueled up!");
                return;
            }
        }
        showAlert("You can't refuel here.");
    }

    private void showAddLocation(Stage primaryStage) {
        Stage addStage = new Stage();
        TextField nameField = new TextField();
        CheckBox hasFuel = new CheckBox("Has Fuel?");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                showAlert("Name can't be empty.");
            } else {
                locations.add(new Location(name, hasFuel.isSelected()));
                updateLocationList();
                addStage.close();
            }
        });

        VBox layout = new VBox(10, new Label("Location Name:"), nameField, hasFuel, addButton);
        layout.setPadding(new Insets(20));

        addStage.setScene(new Scene(layout, 250, 200));
        addStage.show();
    }

    private void updateLabels() {
        fuelLabel.setText("Fuel: " + vehicle.getFuel());
        locationLabel.setText("Current Location: " + vehicle.getCurrentLocation());
    }

    private void updateLocationList() {
        locationListView.getItems().clear();
        for (Location loc : locations) {
            locationListView.getItems().add(loc.getName());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}

class Vehicle {
    private int fuel;
    private String currentLocation;

    public Vehicle(int fuel) {
        this.fuel = fuel;
        this.currentLocation = "Home";
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}

class Location {
    private String name;
    private boolean fuel;

    public Location(String name, boolean fuel) {
        this.name = name;
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public boolean hasFuel() {
        return fuel;
    }
}