package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.UserDAO;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User createUser(User user) throws MVTException {
        throw new NotImplementedException();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public User readUser(Long id) throws MVTException {
        UserEntity e = userDAO.readUser(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User updateUser(User user) throws MVTException {
        throw new NotImplementedException();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User deleteUser(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> listUser() throws MVTException {
        List<UserEntity> list;
        list = userDAO.listUser();
        List<User> res = new ArrayList<>(list.size());
        for (UserEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

}
