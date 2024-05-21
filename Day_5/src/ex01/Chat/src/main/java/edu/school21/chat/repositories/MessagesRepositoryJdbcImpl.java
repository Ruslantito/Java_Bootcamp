package edu.school21.chat.repositories;

import java.sql.*;
import java.util.Optional;
import java.time.LocalDateTime;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;
import java.time.format.DateTimeFormatter;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    
    private ConnectDB db;
    private Connection conn;

    public MessagesRepositoryJdbcImpl() {
        try {
            db = new ConnectDB();
            conn = db.getConnection();                
        } catch (Exception e) {
            System.err.println("WARNING!!! " + e);
            System.exit(-1);
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> result = Optional.empty();
        String query = "SELECT * FROM messages WHERE id = " + id;            
        try(Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query)) { 
            if (res.next()) {
                int MessageId = res.getInt(1);
                int authorId = res.getInt(2);
                int chatroomId = res.getInt(3);
                String messageText = res.getString(4);
                LocalDateTime messageDateTime = res.getTimestamp(5).toLocalDateTime();
                User userFromBD = FindUserById((long) authorId);
                Chatroom chatroomFromDB = FindChatroomById((long) chatroomId);
                Message messageTmp = new Message((long)MessageId, userFromBD, chatroomFromDB, messageText, messageDateTime);
                result = Optional.of(messageTmp);
            }
        } catch (SQLException  e) {
            System.err.println(e);
        }
        return result;
    }

    private User FindUserById(Long id) {
            int userId = 0;
            String login = "";
            String password = "";        
            String query = "SELECT * FROM users WHERE id = " + id;
        try(Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query)) {
            if (res.next()) {
                userId = res.getInt(1);
                login = res.getString(2);
                password = res.getString(3);
            }
        } catch (SQLException  e) {
            System.err.println(e);
        }
        User result = new User((long)userId, login, password);
        return result;
    }

    private Chatroom FindChatroomById(Long id) {
        int ChatroomId = 0;
        String chatRoomName = "";
        User chatroomOwner = null;        
        String query = "SELECT * FROM chatrooms WHERE id = " + id;
        try(Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query)) {
            if (res.next()) {
                ChatroomId = res.getInt(1);
                chatRoomName = res.getString(2);                   
            }
        } catch (SQLException  e) {
            System.err.println(e);
        }
        Chatroom result = new Chatroom((long)ChatroomId, chatRoomName, chatroomOwner);
        return result;
    }    
}
