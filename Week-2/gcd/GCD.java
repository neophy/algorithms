import java.util.*;

public class GCD {
  // Ensured that a>b
  private static int gcd(int a, int b) {
    //Base cases
    if (a==b) return a;
    if (b == 0) return a;
    return gcd(b,a%b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(a>b ? gcd(a,b) : gcd(b,a));
  }
}

