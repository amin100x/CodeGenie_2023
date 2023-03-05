package Tips_And_Trickes;

import java.util.*;

public class SortMap {

    public static void main(String[] args) {
        String[] stu = { "Mahesh", "Suresh", "Ramesh", "Amin", "Dhoni" };
        int[] marks = { 70, 90, 80, 99, 100 };

        // Sort By Key Accending
        Map<String, Integer> mapKey = new TreeMap<>();
        for (int i = 0; i < stu.length; i++) {
            mapKey.put(stu[i], marks[i]);
        }
        String ansKey[] = new String[stu.length];
        int k = 0;
        for (String s : mapKey.keySet()) {
            ansKey[k++] = s;
        }
        System.out.println(Arrays.toString(ansKey));

        // Sort By Key In Decending
        Map<String, Integer> mapKeyDec = new TreeMap<>();
        for (int i = 0; i < stu.length; i++) {
            mapKeyDec.put(stu[i], marks[i]);
        }
        Map<String, Integer> mapSortedDecKey = new TreeMap<>(Comparator.reverseOrder());
        mapSortedDecKey.putAll(mapKeyDec);
        String ansKeyDec[] = new String[stu.length];
        int u = 0;
        for (String s : mapSortedDecKey.keySet()) {
            ansKeyDec[u++] = s;
        }
        System.out.println(Arrays.toString(ansKeyDec));

        // Sort Map by Value in Decending Order
        Map<String, Integer> m = new TreeMap<>();
        Comparator<String> byValue = new Comparator<String>() {
            public int compare(String a, String b) {
                return Integer.compare(m.get(b), m.get(a));
            }
        };
        for (int i = 0; i < stu.length; i++) {
            m.put(stu[i], marks[i]);
        }
        Map<String, Integer> M = new TreeMap<>(byValue);
        M.putAll(m);
        String ans[] = new String[stu.length];
        int i = 0;
        for (String s : M.keySet()) {
            ans[i++] = s;
        }
        System.out.println(Arrays.toString(ans));

        // Sort Map by Value in Accending Order
        Map<String, Integer> map = new TreeMap<>();
        for (int j = 0; j < stu.length; j++) {
            map.put(stu[j], marks[j]);
        }
        Map<String, Integer> sortedMap = new TreeMap<>(byValue.reversed());
        sortedMap.putAll(map);
        String ansAce[] = new String[stu.length];
        int j = 0;
        for (String s : sortedMap.keySet()) {
            ansAce[j++] = s;
        }
        System.out.println(Arrays.toString(ansAce));

    }
}
