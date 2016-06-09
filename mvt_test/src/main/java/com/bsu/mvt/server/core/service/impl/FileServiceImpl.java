package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.FileDAO;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.core.util.ValidationHelper;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("fileService")
public class FileServiceImpl extends BaseServiceImpl implements FileService {

    @Autowired
    @Qualifier("fileDAO")
    private FileDAO fileDAO;


    // video file
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public VideoFile createVideoFile(VideoFile file) throws MVTException {
        String filePath = getBasePath() + file.getPath();
        validateFile("FileService.createVideoFile: wrong file", filePath, file.getHash());

        VideoFileEntity e = EntityHelper.convert(file);
        Long id = fileDAO.createVideoFile(e);
        e.setId(id);

        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public VideoFile readVideoFile(Long id) {
        VideoFileEntity e = fileDAO.readVideoFile(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public VideoFile updateVideoFile(VideoFile file) throws MVTException {
        String filePath = getBasePath() + file.getPath();
        validateFile("FileService.updateVideoFile: wrong file", filePath, file.getHash());

        VideoFileEntity e = EntityHelper.convert(file);
        boolean b = fileDAO.updateVideoFile(e);
        if (b) {
            return EntityHelper.convert(e);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public VideoFile deleteVideoFile(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean deleteVideoFileBySample(Long sampleId) {
        return fileDAO.deleteVideoFileBySample(sampleId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<VideoFile> listVideoFile(Long sampleId) {
        List<VideoFileEntity> list;
        if (sampleId == null) {
            list = fileDAO.listVideoFile();
        } else {
            list = fileDAO.listVideoFile(sampleId);
        }

        if (list == null) {
            return null;
        }
        List<VideoFile> res = new ArrayList<>(list.size());
        for (VideoFileEntity e : list) {
            // NOTE: do not fill sample bean to avoid recursion
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    // sensor data file
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public SensorDataFile createSensorDataFile(SensorDataFile file) throws MVTException {
        String filePath = getBasePath() + file.getPath();
        validateFile("FileService.createSensorDataFile: wrong file", filePath, file.getHash());

        SensorDataFileEntity e = EntityHelper.convert(file);
        try {
            Long id = fileDAO.createSensorDataFile(e);
            e.setId(id);
        } catch (DuplicateKeyException ex) {
            ValidationErrorBean b = ValidationHelper.validateDatabaseDuplicateKey(ex.getMessage(), "sample_id");
            throw new MVTException(ex, "CatalogService.createSport: duplicate entity", MVTErrorConst.DB_DUPLICATE_VALUE, b);
        }

        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public SensorDataFile readSensorDataFile(Long id) {
        SensorDataFileEntity e = fileDAO.readSensorDataFile(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public SensorDataFile updateSensorDataFile(SensorDataFile file) throws MVTException {
        String filePath = getBasePath() + file.getPath();
        validateFile("FileService.createSensorDataFile: wrong file", filePath, file.getHash());

        SensorDataFileEntity e = EntityHelper.convert(file);
        boolean b = fileDAO.updateSensorDataFile(e);
        if (b) {
            return EntityHelper.convert(e);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public SensorDataFile deleteSensorDataFile(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean deleteSensorDataFileBySample(Long sampleId) {
        return fileDAO.deleteSensorDataFileBySample(sampleId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<SensorDataFile> listSensorDataFile(Long sampleId) {
        List<SensorDataFileEntity> list;
        if (sampleId == null) {
            list = fileDAO.listSensorDataFile();
        } else {
            list = fileDAO.listSensorDataFile(sampleId);
        }
        if (list == null) {
            return null;
        }
        List<SensorDataFile> res = new ArrayList<>(list.size());
        for (SensorDataFileEntity e : list) {
            res.add(convertWithInnerFill(e));
        }
        return res;
    }

    private SensorDataFile convertWithInnerFill(SensorDataFileEntity e) {
        // NOTE: do not fill sample bean to avoid recursion

        SensorDataFile b = EntityHelper.convert(e);
        // fill with qualifier key
        b.setQualifierKey(getQualifierKey(e.getQualifier_key_id()));

        return b;
    }
}
