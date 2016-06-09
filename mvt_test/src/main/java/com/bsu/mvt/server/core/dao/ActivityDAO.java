package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.ActivityEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ActivityDAO {
    public ActivityEntity readActivity(Long id) throws DataAccessException;

    public Long createActivity(ActivityEntity e) throws DataAccessException;

    public boolean updateActivity(ActivityEntity e) throws DataAccessException;

    public List<ActivityEntity> listActivity() throws DataAccessException;

    public List<ActivityEntity> listActivity(Long sample_id) throws DataAccessException;

    public boolean deleteActivityBySample(Long sample_id) throws DataAccessException;
}
