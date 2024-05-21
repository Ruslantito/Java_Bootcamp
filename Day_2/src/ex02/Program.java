package ex02;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException{
        if(args.length == 1) {
            Process process = new Process();            
            process.ReadAll(args);
        }
    } 
}
