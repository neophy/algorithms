import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Note: This solution works fine but takes more time
public class JobQueue {
    private int numWorkers;
    // The Jobs array contains the time of each Job
    private int[] jobs;

    // Array which stores the sequence of workers/threads as they took the job
    private int[] assignedWorker;
    // Array which stores the start time as the worker/thread took the job
    private long[] startTime;

    // workers array sorted s.t highest priority worker/least endTime worker is on top
    private int[] workersHeap ;
    // array to store the end time of workers
    private long[] endTime ;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    // We will make a Min. Heap of the size of Jobs
    // The root node of this heap will be the worker who is next available for a Job
    // Priority is assigned to workers depending on the endTime of their jobs.
    // Worker whose end time is least is on the top.
    // Next Job is given to this worker => It's priority increases by the endTime of job take => Heapify again
    // such that worker with least end time comes on top
    private void assignJobsMySolution() {
       int currentJob = 0;
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        while(currentJob < jobs.length) {
            // A job is given to the root node worker in workersHeap
            int workerNumber = workersHeap[0];
            assignedWorker[currentJob] = workerNumber;
            startTime[currentJob] = endTime[0];
            endTime[0] += jobs[currentJob];
            currentJob ++;
            // IMP: We don't need to build heap each time (which takes O(n) time)
            // because the endTime array is already a heap. Now, when we change the priority/endTime
            // then we need to just heapify it (siftUp/siftDown)
            siftDown(0,endTime.length); // log(n) time
        }
    }

    // indexToBeShifted has its endTime increased so we need to put it to its correct place
    // such that minimum heap properties are satisfied i.e. node with least endTime should be at upper levels
    private void siftDown(int indexToBeSifted, int size) {
        long parent = endTime[indexToBeSifted];
        long leftChild = endTime[2*indexToBeSifted+1];
        long rightChild = endTime[2*indexToBeSifted+1];

        int leftChildIndex = 2*indexToBeSifted+1;
        int rightChildIndex =2*indexToBeSifted+2;

        if (leftChildIndex <= size-1 && leftChild < rightChild) {
            if(endTime[indexToBeSifted] == endTime[leftChildIndex] &&
                    workersHeap[indexToBeSifted] > workersHeap[leftChildIndex]) { // IMP: Was doing mistake of considering indexToBeShifted instead of
                                                                                 // workersHeap[indexToBeSifted]
                swapNodes(indexToBeSifted, leftChildIndex, workersHeap);
                siftDown(leftChildIndex, size);
            } else if (endTime[indexToBeSifted] > endTime[leftChildIndex]) {
                swapNodes(indexToBeSifted, leftChildIndex, endTime);
                swapNodes(indexToBeSifted, leftChildIndex, workersHeap);
                siftDown(leftChildIndex, size);
            }
        } else if (rightChildIndex <= size-1) {
              if (endTime[indexToBeSifted] == endTime[rightChildIndex] && workersHeap[indexToBeSifted] > workersHeap[rightChildIndex]) {
                // As smaller index thread gets priority
                swapNodes(indexToBeSifted, rightChildIndex, workersHeap);
                // endTime values is not swapped as both have same values
                siftDown(rightChildIndex, size);
            } else if (endTime[indexToBeSifted] > endTime[rightChildIndex]) {
                swapNodes(indexToBeSifted, rightChildIndex, endTime);
                swapNodes(indexToBeSifted, rightChildIndex, workersHeap);
                siftDown(rightChildIndex, size);
            }
        }
    }

    private long getMinimum(long a, long b) {
        return a <= b ? a : b;
    }

    private void swapNodes(int currentIndex, int parentIndex, long[] arr) {
        long temp = arr[currentIndex];
        arr[currentIndex] = arr[parentIndex];
        arr[parentIndex] = temp;
    }

    private void swapNodes(int currentIndex, int parentIndex, int [] arr) {
        int  temp = arr[currentIndex];
        arr[currentIndex] = arr[parentIndex];
        arr[parentIndex] = temp;
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
//        assignJobs();
        if (numWorkers >= jobs.length) {
            handleBaseCase();
        } else {
            buildBasicWorkersHeap();
            assignJobsMySolution();
        }
        writeResponse();
        out.close();

    }

    private void buildBasicWorkersHeap() {
        workersHeap = new int[numWorkers];
        endTime =  new long[numWorkers];

        // workersHeap stores the thread numbers like,
        // i --> Index of array workersHeap
        // workersHeap[i] --> Thread number present at index 'i'
        for(int i =0; i < numWorkers; i++) {
            workersHeap[i] = i;
            endTime[i] = 0;
        }
    }

    // If number of workers are >= no. of jobs then each worker will take one job and start doing it time 0
    private void handleBaseCase() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            assignedWorker[i] = i;
            startTime[i] = 0; //All workers start at 0 time
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
