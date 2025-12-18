package tubesstragol.model;
import java.util.*;

public class Musician {
    private String name;
    private Set<Integer> instruments;
    private boolean[] available;

    public Musician(String name, Set<Integer> instruments, boolean[] available) {
        this.name = name;
        this.instruments = new HashSet<>(instruments);
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getInstruments() {
        return instruments;
    }

    public boolean[] getAvailable() {
        return available;
    }
}
