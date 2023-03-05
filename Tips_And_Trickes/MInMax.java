package Tips_And_Trickes;

import java.util.*;

public class MInMax {
    public static void main(String[] args) {
        String[] stu = { "Mahesh", "Suresh", "Ramesh", "Amin", "Dhoni" };
        int[] marks = { 70, 90, 80, 99, 100 };

        // O(N) solution.
        int max = 0, min = 0;
        for (int i = 0; i < stu.length; i++) {
            if (marks[i] < marks[min]) {
                min = i;
            } else if (marks[i] > marks[max]) {
                max = i;
            }
        }
        System.out.println("Maximun= " + stu[max]);
        System.out.println("Minimum= " + stu[min]);
    }
}
