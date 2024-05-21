package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {
    static int totalSum = 0;
    
    public static void main(String[] args) throws InterruptedException {
        checkParameters(args);
        int arrSize = getNumber(args[0].toString());
        int threadsCount = getNumber(args[1].toString());
        int[] countersArr = ArrCountPartsSize(arrSize, threadsCount);
        int[] newRandomArr = ArrNewRandom(arrSize);
        ProcessThread[] threadArr = new ProcessThread[threadsCount];

        for (int i = 0; i < threadsCount; ++i) {    
            ProcessThread thread = new ProcessThread(newRandomArr, i, (i * countersArr[0]), (i * countersArr[0] + countersArr[i]));
            threadArr[i] = thread;
        }

        System.out.println("Sum: " + countArrSum(newRandomArr, arrSize));
        for (ProcessThread thread : threadArr) {  
            thread.start();
        }
        for (ProcessThread thread : threadArr) {  
            thread.join();
        }
        System.out.println("Sum by threads: " + totalSum);
    } 


    private static int[] ArrNewRandom(int arrSize) {
        int[] arr = new int[arrSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomByRange(-1000, 1000);
        }
        return arr;
    }

    public static int randomByRange(int from, int to) {
        Random random = new Random();
        int res = random.nextInt((to - from) + 1) + from;
        return res;
    }

    private static int countArrSum(int[] newRandomArr, int arraySize) {
        int res = 0;
        for(int i = 0; i < arraySize; ++i) {
            res += newRandomArr[i];
        }
        return res;
    }

    private static void checkParameters(String[] args) {
        if(args.length != 2) {
            printNotCorrectInfos();
            System.exit(-1);
        }
        if(!args[0].toString().startsWith("--arraySize=")) {
            printNotCorrectInfos();
            System.exit(-1);
        }
        if(!args[1].toString().startsWith("--threadsCount=")) {
            printNotCorrectInfos();
            System.exit(-1);
        }
    }

    private static int[] ArrCountPartsSize(int arrSize, int threadsCount) {
        int countParts = (int) Math.ceil((double)arrSize / threadsCount);
        int countLast  = (int) ((double)arrSize - countParts * (threadsCount-1));
        int[] countersArr = new int[threadsCount];
        Arrays.fill(countersArr, countParts);
        countersArr[threadsCount - 1] = countLast;
        return countersArr;
    }

    private static void printNotCorrectInfos() {
        System.out.println("Please enter correct informations!");
    }

    private static int getNumber(String data) {
        String[] separetedData = new String[3];
        separetedData = data.split("=");
        int res = -1;
        try {
            if(!data.endsWith("=") && separetedData.length == 2) {
                res = Integer.valueOf(separetedData[1]);
            } else {
                printNotCorrectInfos();
                System.exit(-1);
            }
        } catch (NumberFormatException ex) {
            printNotCorrectInfos();
            System.exit(-1);
        }   
        return res;
    }
}
