package tubesstragol;

import java.util.*;
import tubesstragol.model.*;
import tubesstragol.service.*;
import tubesstragol.controller.*;
import tubesstragol.view.*;

public class App {
    public static void main(String[] args) {
        // 1. Define Instruments for Test Case 1
        // "Setiap minggu 1 alat musik 1 saja" implies Capacity = 1 for all.
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Instrument("Gitar", 1));    // Index 0
        instruments.add(new Instrument("Bass", 1));     // Index 1
        instruments.add(new Instrument("Keyboard", 1)); // Index 2
        instruments.add(new Instrument("Drum", 1));     // Index 3

        // 2. Define Duration
        int weeks = 4; // Source 3 shows "Minggu 1" to "Minggu 4"

        // 3. Define Musicians (A-I)
        // Assuming everyone is available (true) for all 4 weeks based on the grid structure.
        List<Musician> musicians = new ArrayList<>();

        // Musician A: Guitar(0), Bass(1)
        musicians.add(new Musician("A", Set.of(0, 1), new boolean[]{true, true, true, true}));

        // Musician B: Guitar(0), Bass(1)
        musicians.add(new Musician("B", Set.of(0, 1), new boolean[]{true, true, true, true}));

        // Musician C: Keyboard(2)
        musicians.add(new Musician("C", Set.of(2), new boolean[]{true, true, true, true}));

        // Musician D: Keyboard(2)
        musicians.add(new Musician("D", Set.of(2), new boolean[]{true, true, true, true}));

        // Musician E: Drum(3)
        musicians.add(new Musician("E", Set.of(3), new boolean[]{true, true, true, true}));

        // Musician F: Drum(3)
        musicians.add(new Musician("F", Set.of(3), new boolean[]{true, true, true, true}));

        // Musician G: No instruments checked in source table
        musicians.add(new Musician("G", Set.of(), new boolean[]{true, true, true, true}));

        // Musician H: Guitar(0)
        musicians.add(new Musician("H", Set.of(0), new boolean[]{true, true, true, true}));

        // Musician I: Guitar(0)
        musicians.add(new Musician("I", Set.of(0), new boolean[]{true, true, true, true}));

        // 4. Initialize and Run Controller
        SchedulerService scheduler = new SchedulerService(musicians, instruments, weeks);
        SchedulerView view = new SchedulerView();
        SchedulerController controller = new SchedulerController(scheduler, view);

        controller.run(musicians, instruments);




        // // --- TEST CASE 2 CONFIGURATION ---

        // // 1. Define Instruments (Capacity = 1 for all, per Source 6)
        // List<Instrument> instruments = new ArrayList<>();
        // instruments.add(new Instrument("Gitar", 1));    // Index 0
        // instruments.add(new Instrument("Bass", 1));     // Index 1
        // instruments.add(new Instrument("Keyboard", 1)); // Index 2
        // instruments.add(new Instrument("Drum", 1));     // Index 3

        // // 2. Define Duration
        // int weeks = 2; // Source 5 shows Minggu 1 - Minggu 2

        // // 3. Define Musicians (A-H) based on Source 4
        // List<Musician> musicians = new ArrayList<>();

        // // A, B: No instruments checked in table
        // musicians.add(new Musician("A", Set.of(), new boolean[]{true, true}));
        // musicians.add(new Musician("B", Set.of(), new boolean[]{true, true}));

        // // C: Keyboard (2)
        // musicians.add(new Musician("C", Set.of(2), new boolean[]{true, true}));

        // // D: Drum (3)
        // musicians.add(new Musician("D", Set.of(3), new boolean[]{true, true}));

        // // E: No instruments checked
        // musicians.add(new Musician("E", Set.of(), new boolean[]{true, true}));

        // // F: Gitar (0)
        // musicians.add(new Musician("F", Set.of(0), new boolean[]{true, true}));

        // // G: Keyboard (2)
        // musicians.add(new Musician("G", Set.of(2), new boolean[]{true, true}));

        // // H: Drum (3)
        // musicians.add(new Musician("H", Set.of(3), new boolean[]{true, true}));

        // // 4. Run Controller
        // SchedulerService scheduler = new SchedulerService(musicians, instruments, weeks);
        // SchedulerView view = new SchedulerView();
        // SchedulerController controller = new SchedulerController(scheduler, view);

        // controller.run(musicians, instruments);




        // --- TEST CASE 3 CONFIGURATION ---

        // // 1. Define Instruments 
        // // Capacities set to MAX values from Source 9 table to ensure solvability
        // List<Instrument> instruments = new ArrayList<>();
        // instruments.add(new Instrument("Gitar", 2));    // Index 0 (Max demand is 2)
        // instruments.add(new Instrument("Bass", 1));     // Index 1
        // instruments.add(new Instrument("Keyboard", 2)); // Index 2 (Max demand is 2)
        // instruments.add(new Instrument("Drum", 1));     // Index 3

        // // 2. Define Duration
        // int weeks = 2; 

        // // 3. Define Musicians (A-I) based on Source 7
        // List<Musician> musicians = new ArrayList<>();

        // // A: Gitar(0), Bass(1)
        // musicians.add(new Musician("A", Set.of(0, 1), new boolean[]{true, true}));

        // // B: Gitar(0), Bass(1)
        // musicians.add(new Musician("B", Set.of(0, 1), new boolean[]{true, true}));

        // // C: Keyboard(2)
        // musicians.add(new Musician("C", Set.of(2), new boolean[]{true, true}));

        // // D: Keyboard(2)
        // musicians.add(new Musician("D", Set.of(2), new boolean[]{true, true}));

        // // E: Drum(3)
        // musicians.add(new Musician("E", Set.of(3), new boolean[]{true, true}));

        // // F: Drum(3)
        // musicians.add(new Musician("F", Set.of(3), new boolean[]{true, true}));

        // // G: No instruments
        // musicians.add(new Musician("G", Set.of(), new boolean[]{true, true}));

        // // H: Bass(1)
        // musicians.add(new Musician("H", Set.of(1), new boolean[]{true, true}));

        // // I: Bass(1)
        // musicians.add(new Musician("I", Set.of(1), new boolean[]{true, true}));

        // // 4. Run Controller
        // SchedulerService scheduler = new SchedulerService(musicians, instruments, weeks);
        // SchedulerView view = new SchedulerView();
        // SchedulerController controller = new SchedulerController(scheduler, view);

        // controller.run(musicians, instruments);

    }
}
