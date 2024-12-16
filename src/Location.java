public class Location {
    private String name;
    private boolean fuel;

    public Location(String name, boolean fuel) {
        this.name = name;
        this.fuel = fuel;
    }

    public String getName() { return name; }
    public boolean hasFuel() { return fuel; }
}