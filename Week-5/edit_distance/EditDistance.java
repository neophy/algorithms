import java.util.*;

class EditDistance {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

  public static int EditDistance(String s, String t) {
    int s_length = s.length();
    int t_length = t.length();
    int [][] distance = new int[s_length+1][t_length+1];
    for (int i=0; i<=t_length; i++) {
      distance[0][i] = i;
    }
    for (int i=0; i<=s_length; i++) {
      distance[i][0] = i;
    }
    for (int i = 1; i<=s_length; i++) {
      for (int j = 1; j<=t_length; j++) {
        if (s.charAt(i-1) == t.charAt(j-1)) {
          distance[i][j] = getMinimum(distance[i][j-1] + 1, distance[i-1][j] + 1, distance[i-1][j-1]);
        } else {
          distance[i][j] = getMinimum(distance[i][j-1] + 1, distance[i-1][j] + 1, distance[i-1][j-1] +1);
        }
      }
    }
    return  distance[s_length][t_length];
  }

  private static int getMinimum(int a, int b, int c) {
    return Math.min(Math.min(a,b),c);
  }
}
