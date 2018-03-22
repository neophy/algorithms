import java.util.*;
/**
 * Knapsack problem without repetitions and not using fractions.
 * We are given 'n' items and maximum weight of knapsack is 'W'
 * Weight of each item is also given.
 * Find out the maximum weight of knapsack possible.
 */
public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }

    private static int optimalWeight(int W, int[] w) {
        int noOfItems = w.length;
        int [][] weightArray = new int[noOfItems+1][W+1];

        for (int i = 0; i<=W; i++) {
            weightArray[0][i] = 0;
        }
        for (int i = 0; i<=noOfItems; i++) {
            weightArray[i][0] = 0;
        }

        // We go on each item and check if items' weight is less than the maximum weight which is 'j'
        // Then we check the 2 cases:
        // 1) If we don't take ith item then what will be the maximum weight
        // 2) If we take the ith item what will be the maximum weight
        // An important point here is that when we are on the ith item then we ask that what will be the maximum weight
        // taking items below ith item (inclusive). We don't account for items which are yet to come beyond 'i'.
        for (int i = 1; i<=noOfItems; i++) {
            int weightOfItem = w[i-1];
            for (int j = 1; j<=W; j++) {
                int maxWeight = j;
                if (weightOfItem <= maxWeight) {
                    weightArray[i][j] = getMaximum(weightArray[i-1][j], weightOfItem + weightArray[i-1][j-weightOfItem]);
                } else {
                    weightArray[i][j] = weightArray[i-1][j];
                }
            }
        }
        return weightArray[noOfItems][W];
    }

    private static int getMaximum (int a, int b) {
        if (a>=b) {
            return a;
        } else {
            return b;
        }
    }

}

