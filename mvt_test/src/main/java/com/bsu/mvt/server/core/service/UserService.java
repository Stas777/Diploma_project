package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.*;

import java.util.List;

public interface UserService {
    public User createUser(User user) throws MVTException;

    public User readUser(Long id) throws MVTException;

    public User updateUser(User user) throws MVTException;

    public User deleteUser(Long id);

    public List<User> listUser() throws MVTException;

}
