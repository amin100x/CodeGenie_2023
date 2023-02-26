/*
 * Test Case - 1
 * Final Number = 2500
 * Operations = {"X + 10", "X - 5", "X * 5", "X ^ 2"}
 * Expected Output: 5
 * 
 * Test Case - 2
 * Final Number = 1000
 * Operations = {"X * 5", "X / 0", "X ^ 3"}
 * Expected Output: -1
 * 
 * Test Case - 3
 * Final Number: 10
 * Operations: {"X * 5", "X * 0", "X + 10"}
 * Expected Output : -2
 * 
 * Test Case - 4
 * Final Number: 617283948
 * Operations: {"X + 5”, “X - 0”, “X + 1”, “X / 2”, “X ^ 1”}
 * Expected Output: 1234567890
 */

public class GuessTheNumber {
    public static void main(String[] args) {
        int finalNumber = 617283948;
        String[] operations = { "X + 5", "X - 0", "X + 1", "X / 2", "X ^ 1" };

        int actualNumber = GuessTheNumber.getActualNumber(finalNumber, operations, operations.length);
        System.out.println("The actual number will be " + actualNumber + " after performing given operations.");
    }

    public static int getActualNumber(int finalNumber, String[] operations, int totalOperations) {
        int ans = finalNumber;

        for (int i = operations.length - 1; i >= 0; i--) {
            String s = operations[i];
            int num = Integer.parseInt(s.substring(4));
            char op = s.charAt(2);

            // Exception Cases
            if (op == '/' && num == 0)
                return -1;
            if ((op == '*' && num == 0) || (op == '^' && num == 0) || op == '%')
                return -2;

            // Apply Operation
            if (op == '+')
                ans -= num;
            else if (op == '*')
                ans /= num;
            else if (op == '-')
                ans += num;
            else if (op == '/')
                ans *= num;
            else {
                ans = (int) Math.pow(ans, 1 / num);
            }
        }
        return ans;
    }
}