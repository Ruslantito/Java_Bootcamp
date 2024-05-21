package ex00;

public class ProcessThread extends Thread {
    String str;
    int step = 0;

    ProcessThread(String str, int step) {
        this.step = step;
        this.str = str;
    }

    @Override
    public void run() {
        for (int i = 0; i < step; ++i) {
            System.out.println(str);
        }
    }
}
