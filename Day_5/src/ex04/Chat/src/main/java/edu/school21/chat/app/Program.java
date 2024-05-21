package edu.school21.chat.app;

import edu.school21.chat.repositories.*;
import edu.school21.chat.models.*;
import java.util.List;
import java.util.ArrayList;

public class Program {
    public static void main( String[] args ) {
        int page = 1;
        int size = 3;
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl();
                    
        try {
            List<User> usersResult = messagesRepository.findAll(page, size);
            System.out.println();
            System.out.println("Page #: " + page);
            printUser(usersResult);
            System.out.println();
        } catch (NotSavedSubEntityException e) {
            System.err.println(e);
        }   
    }

    private static void printUser(List<User> usersResult) {
        for (User user : usersResult) {
            System.out.println("-------------------------------------------");
            System.out.println(user.GetLogin() + "(id=" + user.GetId() + ")");

            System.out.print("Created rooms:    " );
            printRooms(user.GetCreatedRooms());

            System.out.print("Socializes rooms: " );
            printRooms(user.GetSocializesRooms());
        }
    }

    private static void printRooms(List<Chatroom> chatroomList) {
        int i = 0;
        for (Chatroom room : chatroomList) {
            if(room == null) {
                System.out.print("null(id=0)");
                break;
            }
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(room.GetName() + "(id=" + room.GetId() + ")");
            ++i;
        }
        System.out.println();
    }

}
