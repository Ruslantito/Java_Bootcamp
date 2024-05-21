package edu.school21.chat.app;

import edu.school21.chat.repositories.*;
import edu.school21.chat.models.*;
import java.util.Scanner; 
import java.util.Optional;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Program {
    public static void main( String[] args ) {
        System.out.println();
        System.out.println("----- save message: wrong userId and wrong roomId -----");
        saveMessage(8L, 7L);

        System.out.println();
        System.out.println("----- save message: correct userId and wrong roomId -----");
        saveMessage(1L, 7L);

        System.out.println();
        System.out.println("----- save message: wrong userId and correct roomId -----");
        saveMessage(8L, 1L);

        System.out.println();
        System.out.println("----- save message: correct userId and correct roomId -----");
        saveMessage(1L, 1L);
    }

    private static void saveMessage(Long userId, Long roomId) {
        try{
            User creator = new User(userId, "user", "user", new ArrayList(), new ArrayList());
            User author = creator;
            Chatroom room = new Chatroom(roomId, "room", creator, new ArrayList());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl();
            messagesRepository.save(message);
            System.out.println(message.GetId());
        } catch (NotSavedSubEntityException e) {
            System.err.println(e);
        }
    }
}
