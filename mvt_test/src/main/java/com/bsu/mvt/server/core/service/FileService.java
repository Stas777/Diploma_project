package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.*;

import java.util.List;

public interface FileService {
    // video file
    public VideoFile createVideoFile(VideoFile activity) throws MVTException;

    public VideoFile readVideoFile(Long id);

    public VideoFile updateVideoFile(VideoFile activity) throws MVTException;

    public VideoFile deleteVideoFile(Long id);

    public boolean deleteVideoFileBySample(Long sampleId);

    public List<VideoFile> listVideoFile(Long sampleId);

    // sensor data file
    public SensorDataFile createSensorDataFile(SensorDataFile activity) throws MVTException;

    public SensorDataFile readSensorDataFile(Long id);

    public SensorDataFile updateSensorDataFile(SensorDataFile activity) throws MVTException;

    public SensorDataFile deleteSensorDataFile(Long id);

    public boolean deleteSensorDataFileBySample(Long sampleId);

    public List<SensorDataFile> listSensorDataFile(Long sampleId);
}
