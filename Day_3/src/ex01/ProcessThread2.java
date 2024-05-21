package ex01;

public class ProcessThread2 extends Thread {
    String str;
    int step = 0;

    ProcessThread2(String str, int step) {    
        this.step = step;
        this.str = str;
    }

    @Override
    public void run() {
        try {
            Program.prod.cons(str, step);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
