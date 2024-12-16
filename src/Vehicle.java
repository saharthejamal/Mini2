public class Vehicle {
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

    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }
}