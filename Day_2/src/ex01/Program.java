package ex01;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        if(args.length == 2) {
            Process process = new Process();
            process.ReadAll(args[0], args[1]);
        } else {
            System.out.println("Something wrong, please try again!");
        }
    }
}
