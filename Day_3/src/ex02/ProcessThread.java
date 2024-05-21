package ex02;

public class ProcessThread extends Thread {
    int threadNum;
    int[] arr;
    int from;
    int to;

    ProcessThread(int[] arr, int threadNum, int from, int to) {
        this.threadNum = threadNum;
        this.arr = arr;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        int res = 0;
        for(int i = from; i < to; ++i) {
            res += arr[i];
        }
        System.out.println("Thread " + (threadNum + 1) + ": from " + from + " to " + (to - 1) + " sum is " + res);
        Program.totalSum += res;
    }
}
