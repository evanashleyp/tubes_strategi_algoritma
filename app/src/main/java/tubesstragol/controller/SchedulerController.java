package tubesstragol.controller;
import java.util.*;
import tubesstragol.model.*;
import tubesstragol.service.*;
import tubesstragol.view.SchedulerView;


public class SchedulerController {
    private SchedulerService schedulerService;
    private SchedulerView view;

    public SchedulerController(SchedulerService schedulerService, SchedulerView view) {
        this.schedulerService = schedulerService;
        this.view = view;
    }

    public void run(List<Musician> musicians, List<Instrument> instruments) {
        int solNo = 0;
        while (true) {
            Schedule s = schedulerService.getNextSolution();
            if (s == null) break;
            solNo++;
            double fairness = SchedulerService.computeFairness(s, musicians);
            view.displaySolution(solNo, s, musicians, instruments, fairness);
            if (solNo >= 3) break;
        }
        view.displayDone();
    }
}
