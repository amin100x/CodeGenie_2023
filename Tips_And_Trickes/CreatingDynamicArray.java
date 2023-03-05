package Tips_And_Trickes;

import java.util.*;

public class CreatingDynamicArray {
    public static void main(String[] args) {
        String[] stu = { "Mahesh", "Suresh", "Ramesh", "Amin", "Dhoni" };
        int[] marks = { 70, 90, 80, 99, 100 };
        int MinMarks = 80;

        Map<String, Integer> m = new TreeMap<>();
        for (int i = 0; i < stu.length; i++) {
            m.put(stu[i], marks[i]);
        }
        Comparator<String> byValue = new Comparator<String>() {
            public int compare(String a, String b) {
                return Integer.compare(m.get(a), m.get(b));
            }
        };
        Map<String, Integer> sorted = new TreeMap<>(byValue.reversed());
        sorted.putAll(m);

        List<String> ans = new ArrayList<>();
        for (String str : sorted.keySet()) {
            if (sorted.get(str) >= MinMarks)
                ans.add(str);
            else
                break;
        }

        System.out.println(ans);
    }
}
