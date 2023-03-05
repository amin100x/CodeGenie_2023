package Tips_And_Trickes;

import java.util.Arrays;

public class FloatingPointOperations {
    public static void main(String[] args) {
        int[] marks = { 655, 350, 592 };
        int total = 700;

        float[] ans = new float[marks.length];

        for (int i = 0; i < marks.length; i++) {
            // Persentage Calculate
            ans[i] = (float) marks[i] * 100 / total;
        }
        for (int i = 0; i < marks.length; i++) {
            System.out.printf("%.2f ", ans[i]);
            System.out.println();
        }

        // OR
        float number = 3.14159f;
        String formattedNumber = String.format("%.2f", number);
        System.out.println(formattedNumber); // Output: 3.14

    }
}
