package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.*;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.ExtraService;
import com.bsu.mvt.server.rest.model.Extra;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("extraService")
public class ExtraServiceImpl extends BaseServiceImpl implements ExtraService {

    @Autowired
    @Qualifier("extraSampleDAO")
    private ExtraSampleDAO extraSampleDAO;

    @Autowired
    @Qualifier("extraActivityDAO")
    private ExtraActivityDAO extraActivityDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Extra createExtraSample(Extra extraSample) throws MVTException {
        validateExtra("ExtraSampleService.createExtraSample", extraSample);

        ExtraSampleEntity ae = EntityHelper.convert2ExtraSample(extraSample);

        Long id = extraSampleDAO.createExtraSample(ae);
        ae.setId(id);

        return EntityHelper.convert(ae);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Extra readExtraSample(Long id) {
        ExtraSampleEntity extraSampleEntity = extraSampleDAO.readExtraSample(id);
        if (extraSampleEntity == null) {
            return null;
        }
        return EntityHelper.convert(extraSampleEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Extra updateExtraSample(Extra extraSample) {
        validateExtra("ExtraSampleService.updateExtraSample", extraSample);

        ExtraSampleEntity extraSampleEntity = EntityHelper.convert2ExtraSample(extraSample);
        boolean b = extraSampleDAO.updateExtraSample(extraSampleEntity);
        if (b) {
            return EntityHelper.convert(extraSampleEntity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Extra deleteExtraSample(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean deleteExtraSampleBySample(Long sampleId) {
        return extraSampleDAO.deleteExtraSampleBySample(sampleId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Extra> listExtraSample(Long sampleId) {
        List<ExtraSampleEntity> list;
        if (sampleId == null) {
            list = extraSampleDAO.listExtraSample();
        } else {
            list = extraSampleDAO.listExtraSample(sampleId);
        }

        if (list == null) {
            return null;
        }
        List<Extra> res = new ArrayList<>(list.size());
        for (ExtraSampleEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    // for activity

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Extra createExtraActivity(Extra extraActivity) throws MVTException {
        validateExtra("ExtraService.createExtraActivity", extraActivity);

        ExtraActivityEntity e = EntityHelper.convert2ExtraActivity(extraActivity);

        Long id = extraActivityDAO.createExtraActivity(e);
        e.setId(id);

        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Extra readExtraActivity(Long id) {
        ExtraActivityEntity e = extraActivityDAO.readExtraActivity(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Extra updateExtraActivity(Extra extraActivity) {
        validateExtra("ExtraService.updateExtraActivity", extraActivity);

        ExtraActivityEntity e = EntityHelper.convert2ExtraActivity(extraActivity);
        boolean b = extraActivityDAO.updateExtraActivity(e);
        if (b) {
            return EntityHelper.convert(e);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Extra deleteExtraActivity(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean deleteExtraActivityByActivity(Long activityId) {
        return extraActivityDAO.deleteExtraActivityByActivity(activityId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Extra> listExtraActivity(Long activityId) {
        List<ExtraActivityEntity> list;
        if (activityId == null) {
            list = extraActivityDAO.listExtraActivity();
        } else {
            list = extraActivityDAO.listExtraActivity(activityId);
        }

        if (list == null) {
            return null;
        }
        List<Extra> res = new ArrayList<>(list.size());
        for (ExtraActivityEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }
}
