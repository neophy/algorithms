import java.lang.System;
import java.util.*;

/**
 * To find the no. of inversions, we do a normal MergeSort. At the same time, we increment noOfInversions 
 * as explained below.
 */
public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        int[] b = new int[n];
//        System.out.println(getNumberOfInversions(a, b, 0, a.length));
        // no. of inversions
        System.out.println(mergeSort(a, 0, a.length-1));
        // To print the sorted array
//        for (int i = 0; i < n; i++) {
//            System.out.print(a[i] + " ");
//        }
    }

    private static long mergeSort(long[] a, int l, int r) {
        if (l >= r ) {
            return 0;
        }
        int mid = (l+r)/2;
        long numberOfInversions = 0;
        numberOfInversions += mergeSort(a, l, mid);
        numberOfInversions += mergeSort(a, mid + 1, r);
        numberOfInversions += merge(a, l , r, mid);
        return numberOfInversions;
    }


    private static long merge (long[]a, int l, int r, int mid) {
        long [] mergedArray = new long[r-l+1];
        long noOfInversions = 0;
        // Initialize 2 pointers
        int i = l;
        int j = mid+1;
        int k = 0;
        while ( i<= mid && j <= r) {
            if (a[i] < a[j]) {
                mergedArray[k++] = a[i++];
            } else if(a[i] == a[j]) {
                // When a[i] is equal to a[j], no swap is needed 
                mergedArray[k++] = a[i++];
            } else {
                mergedArray[k++] = a[j++];
                // Only when a[i] > a[j] then we will take the a[j] element  and put before a[i]
                // To be able to do it, we have to swap all the elements in between a[i] and a[j] 
                // which is equal to (mid-i) + 1 number of swaps.
                noOfInversions+= (mid - i) + 1;
            }
        }

        if (i == mid+1) {
            while(j <= r) {
                mergedArray[k++] = a[j++];
            }
        } else if (j == r+1) {
            while(i <= mid) {
                mergedArray[k++] = a[i++];
            }
        }

        for (i = 0; i < mergedArray.length; i++) {
            a[l+i] = mergedArray[i];
        }

        return noOfInversions;
    }
}

