package com.h2rd.refactoring.usermanagement.dao;

import java.util.ArrayList;

import com.h2rd.refactoring.usermanagement.User;

/**
 *
 * <p>This interface provide the get, save, update and delete user
 *  function. It's easy for sub-class to change implementation strategy 
 *  based on different data source. <p>
 *
 * @author
 * @version 1.0
 */
public interface UserDao {

    void saveUser(User user);

    ArrayList<User> getUsers();

    void deleteUser(User userToDelete);

    void updateUser(User userToUpdate);

    User findUser(String email);
}
