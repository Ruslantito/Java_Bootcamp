package edu.school21.chat.app;

import edu.school21.chat.repositories.*;
import java.util.Scanner; 
import java.util.Optional;

public class Program {

    public static void main( String[] args ) {
        Scanner in = new Scanner(System.in);
        MessagesRepositoryJdbcImpl messageFromDBById = new MessagesRepositoryJdbcImpl();
        int inputId = 0;
        while(true) {
            try {
                System.out.println("Enter a message ID");
                inputId = in.nextInt();
                if(inputId == -1) break;
                Optional res = messageFromDBById.findById((long)inputId);
                System.out.println(res.get());
            } catch (Exception e) {
                System.err.println("Something wrong, please try again!");
                in.nextLine();
                inputId = 0;
            } 
        }
    }
    
}
