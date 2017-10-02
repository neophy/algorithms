import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Sweep line algorithm
 * @link https://stackoverflow.com/a/33752996/4439957
 * Implements and uses quickSort and binarySearch
 */
public class PointsAndSegments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    //Sweep line algo.
    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        // Step-1: Put all the points of starts, ends and points array into one
        int[] mergedArray = new int[starts.length + ends.length + points.length];
        getMergedarray(mergedArray, starts, ends, points);
        //Step-2: Sort the merged array
        quickSort(mergedArray, 0, mergedArray.length - 1);
        //Step-3: Sort all the other arrays as we will be doing binarySearch
        quickSort(starts, 0, starts.length-1);
        quickSort(ends, 0, ends.length-1);
        return getCountForEachPoint(starts, ends, points, mergedArray);
    }

    private static int[] getCountForEachPoint(int[] starts, int[] ends, int[] points, int[] mergedArray) {
        int counter = 0;
        int []countArray = new int[points.length]; // Will have the count for each point of the points array
        Map<Integer, Integer> pointToCount = new HashMap<>();
        int []copyOfPoints = new int [points.length]; // one of the tricky part which I forgot once
        for (int i = 0; i < points.length; i++) {
            copyOfPoints[i] = points[i];
        }
        // We need a copy becuase sorting will change the sequence in main array
        quickSort(copyOfPoints, 0, copyOfPoints.length-1);

        for (int i= 0; i < mergedArray.length; i++) {
            int point = mergedArray[i];
            if (searchInArray(starts, 0, starts.length-1, point)) {
                counter ++; // If point present in starts array
            } else if (searchInArray(ends, 0, ends.length-1, point)) {
                counter --; // If point present in ends array
            }
            if (searchInArray(copyOfPoints, 0, copyOfPoints.length-1, point)) {
                // If point is the part of the points
                pointToCount.put(point, counter);
            }
        }

        for (int i = 0; i < points.length; i++) {
            countArray[i] = pointToCount.get(points[i]);
        }
        return countArray;
    }

    private static boolean searchInArray (int[] arr, int l, int r, int key) {
        if (l > r) {
            return false;
        }
        int mid = l + (r-l)/2;
        if (key == arr[mid]) {
            return true;
        }
        if (key < arr[mid]) {
            return searchInArray(arr, l, mid-1, key);
        } else {
            return searchInArray(arr, mid+1, r, key);
        }
    }

    // Complexity = O(n)
    private static void getMergedarray(int[] mergedArray, int[] starts, int[] ends, int[] points) {
        int k = 0; // mergedArray index
        for (int i = 0; i<starts.length; i++) {
            mergedArray[k++] = starts[i];
        }
        for (int i = 0; i<ends.length; i++) {
            mergedArray[k++] = ends[i];
        }
        for (int i = 0; i<points.length; i++) {
            mergedArray[k++] = points[i];
        }
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int partitionIndex = partition2(arr, l, r);
        quickSort(arr, l, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, r);
    }

    private static int partition2(int[] arr, int l, int r) {
        int pivot = arr[l];
        int j =  l;
        int i = j+1;
        while( i <=r ) {
            if (arr[i] <= pivot) {
                j++;
                swap(arr, j, i);
            }
            i++;
        }
        // Swap the pivot with the last element which was less than pivot (it will be at index j)
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }


}

