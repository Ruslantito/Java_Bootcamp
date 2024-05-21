package edu.school21.services;

import edu.school21.exceptions.*;
import edu.school21.repositories.*;
import edu.school21.models.User;

public class UsersServiceImpl {

    private UsersRepository userRep;

    public UsersServiceImpl(UsersRepository userRep) {
        this.userRep = userRep;
    }
    public boolean authenticate(String login, String password) {
        boolean status = false;
        User user = userRep.findByLogin(login);
        if(user.GetAuthSuccStatus()) {
            throw new AlreadyAuthenticatedException("User allready Authenticated!");
        }
        String pass = user.GetPassword();
        if(pass.equals(password)) {
            status = true;
            userRep.update(user);
        }
        return status;
    }
}
