import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciPartialSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }

    private static long getFibonacciPartialSum(long from, long to) {
        if (from == 0 || from == to) return getFibonacci(to);
        long lastDigitOfFrom = getFibonacciSumLastDigit(from-1);
        long lastDigitOfTo = getFibonacciSumLastDigit(to);
        long difference = lastDigitOfTo-lastDigitOfFrom;
        if (difference>=0) {
		return difference;
        } else {
		return (10+difference);
	}

    }

    private static long getFibonacci(long n) {
        ArrayList<Long> moduloArr = new ArrayList<>();
        if (n<=2)return n;
        moduloArr.add(0l);
        moduloArr.add(1l);
        moduloArr = getModuloArr(moduloArr);
        long period = moduloArr.size() - 2;
        long fibonacciIndex = n % period;
        return moduloArr.get((int) fibonacciIndex);
    }
    private static ArrayList<Long> getModuloArr(ArrayList<Long> moduloArr) {
        for (int i = 2; ; i++) {
            moduloArr.add((moduloArr.get(i - 1) % 10 + moduloArr.get(i - 2) % 10) % 10);
            if (checkForPisano(moduloArr, i)) return moduloArr;
        }
    }
    private static long getFibonacciSumLastDigit(long n) {
        ArrayList<Long> moduloArr = new ArrayList<>();
        if (n<=2)return n;
        moduloArr.add(0l);
        moduloArr.add(1l);
        moduloArr.add(2l);
        moduloArr = getModuloArrForSum(moduloArr);
        long period = moduloArr.size() - 2;
        long fibonacciIndex = n % period;
        return moduloArr.get((int) fibonacciIndex);
    }

    private static ArrayList<Long> getModuloArrForSum(ArrayList<Long> moduloArr) {
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

