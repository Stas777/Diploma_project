package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ExtraActivityDAO {
    public ExtraActivityEntity readExtraActivity(Long id) throws DataAccessException;

    public Long createExtraActivity(ExtraActivityEntity e) throws DataAccessException;

    public boolean updateExtraActivity(ExtraActivityEntity e) throws DataAccessException;

    public List<ExtraActivityEntity> listExtraActivity() throws DataAccessException;

    public List<ExtraActivityEntity> listExtraActivity(Long sample_id) throws DataAccessException;

    public boolean deleteExtraActivityByActivity(Long sample_id) throws DataAccessException;
}
