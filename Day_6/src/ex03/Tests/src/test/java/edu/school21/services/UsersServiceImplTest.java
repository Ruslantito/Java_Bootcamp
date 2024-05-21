package edu.school21.services;

import static org.mockito.Mockito.*;
import edu.school21.repositories.*;
import edu.school21.models.*;
import edu.school21.exceptions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UsersServiceImplTest {
    final String LOGIN_GOOD = "login";
    final String LOGIN_WRONG = "--login--";
    final String PASSWORD_GOOD = "password";
    final String PASSWORD_WRONG = "--password--";
    final User USER_NOT_AUTH = new User(3L, LOGIN_GOOD, PASSWORD_GOOD, false);
    final User USER_AUTH = new User(3L, LOGIN_GOOD, PASSWORD_GOOD, true);


    //    private UsersRepository userRep;
    //    private UsersServiceImpl userService;
    @Mock
    private UsersRepository userRep;

    @InjectMocks
    private UsersServiceImpl userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        // // MockitoAnnotations.initMocks(this);
//        userRep = mock(UsersRepository.class);
//        userService = new UsersServiceImpl(userRep);
    }
    
    @Test
    void testAuthenticateGood() {
        doReturn(USER_NOT_AUTH).when(userRep).findByLogin(LOGIN_GOOD);
        assertTrue(userService.authenticate(LOGIN_GOOD, PASSWORD_GOOD));
    }
    @Test
    void testAuthenticateBadLogin() {
        doThrow(EntityNotFoundException.class).when(userRep).findByLogin(LOGIN_WRONG);
        assertThrows(
            EntityNotFoundException.class, ()->{
                userService.authenticate(LOGIN_WRONG, PASSWORD_GOOD);
            }    
        );
    }
    @Test
    void testAuthenticateBadPassword() {
        doReturn(USER_NOT_AUTH).when(userRep).findByLogin(LOGIN_GOOD);
        assertFalse(userService.authenticate(LOGIN_GOOD, PASSWORD_WRONG));
    }

    @Test
    void testAlreadyAuthenticatedException() {
        doReturn(USER_AUTH).when(userRep).findByLogin(LOGIN_GOOD);
        assertThrows(
            AlreadyAuthenticatedException.class, ()->{
                userService.authenticate(LOGIN_GOOD, PASSWORD_WRONG);
            }    
        );
    }

    @Test
    void authenticateUpdateException() {
        doReturn(USER_NOT_AUTH).when(userRep).findByLogin(LOGIN_GOOD);
        doThrow(EntityNotFoundException.class).when(userRep).update(USER_NOT_AUTH);
        assertThrows(
            EntityNotFoundException.class, () -> {
                userService.authenticate(LOGIN_GOOD, PASSWORD_GOOD);
            }
        );
    }

}
