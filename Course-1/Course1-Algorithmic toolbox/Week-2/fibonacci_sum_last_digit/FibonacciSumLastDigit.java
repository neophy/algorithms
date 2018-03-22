import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciSumLastDigit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(getFibonacciSumLastDigit(n));
    }

    private static long getFibonacciSumLastDigit(long n) {
        ArrayList<Long> moduloArr = new ArrayList<>();
        if (n<=2)return n;
        moduloArr.add(0l);
        moduloArr.add(1l);
        moduloArr.add(2l);
        moduloArr = getModuloArr(moduloArr);
        long period = moduloArr.size() - 2;
        long fibonacciIndex = n % period;
        return moduloArr.get((int) fibonacciIndex);
    }

    private static ArrayList<Long> getModuloArr(ArrayList<Long> moduloArr) {
        for (int i = 3; ; i++) {
            moduloArr.add((moduloArr.get(i - 1) % 10 + moduloArr.get(i - 2) % 10 + 1) % 10);
            if (checkForPisano(moduloArr, i)) return moduloArr;
        }
    }

    private static boolean checkForPisano(ArrayList<Long> moduloArr, int currentIndex) {
        if (moduloArr.get(currentIndex).equals(1l) && moduloArr.get(currentIndex - 1).equals(0l)) return true;
        return false;
    }
}

