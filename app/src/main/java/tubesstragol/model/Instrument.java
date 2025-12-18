package tubesstragol.model;

public class Instrument {
    private String name;
    private int capacity;

    public Instrument(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return name + " (" + capacity + ")";
    }
}