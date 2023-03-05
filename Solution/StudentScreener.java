import java.util.*;

public class StudentScreener {
    public static void main(String[] args) throws Exception {
        int totalStudents = 7;

        int totalMarks = 1000;
        float requiredPercentile = 50f;

        int[] marks = { 800, 300, 750, 760, 680, 790, 640 };
        String[] students = { "Kartik", "Devang", "Pari", "Ketan", "Sheetal", "Darshana", "Mohan" };
        String[] examResults = { "Passed", "Failed", "Passed", "Failed", "Passed", "Passed", "Passed" };

        String eligiblStudents = getEligibleStudents(totalStudents, students, marks, examResults, totalMarks,
                requiredPercentile);
        System.out.println(eligiblStudents);
    }

    public static String getEligibleStudents(int totalStudents, String[] students, int[] marks, String[] examResults,
            int totalMarks, float requiredPercentile) {

        int n = students.length;
        Map<Integer, String> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        for (int i = 0; i < students.length; i++) {
            map1.put(marks[i], students[i]);
            map2.put(marks[i], examResults[i]);
        }

        int ReuiredRank = n - (int) (requiredPercentile * n / 100);
        Arrays.sort(marks);

        List<String> eligibal = new ArrayList<>();
        for (int i = n - 1; i > n - ReuiredRank; i--) {
            if (map2.get(marks[i]) == "Passed")
                eligibal.add(map1.get(marks[i]));
        }
        return String.join(",", eligibal);
    }
}
