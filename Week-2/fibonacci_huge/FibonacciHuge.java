import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciHuge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }

    private static long getFibonacciHuge(long n, long m) {
        ArrayList<Long> moduloArr = getModuloArr(m);
        long period = moduloArr.size() - 2;
        long fibonacciIndex = n % period;
        return moduloArr.get((int) fibonacciIndex);
    }

    private static ArrayList<Long> getModuloArr(long m) {
        ArrayList<Long> moduloArr = new ArrayList<>();
        moduloArr.add(0l);
        moduloArr.add(1l);
        for (int i = 2; ; i++) {
            moduloArr.add((moduloArr.get(i - 1) % m + moduloArr.get(i - 2) % m) % m);
            if (checkForPisano(moduloArr, i)) return moduloArr;
        }
    }

    private static boolean checkForPisano(ArrayList<Long> moduloArr, int currentIndex) {
        if (moduloArr.get(currentIndex).equals(1l) && moduloArr.get(currentIndex - 1).equals(0l)) return true;
        return false;
    }
}
