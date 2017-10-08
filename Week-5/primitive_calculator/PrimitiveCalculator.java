import java.util.*;

public class PrimitiveCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] sequence = optimal_sequence(n);
        // As on doing every operation we get the next number. So, the number of elements in the sequence
        // will give the no. of operations.
        System.out.println(sequence.length - 1);
        for (int x : sequence) {
            System.out.print(x + " ");
        }
    }

    private static int[] optimal_sequence(int n) {
        // This array's index 'i' will be the number we want to achieve
        // and a[i] is the number of operations done on starting number 1 to get this value 'i'
        int[] operationsArray = new int[n+1];
        operationsArray[0] = 0;
        operationsArray[1] = 0;
        for (int i = 2; i<=n; i++) {
            operationsArray[i] = getMinimum(operationsArray[i-1] + 1, i%3 ==0 ? operationsArray[i/3] +1:Integer.MAX_VALUE,
                    i%2 == 0 ? operationsArray[i/2] +1 :Integer.MAX_VALUE);
        }

        // Last value of operationsArray gives the minimum no. of operations done on 1 to get n.
        int numberOfOperations= operationsArray[n];
        // Now, we have to find the intermediate sequence of numbers.
        int[] sequence = new int[numberOfOperations+1];
        sequence[0] = 1;
        sequence[numberOfOperations] = n;
        // We have to now fill the remaining numbers in this sequence between 1 and n
        int key ;
        int index = operationsArray[n]-1;
        while (index >= 1) {
            key = sequence[index+1];
            if (operationsArray[key-1]+1 == operationsArray[key]) {
                sequence[index] = key-1;
            } else if (key%2 == 0 && operationsArray[key/2]+1 == operationsArray[key]) {
                sequence[index]= key/2;
            } else if (key%3 == 0 && operationsArray[key/3]+1 == operationsArray[key]) {
                sequence[index] = key/3;
            }
            index--;
        }
        return sequence;
    }


    private static int getMinimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    /*
     Greedy way
      */
//    private static List<Integer> optimal_sequence(int n) {
//        List<Integer> sequence = new ArrayList<Integer>();
//        while (n >= 1) {
//            sequence.add(n);
//            if (n % 3 == 0) {
//                n /= 3;
//            } else if (n % 2 == 0) {
//                n /= 2;
//            } else {
//                n -= 1;
//            }
//        }
//        Collections.reverse(sequence);
//        return sequence;
//    }
}

