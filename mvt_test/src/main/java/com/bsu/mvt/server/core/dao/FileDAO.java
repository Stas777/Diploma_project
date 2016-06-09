package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FileDAO {
    public VideoFileEntity readVideoFile(Long id) throws DataAccessException;

    public Long createVideoFile(VideoFileEntity e) throws DataAccessException;

    public boolean updateVideoFile(VideoFileEntity e) throws DataAccessException;

    public List<VideoFileEntity> listVideoFile() throws DataAccessException;

    public List<VideoFileEntity> listVideoFile(Long sample_id) throws DataAccessException;

    public boolean deleteVideoFileBySample(Long sample_id) throws DataAccessException;

    public SensorDataFileEntity readSensorDataFile(Long id) throws DataAccessException;

    public Long createSensorDataFile(SensorDataFileEntity e) throws DataAccessException;

    public boolean updateSensorDataFile(SensorDataFileEntity e) throws DataAccessException;

    public List<SensorDataFileEntity> listSensorDataFile() throws DataAccessException;

    public List<SensorDataFileEntity> listSensorDataFile(Long sample_id) throws DataAccessException;

    public boolean deleteSensorDataFileBySample(Long sample_id) throws DataAccessException;

}
