package tubesstragol.model;
import java.util.*;

public class Schedule {
    private int weeks;
    private int musicians;
    private int[][] assignment;

    public Schedule(int weeks, int musicians) {
        this.weeks = weeks;
        this.musicians = musicians;
        this.assignment = new int[weeks][musicians];
        for (int w = 0; w < weeks; w++) {
            Arrays.fill(assignment[w], -1);
        }
    }

    public void set(int week, int musician, int instr) {
        assignment[week][musician] = instr;
    }

    public int get(int week, int musician) {
        return assignment[week][musician];
    }

    public int getWeeks() {
        return weeks;
    }

    public int getMusicians() {
        return musicians;
    }

    public String toString(List<Musician> musicianList, List<Instrument> instrumentList) {
        StringBuilder sb = new StringBuilder();
        for (int w = 0; w < weeks; w++) {
            sb.append("Week ").append(w + 1).append(":\n");
            for (int m = 0; m < musicians; m++) {
                int instr = assignment[w][m];
                String instrName = (instr >= 0) ? instrumentList.get(instr).getName() : "OFF";
                sb.append("  ").append(musicianList.get(m).getName())
                        .append(" -> ").append(instrName).append("\n");
            }
        }
        return sb.toString();
    }
}