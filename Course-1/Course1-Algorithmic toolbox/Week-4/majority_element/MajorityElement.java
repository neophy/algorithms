//package com.myntra.oms.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.io.InputStreamReader;
import java.lang.System;
import java.util.StringTokenizer;

/**
 * sol1: Divide and Conquer : O(nlogn)
 * sol2: Using HashMaps     : O(n)
 */
public class MajorityElement {
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = getTheMajorityElement(a, 0, a.length - 1);
        System.out.println(result == -1 ? 0 :1);
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


    private static int getTheMajorityElement (int [] a, int left, int right) {
        if (left < right) {
            int middle = left + (right-left)/2;
            int m1 = getTheMajorityElement(a, left, middle);
            int m2 = getTheMajorityElement(a, middle + 1, right);
            return mergePartitions(m1, m2, a, left, right);
        } else if (left == right) {
            return a[left];
        } else {
            return -1;
        }
    }

    private static int mergePartitions(int m1, int m2, int[]a, int left, int right) {
//        System.out.println("left is "+ left+ ", right is"+right+ ", m1 is "+m1+", m2 is "+m2);
        int middle = left +(right-left)/2;
        if (m1 == -1 && m2 == -1) {
            return -1;
        } else if(m1 == -1 && m2 != -1) {
            return checkCountOfMajority(a, m2, left, right);
        } else if(m1 != -1 && m2 == -1) {
            return checkCountOfMajority(a, m1, left, right);
        } else if (m1 == m2) {
            return m1;
        }
        int count_m1 = checkCountOfMajority(a, m1, left, right);
        if (count_m1 != -1) {
            return m1;
        }
        int count_m2 = checkCountOfMajority(a, m2, left, right);
        if(count_m2 != -1) {
            return m2;
        }
        return -1;
    }

    static int checkCountOfMajority(int[] a, int key, int left, int right) {
        int count = getCount(a, key, left, right);
        int arrSize = right-left+1;
        if (count > arrSize/2) {
            return key;
        } else {
            return -1;
        }
    }
    static int getCount (int[] a, int key, int start, int end) {
        int count = 0;
        for (int i = start; i <=  end; i++) {
            if (a[i] == key) count++;
        }
        return count;
    }

}



