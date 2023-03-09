package Tips_And_Trickes;

import java.util.*;

public class StringParsings {
    public static void main(String[] args) {
        String s = "mahmadamin lodhiya";
        System.out.println(s.contains("amin0"));
        System.out.println(s.substring(11));
        System.out.println(s.substring(0, 10));

        // Join
        System.out.println(String.join("^", "Amin", "Lodhiya", "Dhoni"));
        List<String> l = new ArrayList<>();
        l.add("Ms");
        l.add("Dhoni");
        l.add("The Best");
        l.add("Captain");
        System.out.println(String.join("**", l));

        // Concat
        String s1 = "java string";
        // The string s1 does not get changed, even though it is invoking the method
        // concat(), as it is immutable. Therefore, the explicit assignment is required
        // here.
        s1.concat("is immutable");
        System.out.println(s1);
        s1 = s1.concat(" is immutable so assign it explicitly");
        System.out.println(s1);
        // Concatenating multiple strings
        String str5 = s1.concat(" a").concat(" u");
        System.out.println(str5);

        // REPLACE
        String S = "javatpoint is a very good website";
        String replaceString = S.replace('a', 'e');// replaces all occurrences of 'a' to 'e'
        System.out.println(replaceString);
        String s11 = "my name is khan my name is java";
        String replaceString1 = s11.replace("is", "was");// replaces all occurrences of "is" to "was"
        System.out.println(replaceString1);

        // String to Int and Int to String
        int n = Integer.parseInt("1355");
        System.out.println(n + 10);
        String str = String.valueOf(n);
        System.out.println(str+"AMKM");

        // Split
        String s22 = "java,string,split,method,by,javatpoint";
        String[] words = s22.split(",");// splits the string based on whitespace
        System.out.println(Arrays.toString(words));

        // Trim
        String s12 = "  hello java string   ";
        System.out.println(s1.length());
        System.out.println(s1); // Without trim()
        String tr = s12.trim();
        System.out.println(tr.length());
        System.out.println(tr); // With trim()

        System.out.println();
        // Example
        String exp = "1234567890 + 12345";
        String[] arr = exp.split(" ");
        if (arr[1].equals("+")) // Avi rite badha operations in condition lakhi nakhavani..
            System.out.println(Integer.parseInt(arr[0]) + Integer.parseInt(arr[2]));

    }
}
