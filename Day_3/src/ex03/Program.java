package ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class Program {
    static Queue<String[]> arrFromFile = new LinkedList<>();
    static boolean finish = false;
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
        checkParameters(args);
        int threadsCount = getParametersNumber(args[0].toString());
        Path filePath = Paths.get("ex03/files_urls.txt");
        CreateNewArr(filePath.toAbsolutePath().toString());

        ProcessThread[] threadArr = new ProcessThread[threadsCount];
        for (int i = 0; i < threadsCount; ++i) {    
            ProcessThread thread = new ProcessThread(i);
            threadArr[i] = thread;
        }
        for (ProcessThread thread : threadArr) {  
            thread.start();
        }
        for (ProcessThread thread : threadArr) {  
            thread.join();
        }
    } 

    private static void CreateNewArr(String fileName) throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] str = new String[2];
            while ((line = reader.readLine()) != null) {
                str = line.split(" ");
                arrFromFile.add(str);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Warning!!!\n" + e);
        }
        finish = true;
    }

    private static void checkParameters(String[] args) {
        if(args.length != 1) {
            printNotCorrectInfos();
            System.exit(-1);
        }
        if(!args[0].toString().startsWith("--threadsCount=")) {
            printNotCorrectInfos();
            System.exit(-1);
        }
    }

    private static void printNotCorrectInfos() {
        System.out.println("Please enter correct informations!");
    }

    private static int getParametersNumber(String data) {
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
