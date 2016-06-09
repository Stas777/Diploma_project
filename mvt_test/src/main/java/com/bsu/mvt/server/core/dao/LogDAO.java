package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.LogEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface LogDAO {
    public LogEntity readLog(Long id) throws DataAccessException;

    public Long createLog(LogEntity e) throws DataAccessException;

    public List<LogEntity> listLog() throws DataAccessException;

    public List<LogEntity> listLogByUser(Long userId) throws DataAccessException;

    public List<LogEntity> listLogBySample(Long sampleId) throws DataAccessException;

    public List<LogEntity> listLogByUserAndSample(Long userId, Long sampleId) throws DataAccessException;
}
