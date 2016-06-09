package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.UserDAO;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.core.util.*;
import com.bsu.mvt.server.rest.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("authService")
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {
    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    // uuid 2 login
    private TtlHashMap<String, Long> ttlHashMap = new TtlHashMap<String, Long>(TimeUnit.MINUTES, 60L);
    private HashMap<Long, String> id2uuid = new HashMap<>();

    @Override
    public Session login(String login, String password) throws MVTException {
        validateLoginPassword("AuthServiceImpl.login: wrong login or password", login, password);
        UserEntity e = userDAO.readUser(login);
        logger.debug("user - ", e.getFirst_name());

        // IgnoreCase is needed to have ability to add password to db in uppercase
        if (e != null && e.getPassword().equalsIgnoreCase(FileUtil.generateMD5Password(password))) {
            String uuid = id2uuid.get(e.getId());
            boolean expired = ttlHashMap.get(uuid) == null;
            if (uuid != null && !expired) {
                ttlHashMap.put(uuid, e.getId());
                id2uuid.put(e.getId(), uuid);
            } else {
                uuid = UUID.randomUUID().toString().replaceAll("-", "");
                ttlHashMap.put(uuid, e.getId());
                id2uuid.put(e.getId(), uuid);
            }
            Session s = new Session(uuid);
            s.setUser(EntityHelper.convert(e));
            return s;
        }
        throw new MVTException("AuthService.login: invalid pare login-password", MVTErrorConst.LOGIN_INVALID_OR_USER_NOT_FOUND);
    }

    @Override
    public String logout(String uuid) throws MVTException {
        ttlHashMap.remove(uuid);
        return uuid;
    }

    @Override
    public boolean checkAuth(String uuid) throws MVTException {
        Long userId = ttlHashMap.get(uuid);
        return userId != null;
    }

    @Override
    public Long getUserId(String uuid) throws MVTException {
        return ttlHashMap.get(uuid);
    }
}
