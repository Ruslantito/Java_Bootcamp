package edu.school21.models;

public class User {
    private Long id;
    private String login;
    private String password;
    private Boolean authSuccStatus;

    public User(Long id, String login, String password, Boolean authSuccStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authSuccStatus = authSuccStatus;
    }

    public Long GetId() {
        return id;
    }
    public String GetName() {
        return login;
    }
    public String GetPassword() {
        return password;
    }
    public Boolean GetAuthSuccStatus() {
        return authSuccStatus;
    }

    public void SetId(Long id) {
        this.id = id;
    }
    public void SetName(String login) {
        this.login = login;
    }
    public void SetPassword(String password) {
        this.password = password;
    }
    public void SetAuthSuccStatus(Boolean authSuccStatus) {
        this.authSuccStatus = authSuccStatus;
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
        if ((this.authSuccStatus == null) ? (other.authSuccStatus != null) : !this.authSuccStatus.equals(other.authSuccStatus)) {
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
        hash = 53 * hash + (this.authSuccStatus != null ? this.authSuccStatus.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return "Product : {\nid=" + this.id + ",\nname=" + this.login + ",\nprice=" + this.password + ",\nprice=" + this.authSuccStatus + "\n}";
    }

}
