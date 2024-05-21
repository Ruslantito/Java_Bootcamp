package ex00;

public class Program {
    public static void main(String[] args) {
        String[] step = new String[2];
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
            
            ProcessThread threadEgg = new ProcessThread("Egg", tmp);
            ProcessThread threadHen = new ProcessThread("Hen", tmp);
            threadEgg.start();
            threadHen.start();

            try {
                threadEgg.join();
                threadHen.join();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

            for(int i = 0; i < tmp; ++i) {
                System.out.println("Human");
            }
        } else {
            printNotCorrectInfos();
        }
    }

    private static void printNotCorrectInfos() {
        System.out.println("Please enter correct informations!");
    }
}
