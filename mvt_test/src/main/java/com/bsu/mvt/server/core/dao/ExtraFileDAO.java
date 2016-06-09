package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.ExtraFileEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ExtraFileDAO {
    public ExtraFileEntity readExtraFile(Long id) throws DataAccessException;

    public Long createExtraFile(ExtraFileEntity e) throws DataAccessException;

    public boolean updateExtraFile(ExtraFileEntity e) throws DataAccessException;

    public List<ExtraFileEntity> listExtraFile() throws DataAccessException;

    public List<ExtraFileEntity> listExtraFile(Long sample_id) throws DataAccessException;

    public boolean deleteExtraFileBySample(Long sample_id) throws DataAccessException;
}
