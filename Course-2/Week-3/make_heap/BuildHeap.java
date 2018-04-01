import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap>  swaps = new ArrayList<Swap>();

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap,
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      for (int i = 0; i < data.length; ++i) {
        for (int j = i + 1; j < data.length; ++j) {
          if (data[i] > data[j]) {
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
          }
        }
      }
    }

    // Input: 0-based array
    // Build a Min Heap (smaller elements at top)
    // Imp: We have to do swaps in the data array to sort it.
    //      We are not supposed to use extra memory for it.
    private void buildMinHeap() {
        int size = data.length;
        for (int i = size/2; i >= 0; i--) {
            siftDown(i, size);
        }
    }

    private void siftDown(int indexToBeSifted, int size) {
        int leftChildIndex = 2*indexToBeSifted+1;
        int rightChildIndex = 2*indexToBeSifted+2;
        if (rightChildIndex <= size-1 && data[indexToBeSifted] > data[rightChildIndex]) {
            swapNodes(indexToBeSifted, rightChildIndex);
            siftDown(rightChildIndex, size);
        }
        if (leftChildIndex <= size-1 && data[indexToBeSifted] > data[leftChildIndex]) {
            swapNodes(indexToBeSifted, leftChildIndex);
            siftDown(leftChildIndex, size);
        }
    }

    private void swapNodes(int currentIndex, int parentIndex) {
        int temp = data[currentIndex];
        data[currentIndex] = data[parentIndex];
        data[parentIndex] = temp;
        swaps.add(new Swap(currentIndex, parentIndex));
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
//        generateSwaps();
        buildMinHeap();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
