import java.io.*;
import java.util.*;

public class BinarySearch {
    // a is the array in which we have to search
    // key with which we have to search in array a
    // return the index of key in array a if present else return -1
    static int search(long[] a, long key) {
        int left = 0;
        int right = a.length -1;
        if (key > a[a.length-1] || key < a[0]) {
            return -1;
        }
        return binarySearch(a, left, right, key);
    }

    static int binarySearch (long[]a, int left, int right, long key) {
        // I was making mistake in adding 'left' to the below equation
        int middle = left + (right-left)/2;
        // Base case
        if (left > right) {
            return -1;
        }
        if (key == a[middle]) {
            return middle;
        } else if (key < a[middle]) {
            right = middle-1;
        } else {
            left = middle + 1;
        }
        return binarySearch(a, left, right, key);
    }

//    static int linearSearch(int[] a, int x) {
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] == x) return i;
//        }
//        return -1;
//    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        int m = scanner.nextInt();
        long[] b = new long[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextLong();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(search(a, b[i]) + " ");
        }
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
