package edu.school21.chat.app;

import edu.school21.chat.repositories.*;
import edu.school21.chat.models.*;
import java.util.Scanner; 
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main( String[] args ) {
        long messageId = 1;
        long userId = 1;
        long roomId = 2;
        String text = "lalalalalalala";
        try {
            User author = new User(userId, "user", "user", new ArrayList(), new ArrayList());
            Chatroom room = new Chatroom(roomId, "room", author, new ArrayList());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl();
            Optional<Message> messageOptional = messagesRepository.findById(messageId);

            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();

                message.SetAuthor(author);
                message.SetRoom(room);
                message.SetText(text);
                message.SetDateTime(null);
                
                messagesRepository.update(message);
            } else {
                System.out.println("A message for updating was not found!");
            }
        } catch (NotSavedSubEntityException e) {
            System.err.println(e);
        }
    }

}
