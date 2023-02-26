import java.util.Arrays;

public class PercentileScoreCalculator {
    public static void main(String[] args) throws Exception {
      int totalStudents = 7;
      int[] marks = {800, 300, 950, 760, 680, 490, 640};
      int yourScore = 760;
  
      float percentileScore = PercentileScoreCalculator.calculatePercentileScore(totalStudents, marks, yourScore);
      System.out.printf("Your percentile score is : %.2f", percentileScore);
    }
  
    public static float calculatePercentileScore(int totalStudents, int[] marks, int yourScore){
        Arrays.sort(marks);
        float ans = (float) (Search(yourScore, marks)) * (100) / totalStudents;
        return ans;
    }
    static int Search(int score, int[] marks) {
        int s = 0;
        int e = marks.length - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;
            if (marks[m] == score)
                return m;
            else if (marks[m] < score)
                s = m + 1;
            else
                e = m - 1;
        }
        return 0;
    }
  }