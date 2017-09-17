import java.util.Scanner;

public class FibonacciLastDigit {
  private static int getFibonacciLastDigit(int n) {
    if (n<=1) return n;
    int [] lastDigitArray = new int[n+1]; //n+1 numbers 
    lastDigitArray[0] = 0;
    lastDigitArray[1] = 1;
    for (int i=2; i <=n ;  i++) {
      // Modulo with 10 gives last digit
      lastDigitArray[i] = (lastDigitArray[i-2] + lastDigitArray[i-1])%10;
    }
    return lastDigitArray[n];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int c = getFibonacciLastDigit(n); // n is the index
    System.out.println(c);
  }
}
