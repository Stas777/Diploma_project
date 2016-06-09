package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.*;

import java.util.List;

public interface LogService {
    public Log createLog(Log log) throws MVTException;

    public Log readLog(Long id) throws MVTException;

    public List<Log> listLog() throws MVTException;

    public List<Log> listLogByUser(Long userId) throws MVTException;

    public List<Log> listLogBySample(Long sampleId) throws MVTException;

    public List<Log> listLogByUserAndSample(Long userId, Long sampleId) throws MVTException;
}
