 /**
 * Created by 11151 on 24/09/17.
 */
 import java.util.*;

/**
 * We have 'n' candies which are to be distributed to max. no. of children given that no 2 children get same no. of
 * candies. How many children will get candies ?
 * Solution:
 */
public class DifferentSummands {
    public static Stack<Long> summands = new Stack<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long n = scanner.nextLong();

        // Summands should be stored as Set as Set has distinct elements
        Long sum = 0l;
        optimalSummands( n, sum);
        System.out.println(summands.size());
        Iterator<Long> ite = summands.iterator();
        Long summand;
        while (ite.hasNext()) {
            summand = ite.next();
            System.out.print(summand + " ");
        }
    }

    private static boolean optimalSummands(Long n, Long sum) {
        // Base cases
        if(sum.compareTo(n)==0){
            return true;
        }

        if(sum.compareTo(n)>0) {
            return false;
        }

        Long lastNum;
        if(summands.size()>0) {
            lastNum = summands.peek();
        } else {
            lastNum = 0l;
        }

        for(Long nextNum = lastNum+1; (sum+nextNum)<=n;) {
            sum = sum+nextNum;
            summands.add(nextNum);
            boolean ans = optimalSummands(n, sum);
            if(ans==true){
                return true;
            }
            sum = sum-nextNum;
            summands.pop();
            nextNum = nextNum+1;
        }
        return false;
    }
}