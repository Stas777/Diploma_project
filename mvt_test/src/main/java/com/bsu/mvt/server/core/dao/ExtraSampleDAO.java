package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.ExtraSampleEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ExtraSampleDAO {
    public ExtraSampleEntity readExtraSample(Long id) throws DataAccessException;

    public Long createExtraSample(ExtraSampleEntity e) throws DataAccessException;

    public boolean updateExtraSample(ExtraSampleEntity e) throws DataAccessException;

    public List<ExtraSampleEntity> listExtraSample() throws DataAccessException;

    public List<ExtraSampleEntity> listExtraSample(Long sample_id) throws DataAccessException;

    public boolean deleteExtraSampleBySample(Long sample_id) throws DataAccessException;
}
