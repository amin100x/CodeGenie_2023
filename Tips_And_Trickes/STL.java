package Tips_And_Trickes;

public class STL {
    public static void main(String[] args) {

        // MATH
        int number = -10;
        int absoluteValue = Math.abs(number);
        System.out.println(absoluteValue); // Output: 10

        double base = 2.0;
        double exponent = 3.0;
        double result = Math.pow(base, exponent);
        System.out.println(result); // Output: 8.0

        double number1 = 25.0;
        double squareRoot = Math.sqrt(number1);
        System.out.println(squareRoot); // Output: 5.0

        double number11 = 5.6;
        int closestInteger = (int) Math.round(number11);
        System.out.println(closestInteger); // Output: 6

        int number122 = 10;
        int number223 = 20;
        int maxNumber = Math.max(number122, number223);
        int minNumber = Math.min(number122, number223);
        System.out.println(maxNumber); // Output: 20
        System.out.println(minNumber); // Output: 10

        double randomValue = Math.random();
        System.out.println(randomValue); // Output: 0.123456789 (example output)

        // Input positive value, Output floor value of x
        double x = 94.69;
        System.out.println(Math.floor(x)); // 94

        double y = 83.56;    
        System.out.println(Math.ceil(y));  // 84
        
        
    }
}
