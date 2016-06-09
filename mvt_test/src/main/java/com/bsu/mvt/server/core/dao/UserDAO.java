package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.UserEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDAO {
    public UserEntity readUser(Long id) throws DataAccessException;

    public UserEntity readUser(String email) throws DataAccessException;

    public Long createUser(UserEntity e) throws DataAccessException;

    public boolean updateUser(UserEntity e) throws DataAccessException;

    public List<UserEntity> listUser() throws DataAccessException;
}
