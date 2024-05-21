package edu.school21.printer.app;

import java.io.IOException;
import edu.school21.printer.logic.Process;

public class Program {
    public static void main(String[] args) throws IOException{    
        if(args.length == 2) {
            Process process = new Process();
            process.Read(args[0], args[1]);
        } else {
            System.out.println("Interrupted: Incorrect number of arguments");
            System.exit(-1);
        }
    }
}
