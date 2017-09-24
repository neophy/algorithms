import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * We have 'n' candies which are to be distributed to max. no. of children given that no 2 children get same no. of
 * candies. How many children will get candies ?
 * @author Neophy
 * Solution: As explained in the lemma in the instruction page of course
 * @link https://medium.com/competitive/pairwise-distinct-summands-9ef4e8686b17
 */

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        for (int k = n, l =1; k > 0 ; l++) {
            if (k<=2*l) {
                summands.add(k);
                k -=k;
            } else {
                summands.add(l);
                k -= l;
            }
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

