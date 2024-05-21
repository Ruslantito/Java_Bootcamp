package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messagesInRoom;

    public Chatroom(Long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
    public Chatroom(Long id, String name, User owner, List<Message> messagesInRoom) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messagesInRoom = messagesInRoom;
    }

    public Long GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public User GetOwner() {
        return owner;
    }

    public List<Message> GetMessagesInRoom() {
        return messagesInRoom;
    }

    public void SetId(Long id) {
        this.id = id;
    }
    public void SetName(String name) {
        this.name = name;
    }
    public void SetOwner(User owner) {
        this.owner = owner;
    }
    public void SetMessagesInRoom(List<Message> messagesInRoom) {
        this.messagesInRoom = messagesInRoom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Chatroom other = (Chatroom) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.owner == null) ? (other.owner != null) : !this.owner.equals(other.owner)) {
            return false;
        }
        if ((this.messagesInRoom == null) ? (other.messagesInRoom != null) : !this.messagesInRoom.equals(other.messagesInRoom)) {
            return false;
        }

        return true;
    } 
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Long.valueOf(this.id).intValue();
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.owner != null ? this.owner.hashCode() : 0);
        hash = 53 * hash + (this.messagesInRoom != null ? this.messagesInRoom.hashCode() : 0);

        return hash;
    }
    
    @Override
    public String toString() {
        return "{id=" + this.id + ",name=\"" + this.name + "\",creator=" + this.owner + ",messages=" + this.messagesInRoom + "}";
    }

}
