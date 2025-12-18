package tubesstragol.view;
import java.util.*;
import tubesstragol.model.*;


public class SchedulerView {
    public void displaySolution(int index, Schedule s, List<Musician> musicians, List<Instrument> instruments, double fairness) {
        System.out.println("=== Solution #" + index + " ===");
        System.out.println(s.toString(musicians, instruments));
        System.out.printf("Fairness: %.2f/100%n", fairness);
        System.out.println("------------------------------");
    }

    public void displayDone() {
        System.out.println("All solutions generated (or demo limit reached).");
    }
}