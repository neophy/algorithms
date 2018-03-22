import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * We have 'n' candies which are to be distributed to max. no. of children given that no 2 children get same no. of
 * candies. How many children will get candies ?
 * @author Neophy
 * Note: Doesn't work for 10*power(9)
 */
public class DifferentSummands {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();

        // Summands should be stored as Set as Set has distinct elements
        Set<BigInteger> summands = new HashSet<>();
        BigInteger maximumSummand = BigInteger.ZERO;
        summands = optimalSummands(summands, n, maximumSummand);
        System.out.println(summands.size());
        for (BigInteger summand : summands) {
            System.out.print(summand + " ");
        }
    }

    private static Set<BigInteger> optimalSummands(Set<BigInteger> summands, BigInteger n, BigInteger maximumSummand) {
        // Base cases
        if (n.equals(BigInteger.ZERO)) return summands;
        if (n.equals(BigInteger.ONE)) return Collections.singleton(n);

        BigInteger nextSummand = n.subtract(maximumSummand.add(BigInteger.ONE));
        // Example: n = 2
        if (nextSummand.compareTo(maximumSummand.add(BigInteger.ONE)) <= 0) {
            return Collections.singleton(n);
        }

        if (summands.contains(maximumSummand.add(BigInteger.ONE)) || summands.contains(nextSummand)) {
            return Collections.singleton(n);
        }
        summands.add(maximumSummand.add(BigInteger.ONE));
        maximumSummand = maximumSummand.add(BigInteger.ONE);
        Set<BigInteger> b = optimalSummands(summands, nextSummand, maximumSummand);
        summands.addAll(b);
        return summands;
    }
}

