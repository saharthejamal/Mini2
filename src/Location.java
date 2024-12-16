public class Location {
    private String name;
    private boolean hasFuel;

    public Location(String name, boolean hasFuel) {
        this.name = name;
        this.hasFuel = hasFuel;
    }

    public String getName() {
        return name;
    }

    public boolean hasFuel() {
        return hasFuel;
    }
}