import java.io.*;
import java.lang.System;
import java.util.*;

/**
 * For 3 partition, we have to choose 2 indexes and then sort
 */
public class Sorting {
    private static Random random = new Random();
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
//       TODO: Failing for case-14 ?? Rest all cases passed. See the implementation of Arrays.sort and find out the failing case
//        randomizedQuickSort(a, 0, n - 1);
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void randomizedQuickSort(long[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        // Using random key for Partiton2. For Partition3, it might be difficult to get 2 random indexes as the
        // random generator might return the same value for a small range. So, in case of Partition3, will choose first 2 indexes as the pivot
//        int k = random.nextInt(r - l + 1) + l;
//        int t = a[l];
//        a[l] = a[k];
//        a[k] = t;
//        int m = partition2(a, l, r);

        int[] pivots = partition3(a, l, r); // Have pivot indexes  m1 and m2
        int m1 = pivots[0];
        int m2 = pivots[1];

        randomizedQuickSort(a, l, m1 - 1);
        randomizedQuickSort(a, m1+1, m2-1);
        randomizedQuickSort(a, m2 + 1, r);
    }

    private static int[] partition3(long[] a, int l, int r) {
        int[] pivots = new int[2];
        if (a[l] > a[l+1]) {
            swapElements(a, l, l+1); // I made a mistake here by hardcoding the indexes instead of l, l+1
        }

        // Initializing 3 pointers i,j,k
        int p1 = l;
        int p2 = l+1;
        int j  = l + 1;

        for (int i = j+1; i<=r; i++) {
            // Case-1: a[i] lies in between p1 and p2
            // In this case 2 swaps needed to take a[i] to the correct range
            if (a[i] >= a[p1] && a[i] <= a[p2]) {
                j++;
                swapElements(a, j, i);
                swapElements(a, j-1, j);
                p2++; // Pivot-2 has moved forward

            }
            // Case-2: a[i] lies before the p1
            // We have to do 4 swaps to take a[i] to the correct range
            else if (a[i] < a[p1]) {
                j++;
                swapElements(a, j, i);
                swapElements(a, j-1, j);
                swapElements(a, j-1, p1+1);
                swapElements(a, p1, p1+1); // I missed doing this swap
                p1 ++;
                p2 ++;
            }
        }
        pivots[0] = p1;
        pivots[1] = p2;
        return pivots;
    }

    private static void swapElements(long []a, int i, int j) {
        long x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
    private static int partition2(int[] a, int l, int r) {
        // We take the first element as pivot.
        // Aim will be to partition the given array into 2 parts such that pivot is at its final place
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        // Swap the pivot with the last element which was less than pivot (it will be at index j)
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
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
        long nextLong() {
            return Long.parseLong(next());
        }

    }
}


