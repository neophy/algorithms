import java.util.*;

/**
 * We have 'n' candies which are to be distributed to max. no. of children given that no 2 children get same no. of
 * candies. How many children will get candies ?
 * Solution:
 */
public class DifferentSummands {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // Summands should be stored as Set as Set has distinct elements
        Set<Integer> summands = new HashSet<>();
        summands = optimalSummands(summands, n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }

    private static Set<Integer> optimalSummands(Set<Integer> summands, int n) {

    }
}

