import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class MaxPairwiseProduct {
    static BigInteger getMaxPairwiseProduct(int[] numbers) {
        BigInteger result;
        int max1 = numbers[0];
        int max2 = numbers[1];
        int n = numbers.length;
        for (int i = 2; i < n; ++i) {
             if(max1 < max2) {
                 if (max1 < numbers[i]) {
                     max1 = numbers[i];
                 }
             } else {
                 if (max2 < numbers[i]) {
                     max2 =numbers[i];
                 }
             }
        }
        result = convertToBigInteger(max1).multiply(convertToBigInteger(max2));
        return result;
    }

    private static BigInteger convertToBigInteger(int num) {
           Long longNum = Long.parseLong(String.valueOf(num)); 
           return BigInteger.valueOf(longNum);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}

