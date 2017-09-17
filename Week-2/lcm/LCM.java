import java.util.*;
import java.math.BigInteger;

public class LCM {

//    private static BigInteger lcm(int a, int b) {
//        if (a==1 || b==1)return convertIntToBigInt(a).multiply(convertIntToBigInt(b));
//        if (a%b == 0) return convertIntToBigInt(a); // Example: 11,55
//        for (int i = a;  ;i++) {
//            if (i%a==0 && i%b==0){
//                return convertIntToBigInt(i);
//            }
//        }
//        //return convertIntToBigInt(a).multiply(convertIntToBigInt(b));
//    }
//    private static boolean isPrime (int a) {
//        for (int i=2; i<=a; i++) {
//            if(a!=i && a%i==0) return false;
//        }
//        return true;
//    }
//    private static BigInteger convertIntToBigInt(int num) {
//        Long longNum = Long.parseLong(String.valueOf(num));
//        return BigInteger.valueOf(longNum);
//    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        BigInteger a = scanner.nextBigInteger();
        BigInteger b = scanner.nextBigInteger();
        System.out.println(a.compareTo(b) != 1 ? lcm(a, b): lcm(b,a));
    }

    private static BigInteger lcm (BigInteger a, BigInteger b) {
        BigInteger product = a.multiply(b);
        BigInteger lcm = product.divide(gcd(a,b));
        return lcm;
    }
    // Ensured that a>b
    private static BigInteger gcd(BigInteger a, BigInteger b) {
        //Base cases
        if (a.compareTo(b) == 0) return a;
        if (b.compareTo(BigInteger.ZERO) == 0 ) return a;
        return gcd(b,a.mod(b));
    }
}
