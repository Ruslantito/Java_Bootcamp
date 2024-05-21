package edu.school21.chat.models;

import java.util.List;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializesRooms;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializesRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socializesRooms = socializesRooms;
    }

    public Long GetId() {
        return id;
    }
    public String GetLogin() {
        return login;
    }
    public String GetPassword() {
        return password;
    }
    public List<Chatroom> GetCreatedRooms() {
        return createdRooms;
    }
    public List<Chatroom> GetSocializesRooms() {
        return socializesRooms;
    }

    public void SetId(Long id) {
        this.id = id;
    }
    public void SetLogin(String login) {
        this.login = login;
    }
    public void SetPassword(String password) {
        this.password = password;
    }
    public void SetCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }
    public void SetSocializesRooms(List<Chatroom> socializesRooms) {
        this.socializesRooms = socializesRooms;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final User other = (User) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.createdRooms == null) ? (other.createdRooms != null) : !this.createdRooms.equals(other.createdRooms)) {
            return false;
        }
        if ((this.socializesRooms == null) ? (other.socializesRooms != null) : !this.socializesRooms.equals(other.socializesRooms)) {
            return false;
        }

        return true;
    } 

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Long.valueOf(this.id).intValue();
        hash = 53 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 53 * hash + (this.createdRooms != null ? this.createdRooms.hashCode() : 0);
        hash = 53 * hash + (this.socializesRooms != null ? this.socializesRooms.hashCode() : 0);

        return hash;
    }
    
    @Override
    public String toString() {
        return "{id=" + this.id + ",login=\"" + this.login + "\",password=\"" + this.password + "\",createdRooms=" + this.createdRooms + ",rooms=" + this.socializesRooms + "}";
    }

}
