package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.LogDAO;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.LogService;
import com.bsu.mvt.server.rest.model.Log;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl implements LogService {

    @Autowired
    @Qualifier("logDAO")
    private LogDAO logDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Log createLog(Log log) throws MVTException {
        LogEntity e = EntityHelper.convert(log);
        Long id = logDAO.createLog(e);
        e.setId(id);

        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Log readLog(Long id) throws MVTException {
        LogEntity e = logDAO.readLog(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Log> listLog() throws MVTException {
        List<LogEntity> list;
        list = logDAO.listLog();
        if (list == null) {
            return null;
        }
        List<Log> res = new ArrayList<>(list.size());
        for (LogEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Log> listLogByUser(Long userId) throws MVTException {
        List<LogEntity> list;
        list = logDAO.listLogByUser(userId);
        if (list == null) {
            return null;
        }
        List<Log> res = new ArrayList<>(list.size());
        for (LogEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Log> listLogBySample(Long sampleId) throws MVTException {
        List<LogEntity> list;
        list = logDAO.listLogBySample(sampleId);
        if (list == null) {
            return null;
        }
        List<Log> res = new ArrayList<>(list.size());
        for (LogEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Log> listLogByUserAndSample(Long userId, Long sampleId) throws MVTException {
        List<LogEntity> list;
        list = logDAO.listLogByUserAndSample(userId, sampleId);
        if (list == null) {
            return null;
        }
        List<Log> res = new ArrayList<>(list.size());
        for (LogEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }
}
