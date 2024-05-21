package edu.school21.printer.app;

import java.io.IOException;
import edu.school21.printer.logic.Process;
import edu.school21.printer.logic.Arguments;
import com.beust.jcommander.*;

public class Program {
    public static void main(String[] args) throws IOException{    
        if(args.length == 2) {
            Arguments argmnts = new Arguments();
            JCommander.newBuilder()
                    .addObject(argmnts)
                    .build()
                    .parse(args);
            Process process = new Process();
            process.Read(argmnts.GetWhite(), argmnts.GetBlack());
        } else {
            System.out.println("Interrupted: Incorrect number of arguments");
            System.exit(-1);
        }
    }    
}
