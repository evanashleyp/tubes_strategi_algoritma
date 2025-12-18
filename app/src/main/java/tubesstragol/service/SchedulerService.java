package tubesstragol.service;


import java.util.*;
import tubesstragol.model.Instrument;
import tubesstragol.model.Musician;
import tubesstragol.model.Schedule;

public class SchedulerService {
    private List<Musician> musicians;
    private List<Instrument> instruments;
    private int weeks;

    private int V;
    private List<Integer>[] domains;
    private int[] choiceIndex;
    private int[] assignments;
    private int[][] capacitiesUsed;
    private int vPointer;
    private boolean exhausted;

    public SchedulerService(List<Musician> musicians, List<Instrument> instruments, int weeks) {
        this.musicians = musicians;
        this.instruments = instruments;
        this.weeks = weeks;
        this.V = weeks * musicians.size();
        this.domains = new List[V];
        this.choiceIndex = new int[V];
        this.assignments = new int[V];
        Arrays.fill(this.assignments, Integer.MIN_VALUE);
        this.capacitiesUsed = new int[weeks][instruments.size()];
        this.vPointer = 0;
        this.exhausted = false;
        prepareDomains();
    }

    private void prepareDomains() {
        int mCount = musicians.size();
        for (int w = 0; w < weeks; w++) {
            for (int m = 0; m < mCount; m++) {
                int v = w * mCount + m;
                List<Integer> d = new ArrayList<>();
                if (!musicians.get(m).getAvailable()[w]) {
                    d.add(-1);
                } else {
                    for (int instr : musicians.get(m).getInstruments()) {
                        d.add(instr);
                    }
                    d.add(-1);
                }
                domains[v] = d;
                choiceIndex[v] = 0;
            }
        }
    }

    private int countPlayersForInstrument(int instrIndex) {
        int count = 0;
        for (Musician m : musicians) {
            if (m.getInstruments().contains(instrIndex)) count++;
        }
        return count;
    }

    public Schedule getNextSolution() {
        if (exhausted) return null;
        int mCount = musicians.size();


        while (true) {
            if (vPointer == V) {
                Schedule s = buildScheduleFromAssignments();
                vPointer = V - 1;
                choiceIndex[vPointer]++;
                int lastAssigned = assignments[vPointer];
                int lastWeek = vPointer / mCount;
                if (lastAssigned != Integer.MIN_VALUE && lastAssigned != -1) {
                    capacitiesUsed[lastWeek][lastAssigned]--;
                }
                assignments[vPointer] = Integer.MIN_VALUE;
                return s;
            }

            if (vPointer < 0) {
                exhausted = true;
                return null;
            }

            List<Integer> dom = domains[vPointer];
            if (choiceIndex[vPointer] >= dom.size()) {
                if (assignments[vPointer] != Integer.MIN_VALUE && assignments[vPointer] != -1) {
                    int wk = vPointer / mCount;
                    capacitiesUsed[wk][assignments[vPointer]]--;
                }
                assignments[vPointer] = Integer.MIN_VALUE;
                choiceIndex[vPointer] = 0;
                vPointer--;
                continue;
            }

            int choice = dom.get(choiceIndex[vPointer]);
            int wk = vPointer / mCount;
            int musicianIndex = vPointer % mCount;
            boolean ok = true;

            if (choice != -1) {
                // Rule 1: no duplicate instrument in the same week
                for (int mm = 0; mm < musicians.size(); mm++) {
                    int varIdx = wk * musicians.size() + mm;
                    if (assignments[varIdx] == choice) {
                        ok = false;
                        break;
                    }
                }

                // Rule 2: player cannot play same instrument in different weeks unless unique
                if (ok) {
                    int capableCount = countPlayersForInstrument(choice);
                    if (capableCount > 1) {
                        for (int wPrev = 0; wPrev < wk; wPrev++) {
                            int prevIdx = wPrev * musicians.size() + musicianIndex;
                            if (assignments[prevIdx] == choice) {
                                ok = false;
                                break;
                            }
                        }
                    }
                }


                if (ok && capacitiesUsed[wk][choice] >= instruments.get(choice).getCapacity()) {
                    ok = false;
                }
            }

            if (ok) {
                assignments[vPointer] = choice;
                if (choice != -1) capacitiesUsed[wk][choice]++;
                vPointer++;
            } else {
                choiceIndex[vPointer]++;
            }
        }
    }

    private Schedule buildScheduleFromAssignments() {
        int mCount = musicians.size();
        Schedule s = new Schedule(weeks, mCount);
        for (int v = 0; v < V; v++) {
            int w = v / mCount;
            int m = v % mCount;
            int val = assignments[v];
            if (val == Integer.MIN_VALUE) val = -1;
            s.set(w, m, val);
        }
        return s;
    }

    public static double computeFairness(Schedule s, List<Musician> musicianList) {
        int mCount = musicianList.size();
        int[] assignedCounts = new int[mCount];
        for (int w = 0; w < s.getWeeks(); w++) {
            for (int m = 0; m < mCount; m++) {
                if (s.get(w, m) != -1) assignedCounts[m]++;
            }
        }
        double mean = Arrays.stream(assignedCounts).average().orElse(0);
        double variance = 0;
        for (int c : assignedCounts) variance += Math.pow(c - mean, 2);
        variance /= mCount;
        double stddev = Math.sqrt(variance);
        double denom = (mean == 0) ? 1.0 : mean;
        double score = 100.0 - (100.0 * (stddev / denom));
        return Math.max(0, Math.min(100, score));
    }
}