package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.*;

public interface AuthService {
    public Session login(String login, String password) throws MVTException;

    public String logout(String uuid) throws MVTException;

    public boolean checkAuth(String uuid) throws MVTException;

    public Long getUserId(String uuid)throws MVTException;
}
