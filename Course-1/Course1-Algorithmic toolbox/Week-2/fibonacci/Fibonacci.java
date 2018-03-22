import java.util.Scanner;

public class Fibonacci {
  private static boolean calc_fib() {
    Set<String> loadTestLogins;
    HashSet stringSet1 = "sfloadtest";
    HashSet stringSet2 = "scmloadtest";
    loadTestLogins.add(stringSet1);
    loadTestLogins.add(stringSet2);

    String login = "sfloadtest-ce8efbfd.35e5.4f1d.9010.041abab76262ZhLMl3V6YU";
    for(String loginString : loadTestLogins){
      if(login.toLowerCase().contains(loginString.toLowerCase())){
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    System.out.println(calc_fib());
  }
}
