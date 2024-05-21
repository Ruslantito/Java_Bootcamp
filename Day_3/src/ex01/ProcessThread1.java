package ex01;

public class ProcessThread1 extends Thread {
    String str;
    int step = 0;

    ProcessThread1(String str, int step) {    
        this.step = step;
        this.str = str;
    }

    @Override
    public void run() {
        try {
            Program.prod.prod(str, step);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }
}
