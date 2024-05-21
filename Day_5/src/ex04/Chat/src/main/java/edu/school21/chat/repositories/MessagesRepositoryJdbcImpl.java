package edu.school21.chat.repositories;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
                int messageId = res.getInt(1);
                int authorId = res.getInt(2);
                int chatroomId = res.getInt(3);
                String messageText = res.getString(4);
                LocalDateTime messageDateTime = res.getTimestamp(5).toLocalDateTime();
                User userFromBD = FindUserById((long) authorId);
                Chatroom chatroomFromDB = FindChatroomById((long) chatroomId);
                Message messageTmp = new Message((long)messageId, userFromBD, chatroomFromDB, messageText, messageDateTime);
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
        int chatroomId = 0;
        String chatRoomName = "";
        User chatroomOwner = null;        

        String query = "SELECT * FROM chatrooms WHERE id = " + id;            
        try(Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query)) {
            if (res.next()) {
                chatroomId = res.getInt(1);
                chatRoomName = res.getString(2);                   
            }
        } catch (SQLException  e) {
            System.err.println(e);
        } 

        Chatroom result = new Chatroom((long)chatroomId, chatRoomName, chatroomOwner);
        return result;
    }

    @Override
    public void save(Message message) {
        String query = "insert into Messages (author, room, text) values (";
        query += message.GetAuthor().GetId() + ", ";
        query += message.GetRoom().GetId() + ", ";
        query += "'" + message.GetText() + "'";
        query += ") returning id";

        try(Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query)) {            
            if (res.next()) {
                Long returnedId = res.getLong(1);
                message.SetId(returnedId);
            }
        } catch(SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }

    @Override
    public void update(Message message) {
        String query = "update Messages set ";
        query += "author = " + message.GetAuthor().GetId() + ", ";
        query += "room = " + message.GetRoom().GetId() + ", ";
        query += "text = '" + message.GetText() + "', ";
        query += "datetime = '" + message.GetDateTime() + "' ";
        query += "where id = " + message.GetId();

        try(Statement stm = conn.createStatement()) {
            int res = stm.executeUpdate(query);
            if (res > 0) {
                System.out.println("The records were updated: " + res);
            }
        } catch(SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        } 
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> usersResult = new ArrayList<>();
        List<Chatroom> chatroomList = new ArrayList<>();
        String query = creatQuery(page, size);
        try(Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query)) {
            while(res.next()) {
                int userId = res.getInt(1);
                String userLogin = res.getString(2);
                String userPassword = res.getString(3);
                int roomId = res.getInt(4);
                String roomName = res.getString(5);
                // int ownerId = res.getInt(6);
                int socializedIn = res.getInt(7);
                User userTmp = findUser(usersResult, (long)userId);
                if(userTmp == null) {
                    userTmp = new User((long)userId, userLogin, userPassword, new ArrayList(), new ArrayList());
                    usersResult.add(userTmp);
                }

                int index = usersResult.indexOf(userTmp);
                if(socializedIn == 1) {
                    Chatroom roomTmp = findRoom(chatroomList, (long)roomId);
                    if(userTmp == null) {
                        roomTmp = new Chatroom((long)roomId, roomName, findUser(usersResult, (long)userId), new ArrayList());
                    }
                    List<Chatroom> chatListTmp = usersResult.get(index).GetSocializesRooms();
                    chatListTmp.add(roomTmp);
                    usersResult.get(index).SetSocializesRooms(chatListTmp);
                } else {
                    Chatroom roomTmp = new Chatroom((long)roomId, roomName, userTmp, new ArrayList());
                    List<Chatroom> chatListTmp = usersResult.get(index).GetCreatedRooms();
                    chatListTmp.add(roomTmp);
                    usersResult.get(index).SetCreatedRooms(chatListTmp);
                    chatroomList.add(roomTmp);
                }
            }
        } catch (SQLException  e) {
            System.err.println(e);
        }
        return usersResult;
    }

    private String creatQuery(int page, int size) {
        int offset = ((page-1) * size);
        String query = "WITH selected_users AS (";
        query += "SELECT id as UserId, login, password FROM Users";
        query += "     ORDER BY id ";
        query += "     OFFSET " + offset + " LIMIT " + size + " ";
        query += " ), ";
        query += "     rooms_owner AS ( ";
        query += "         SELECT su.UserId as userId, su.login, su.password, cr.id as roomId, cr.name as roomName, cr.owner, 0 as socialized ";
        query += "         FROM selected_users su ";
        query += "             LEFT JOIN Chatrooms cr ON su.UserId = cr.owner ";
        query += " ), ";
        query += "     rooms_socilizedr AS ( ";
        query += "         SELECT distinct su.UserId as userId, su.login, su.password, cr.id as roomId, cr.name as roomName, cr.owner, 1 as socialized ";
        query += "         FROM selected_users su ";
        query += "             LEFT JOIN Messages m ON su.UserId = m.author ";
        query += "             LEFT JOIN Chatrooms cr ON su.UserId != cr.owner ";
        query += "         WHERE m.room = cr.id ";
        query += "     ) ";
        query += " SELECT * FROM rooms_socilizedr t1 ";
        query += " UNION ALL ";
        query += " SELECT * FROM rooms_owner t2 ";
        query += " ORDER BY socialized ";
        return query;
    }

    private User findUser(List<User> usersResult, Long userId) {
        for (User user : usersResult) {
            if(user.GetId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Chatroom findRoom(List<Chatroom> chatroomList, Long roomId) {
        for (Chatroom room : chatroomList) {
            if(room.GetId() == roomId) {
                return room;
            }
        }
        return null;
    }

}
