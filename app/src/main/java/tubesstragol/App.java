package tubesstragol;

import java.util.*;
import tubesstragol.model.*;
import tubesstragol.service.*;
import tubesstragol.controller.*;
import tubesstragol.view.*;

public class App {
    public static void main(String[] args) {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument("Guitar", 2));
        instruments.add(new Instrument("Keyboard", 1));
        instruments.add(new Instrument("Drum", 1));
        instruments.add(new Instrument("Bass", 1));
        instruments.add(new Instrument("Singer", 2));

        int weeks = 3;
        List<Musician> musicians = new ArrayList<>();
        musicians.add(new Musician("Cyrene", Set.of(0, 4), new boolean[]{true, true, true}));
        musicians.add(new Musician("Castorice", Set.of(0, 3), new boolean[]{true, false, true}));
        musicians.add(new Musician("Anaxa", Set.of(1), new boolean[]{true, true, true}));
        musicians.add(new Musician("Phainon", Set.of(2, 4), new boolean[]{true, false, true}));
        musicians.add(new Musician("Mydei", Set.of(3), new boolean[]{true, true, true}));

        SchedulerService scheduler = new SchedulerService(musicians, instruments, weeks);
        SchedulerView view = new SchedulerView();
        SchedulerController controller = new SchedulerController(scheduler, view);

        controller.run(musicians, instruments);
    }
}
