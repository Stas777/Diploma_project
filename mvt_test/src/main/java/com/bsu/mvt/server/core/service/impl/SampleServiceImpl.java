package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.SampleDAO;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("sampleService")
public class SampleServiceImpl extends BaseServiceImpl implements SampleService {

    @Autowired
    @Qualifier("sampleDAO")
    private SampleDAO sampleDAO;


    @Autowired
    private ActivityService activityService;

    @Autowired
    private ExtraService extraService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ExtraFileService extraFileService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Sample createSample(Sample sample) throws MVTException {
        validateSample("SampleService.createSample", sample);
/*
        private VideoFile videoFile;
        private List<SensorDataFile> dataFileList;
        private List<Activity> activityList;
*/

        Sample s;
        // TODO: add transaction and save all
        SampleEntity se = EntityHelper.convert(sample);
        try {
            Long id = sampleDAO.createSample(se);
            se.setId(id);
            s = EntityHelper.convert(se);

            VideoFile videoFile = sample.getVideoFile();
            videoFile.setSample(s);
            VideoFile vf = fileService.createVideoFile(videoFile);
            s.setVideoFile(vf);

            List<SensorDataFile> dataFileList = sample.getDataFileList();
            List<SensorDataFile> resultDataFileList = new ArrayList<>(dataFileList.size());
            for (SensorDataFile sdf : dataFileList) {
                sdf.setSample(s);
                resultDataFileList.add(fileService.createSensorDataFile(sdf));
            }
            s.setDataFileList(resultDataFileList);

            List<Activity> activityList = sample.getActivityList();
            if (activityList != null) {
                List<Activity> resultActivityList = new ArrayList<>(activityList.size());
                for (Activity a : activityList) {
                    a.setSample(s);
                    resultActivityList.add(activityService.createActivity(a));
                }
                s.setActivityList(resultActivityList);
            }

            List<Extra> extraList = sample.getExtraList();
            if (extraList != null) {
                List<Extra> resultExtraList = new ArrayList<>(extraList.size());
                for (Extra e : extraList) {
                    e.setSample(s);
                    resultExtraList.add(extraService.createExtraSample(e));
                }
                s.setExtraList(resultExtraList);
            }

            List<ExtraFile> extraFileList = sample.getExtraFileList();
            if (extraFileList != null) {
                List<ExtraFile> resultExtraFileList = new ArrayList<>(extraFileList.size());
                for (ExtraFile e : extraFileList) {
                    e.setSample(s);
                    resultExtraFileList.add(extraFileService.createExtraFile(e));
                }
                s.setExtraFileList(resultExtraFileList);
            }

        } catch (DuplicateKeyException e) {
            throw new MVTException(e, "CatalogService.createSample: duplicate sample name", MVTErrorConst.DB_DUPLICATE_VALUE);
        }
        return s;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Sample readSample(Long id) throws MVTException {
        SampleEntity e = sampleDAO.readSample(id);
        if (e == null) {
            return null;
        }
        return getFullSample(e, false);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Sample updateSample(Sample sample) throws MVTException {
        validateSample("SampleService.updateSample", sample);

        SampleEntity sampleEntity = EntityHelper.convert(sample);
        boolean b = sampleDAO.updateSample(sampleEntity);
        if (b) {
            Sample s = EntityHelper.convert(sampleEntity);
            VideoFile videoFile = sample.getVideoFile();
            if (videoFile != null) {
                if (videoFile.getId() == null) {
                    videoFile.setSample(s);
                    VideoFile vf = fileService.createVideoFile(videoFile);
                    s.setVideoFile(vf);
                } else {
                    videoFile.setSample(s);
                    VideoFile vf = fileService.updateVideoFile(videoFile);
                    s.setVideoFile(vf);
                }
            }
            fileService.deleteSensorDataFileBySample(sample.getId());
            List<SensorDataFile> dataFileList = sample.getDataFileList();
            List<SensorDataFile> resultDataFileList = new ArrayList<>(dataFileList.size());
            for (SensorDataFile sdf : dataFileList) {
                sdf.setSample(s);
                resultDataFileList.add(fileService.createSensorDataFile(sdf));
            }
            s.setDataFileList(resultDataFileList);


            activityService.deleteActivityBySample(sample.getId());
            List<Activity> activityList = sample.getActivityList();
            if (activityList != null) {
                List<Activity> resultActivityList = new ArrayList<>(activityList.size());
                for (Activity a : activityList) {
                    a.setSample(s);
                    resultActivityList.add(activityService.createActivity(a));
                }
                s.setActivityList(resultActivityList);
            }

            extraService.deleteExtraSampleBySample(sample.getId());
            List<Extra> extraList = sample.getExtraList();
            if (extraList != null) {
                List<Extra> resultExtraList = new ArrayList<>(extraList.size());
                for (Extra e : extraList) {
                    e.setSample(s);
                    resultExtraList.add(extraService.createExtraSample(e));
                }
                s.setExtraList(resultExtraList);
            }

            extraFileService.deleteExtraFileBySample(sample.getId());
            List<ExtraFile> extraFileList = sample.getExtraFileList();
            if (extraFileList != null) {
                List<ExtraFile> resultExtraFileList = new ArrayList<>(extraFileList.size());
                for (ExtraFile e : extraFileList) {
                    e.setSample(s);
                    resultExtraFileList.add(extraFileService.createExtraFile(e));
                }
                s.setExtraFileList(resultExtraFileList);
            }

            return s;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Sample deleteSample(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Sample> listSample(Long sportId, Boolean skipActivity) throws MVTException {
        List<SampleEntity> list;
        if (sportId == null) {
            list = sampleDAO.listSample();
        } else {
            list = sampleDAO.listSample(sportId);
        }
        if (list == null) {
            return null;
        }
        List<Sample> res = new ArrayList<>(list.size());
        for (SampleEntity e : list) {
            res.add(getFullSample(e, skipActivity));
        }
        return res;
    }

    private Sample getFullSample(SampleEntity e, Boolean skipActivity) throws MVTException {
        // fill sample with catalog bean & files
        Sample s = convertWithInnerFill(e);

        if (!skipActivity) {
            s.setActivityList(activityService.listActivity(s.getId()));
        }

        s.setExtraList(extraService.listExtraSample(s.getId()));

        s.setDataFileList(fileService.listSensorDataFile(s.getId()));
        List<VideoFile> vfl = fileService.listVideoFile(s.getId());
        if (vfl != null) {
            if (vfl.size() == 1) {
                s.setVideoFile(vfl.get(0));
            } else {
                throw new MVTException("SampleService.listSample: multiple video", MVTErrorConst.ERROR,
                        new ValidationErrorBean("sample", "Sample with id = '%1' has multiple video files",
                                Arrays.asList((Object) s.getId())));
            }
        }

        s.setExtraFileList(extraFileService.listExtraFile(s.getId()));
        //~fill sample with catalog bean & files
        return s;
    }

    private Sample convertWithInnerFill(SampleEntity e) {
        Sample b = EntityHelper.convert(e);
        // fill with reglament
        b.setReglament(getReglament(e.getReglament_id()));

        // fill with sport
        b.setSport(getSport(e.getSport_id()));

        return b;
    }
}
