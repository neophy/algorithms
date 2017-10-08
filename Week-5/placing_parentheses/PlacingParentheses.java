import java.util.Scanner;

public class PlacingParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    private static long getMaximValue(String exp) {
        // for ease of understanding, let's separate the values from operators
        int []values = new int[exp.length()/2 +1];
        for (int i = 0, k = 0 ; i<exp.length(); i = i+2,k++) {
            values[k] = Character.getNumericValue(exp.charAt(i));

        }
        // We make 2 matrices : min and max
        int [][] min = new int[values.length][values.length];
        int [][] max = new int[values.length][values.length];

        // If i=j i.e starts and ends at the same number then the value will be number itself
        // i is the starting index of values and j is the ending index of values

        for (int i = 0; i < values.length; i++) {
            min[i][i] = values[i];
            max[i][i] = values[i];
        }

        // As starting index i.e 'i' has to be less than ending index 'j' so we fill up the matrices above the diagonal
        // which means j will be i+1
        for (int diff=1; diff < values.length; diff++) { // This condition is driving the upward flow of diagonal as the diff increases the diagonal moves up
            for (int i = 0; i<=diff; i++) {
                int j = diff +i; // because diff = j-i
///TODO
            }
        }

    }

}

