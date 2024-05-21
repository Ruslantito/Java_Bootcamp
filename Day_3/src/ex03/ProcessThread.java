package ex03;

public class ProcessThread extends Thread {
    int threadNum;

    ProcessThread(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        while(!Program.arrFromFile.isEmpty() && Program.finish) {
            try {
                String[] arrData = Program.arrFromFile.poll();
                System.out.println("Thread-" + (threadNum + 1) + " start download file number " + arrData[0]);
                Thread.sleep(50);
                System.out.println("Thread-" + (threadNum + 1) + " finish download file number " + arrData[0]); 
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }            
    }
}
