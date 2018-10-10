package com.h2rd.refactoring.usermanagement.dao;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.h2rd.refactoring.usermanagement.User;

public class UserDaoImpl implements UserDao {

    public static Map<String, User> users = new ConcurrentHashMap<String, User>();

    public void saveUser(User user) {
        users.put(user.getEmail(), user);
    }

    public ArrayList<User> getUsers() {
    	if (users.size() > 0) {
    		return new ArrayList<User>(users.values());
    	} else {
    		return null;
    	}
    }

    public void deleteUser(User userToDelete) {
    	User user = users.get(userToDelete.getEmail());
        if (user != null) {
            users.remove(userToDelete.getEmail());
        }
    }

    public void updateUser(User userToUpdate) {
    	User user = users.get(userToUpdate.getEmail());
        if (user != null) {
            user.setEmail(userToUpdate.getEmail());
            user.setRoles(userToUpdate.getRoles());
        }
    }

    public User findUser(String email) {
        return users.get(email);
    }
}
