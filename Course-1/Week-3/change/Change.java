import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int[] denominations = {10,5,1}; // already sorted in decreasing order
        int countOfCoins = 0;
        for (int i = 0; i < denominations.length && m > 0; i++) {
            if (m >= denominations[i]) {
                countOfCoins += m / denominations[i];
                m = m - denominations[i] * (m / denominations[i]);
            }
        }
        return countOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}
