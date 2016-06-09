package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.SampleEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SampleDAO {
    public SampleEntity readSample(Long id) throws DataAccessException;

    public Long createSample(SampleEntity e) throws DataAccessException;

    public boolean updateSample(SampleEntity e) throws DataAccessException;

    public List<SampleEntity> listSample() throws DataAccessException;

    public List<SampleEntity> listSample(Long sportId) throws DataAccessException;
}
