import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//Don’t forget that more than 1 set of values and weight could have same value/weight fraction value. Code thinking abt this as well

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double [] unsortedFractions = new double[values.length];
        HashMap<Double, Integer> fractionToWeight = new HashMap<>();
        HashMap<Double, Double> fractionToValue = new HashMap<>();
        for (int i = 0 ; i < values.length ; i++) {
            unsortedFractions[i] = (double)values[i]/weights[i];
            if (fractionToWeight.containsKey(unsortedFractions[i])) {
                Integer alreadyPresentWeight = fractionToWeight.get(unsortedFractions[i]);
                Double alreadyPresentValue = fractionToValue.get(unsortedFractions[i]);
                fractionToWeight.put(unsortedFractions[i], alreadyPresentWeight + weights[i]);
                fractionToValue.put(unsortedFractions[i], alreadyPresentValue + values[i]);
            } else {
                fractionToWeight.put(unsortedFractions[i], weights[i]);
                fractionToValue.put(unsortedFractions[i], values[i] + 0.0D);
            }
        }
        double[] sortedFractions = sort(unsortedFractions);
        return getMaxAmount(capacity, sortedFractions, fractionToWeight);

    }
    private static double[] sort(double [] unsortedFractions) {
        // Dual-Pivot Quicksort with O(nlog(n)) complexity
        Arrays.sort(unsortedFractions);
        return unsortedFractions;
    }
    private static double getMaxAmount(int capacity, double[] sortedFractions,
                                       HashMap<Double, Integer> fractionToWeight) {
        double maxAmount = 0D;
        for (int i=sortedFractions.length-1; i>=0 && capacity > 0; i--) {
            int weight = fractionToWeight.get(sortedFractions[i]);
            if (capacity >= weight) {
                maxAmount += weight * sortedFractions[i];
                capacity  -= weight;
            } else {
                // Some fraction of weight can fulfill the capacity
                maxAmount += capacity * sortedFractions[i];
                capacity = 0;
            }
        }
        return Math.round(maxAmount*100000D)/100000D; // To get result for 5 decimal places only
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
}

