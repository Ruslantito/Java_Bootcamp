package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long GetId() {
        return id;
    }
    public User GetAuthor() {
        return author;
    }
    public Chatroom GetRoom() {
        return room;
    }
    public String GetText() {
        return text;
    }
    public LocalDateTime GetDateTime() {
        return dateTime;
    }

    public void SetId(Long id) {
        this.id = id;
    }
    public void SetAuthor(User author) {
        this.author = author;
    }
    public void SetRoom(Chatroom room) {
        this.room = room;
    }
    public void SetText(String text) {
        this.text = text;
    }
    public void SetDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Message other = (Message) obj;
        if ((this.author == null) ? (other.author != null) : !this.author.equals(other.author)) {
            return false;
        }
        if ((this.room == null) ? (other.room != null) : !this.room.equals(other.room)) {
            return false;
        }
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        if ((this.dateTime == null) ? (other.dateTime != null) : !this.dateTime.equals(other.dateTime)) {
            return false;
        }

        return true;
    } 
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Long.valueOf(this.id).intValue();
        hash = 53 * hash + (this.author != null ? this.author.hashCode() : 0);
        hash = 53 * hash + (this.room != null ? this.room.hashCode() : 0);
        hash = 53 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 53 * hash + (this.dateTime != null ? this.dateTime.hashCode() : 0);

        return hash;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Message : {\nid=" + this.id + ",\nauthor=" + this.author + ",\nroom=" + this.room + ",\ntext=\"" + this.text + "\",\ndateTime=\"" + this.dateTime.format(formatter) + "\n}";
    }

}
