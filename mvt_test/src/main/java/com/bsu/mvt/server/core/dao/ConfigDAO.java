package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.ConfigEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ConfigDAO {
    public List<ConfigEntity> listConfig() throws DataAccessException;
}
