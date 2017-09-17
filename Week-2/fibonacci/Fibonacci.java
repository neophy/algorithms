import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
  	int[] resultArray=new int[n+1];
	resultArray[0]=0;
    if (n>=1){
        resultArray[1]=1;
    }
	if (n<=1) return n;
    	for (int i=2; i<n+1; i++) {
      		resultArray[i] = resultArray[i-1] + resultArray[i-2];
   	 }
    return resultArray[n];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
