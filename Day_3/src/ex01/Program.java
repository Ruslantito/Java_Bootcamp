package ex01;

public class Program {
    static ProdCons prod = new ProdCons();

    public static void main(String[] args) {
        String[] step = new String[3];
        if(args.length == 1 && args[0].toString().startsWith("--count=")) {
            step = args[0].toString().split("=");
            int tmp = -1;

            try {
                if(step.length == 2) {
                    tmp = Integer.valueOf(step[1]);
                } else {
                    printNotCorrectInfos();
                    System.exit(-1);
                }
            } catch (NumberFormatException ex) {
                printNotCorrectInfos();
                System.exit(-1);
            }            
            
            ProcessThread1 threadEgg = new ProcessThread1("Egg", tmp);
            ProcessThread2 threadHen = new ProcessThread2("Hen", tmp);

            threadEgg.start();
            threadHen.start();

            try {
                threadEgg.join();
                threadHen.join();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

        } else {
            printNotCorrectInfos();
        }
    } 

    private static void printNotCorrectInfos() {
        System.out.println("Please enter correct informations!");
    }
}
