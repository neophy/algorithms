import java.util.*;

// Follow the pseduo code written in the assignment
public class LargestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // Choosing List as the data structure as it provides functionality of easily adding and removing the 
        // numbers as well as adding numbers of same value 
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            digits.add(scanner.nextInt());
        }
        System.out.println(largestNumber(digits));
    }

    private static String largestNumber(List<Integer> digits) {
        String salary = "";
        while (digits.size() > 0) {
            int maxDigit = Integer.MIN_VALUE;
            for (Integer digit : digits) {
                if (isGreaterOrEqual(digit, maxDigit)) {
                    maxDigit = digit;
                }
            }
            salary += maxDigit;
            // Remove the digit once we have appended it in the salary
            digits.remove(digits.indexOf(maxDigit));
        }
        return salary;
    }

    /*
    If prepending digit (to left) of maxDigit makes the number greater then return true
    but if appending it (to right) of maxDigit makes the number greater then return false
     */
    private static boolean isGreaterOrEqual(Integer digit, Integer maxDigit) {
        //If maxDigit is Integer.MIN_VALUE which is a negative value
        if (maxDigit < 0) {
            return true;
        } else {
            //Example: 21,2 are the 2 nos. then 221 is greater or 212 is greater
            String option_1 = digit.toString() + maxDigit.toString();
            String option_2 = maxDigit.toString() + digit.toString();
            if (Integer.parseInt(option_1) >= Integer.parseInt(option_2)) {
                return true;
            }
        }
        return false;
    }
}
