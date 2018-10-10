package com.h2rd.refactoring.usermanagement.service;

import java.util.ArrayList;

import com.h2rd.refactoring.usermanagement.User;

/**
*
* <p>This interface provide the get, save, update and delete user
*  function. It's easy for sub-class to change service logic strategy 
*  based on different criteria. <p>
*
* @author
* @version 1.0
*/
public interface UserService {

    int saveUser(User user);

    ArrayList<User> getUsers();

    int deleteUser(User userToDelete);

    int updateUser(User userToUpdate);

    User findUser(String email);
}
