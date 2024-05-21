package ex00;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        Scanner in = new Scanner(System.in);
        String fileName = new String();
        while(true) {
            fileName = in.nextLine();
            if(fileName.equals("42")) {
                break;
            }
            Process process = new Process();
            process.Read(fileName);
        }
        in.close();
    }
}
