package com.h2rd.refactoring.usermanagement.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.dao.UserDao;
import com.h2rd.refactoring.usermanagement.util.ErrorStatus;
import com.h2rd.refactoring.usermanagement.util.UserUtils;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    public ArrayList<User> getUsers() {
        return userDao.getUsers();
    }

    public int deleteUser(User userToDelete) {
        int i = ErrorStatus.NO_ERROR.getStatus();
        
        try {
                userDao.deleteUser(userToDelete);
            
        } catch (Exception e) {
            i = ErrorStatus.DELETE_USER_ERROR.getStatus();
            log.error(e);
        }
        
        return i;
    }

    public int updateUser(User userToUpdate) {
        int i = ErrorStatus.NO_ERROR.getStatus();
        
        try {
            i = UserUtils.validateUserEmail(userToUpdate);
            i = UserUtils.validateUserRoles(userToUpdate);
            if (i == ErrorStatus.NO_ERROR.getStatus()) {
                userDao.updateUser(userToUpdate);
            }
        } catch (Exception e) {
            i = ErrorStatus.UPDATE_USER_ERROR.getStatus();
            log.error(e);
        }

        return i;
    }

    public User findUser(String email) {
        User user = null;
        try {
            user = userDao.findUser(email);
        } catch (Exception e) {
            log.error(e);
        }
        return user;
    }

    public int saveUser(User user) {
        int i = ErrorStatus.NO_ERROR.getStatus();
        
        try {
            i = UserUtils.validateUserEmail(user);
            i = UserUtils.validateUserRoles(user);
            if (i == ErrorStatus.NO_ERROR.getStatus()) {
                User curUser = this.findUser(user.getEmail());
                log.debug("User: " + user);
                log.debug("User: " + curUser);
                // validate if the same email was registered for users
                if (curUser != null) {
                    i = ErrorStatus.EMAIL_UNIQUE_ERROR.getStatus();
                } else {
                    userDao.saveUser(user);
                }
            }
        } catch (Exception e) {
            i = ErrorStatus.SAVE_USER_ERROR.getStatus();
            log.error(e);
        }
        log.debug("i: " + i);
        return i;
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
