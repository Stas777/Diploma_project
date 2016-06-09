package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.ActivityDAO;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl implements ActivityService {
    @Autowired
    private ExtraService extraService;

    @Autowired
    @Qualifier("activityDAO")
    private ActivityDAO activityDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Activity createActivity(Activity activity) throws MVTException {
        Activity a;
        ActivityEntity ae = EntityHelper.convert(activity);
        try {
            Long id = activityDAO.createActivity(ae);
            ae.setId(id);
            a = EntityHelper.convert(ae);

            List<Extra> extraList = activity.getExtraList();
            if (extraList != null) {
                List<Extra> resultExtraList = new ArrayList<>(extraList.size());
                for (Extra e : extraList) {
                    e.setActivity(a);
                    resultExtraList.add(extraService.createExtraActivity(e));
                }
                a.setExtraList(resultExtraList);
            }

        } catch (DuplicateKeyException e) {
            throw new MVTException(e, "ActivityService.createActivity: duplicate activity name", MVTErrorConst.DB_DUPLICATE_VALUE);
        }

        return a;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Activity readActivity(Long id) {
        ActivityEntity activityEntity = activityDAO.readActivity(id);
        if (activityEntity == null) {
            return null;
        }
        Activity activity = EntityHelper.convert(activityEntity);
        activity.setExtraList(extraService.listExtraActivity(activity.getId()));
        return activity;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Activity updateActivity(Activity activity) {
        Activity a;
        ActivityEntity activityEntity = EntityHelper.convert(activity);
        boolean b = activityDAO.updateActivity(activityEntity);
        if (b) {
            a = EntityHelper.convert(activityEntity);

            extraService.deleteExtraActivityByActivity(a.getId());
            List<Extra> extraList = activity.getExtraList();
            if (extraList != null) {
                List<Extra> resultExtraList = new ArrayList<>(extraList.size());
                for (Extra e : extraList) {
                    e.setActivity(a);
                    resultExtraList.add(extraService.createExtraActivity(e));
                }
                a.setExtraList(resultExtraList);
            }
        } else {
            return null;
        }
        return a;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Activity deleteActivity(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean deleteActivityBySample(Long sampleId) {
        // extras is deleted by cascade trigger
        return activityDAO.deleteActivityBySample(sampleId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Activity> listActivity(Long sampleId) {
        List<ActivityEntity> list;
        if (sampleId == null) {
            list = activityDAO.listActivity();
        } else {
            list = activityDAO.listActivity(sampleId);
        }

        if (list == null) {
            return null;
        }
        List<Activity> res = new ArrayList<>(list.size());
        for (ActivityEntity e : list) {
            Activity a = convertWithInnerFill(e);
            a.setExtraList(extraService.listExtraActivity(a.getId()));
            res.add(a);
        }
        return res;
    }

    protected Activity convertWithInnerFill(ActivityEntity e) {
        Activity b = EntityHelper.convert(e);
        // fill with reglament
        b.setReglament(getReglament(e.getReglament_id()));

        // fill with motion type
        b.setMotionType(getMotionType(e.getMotion_type_id()));

        return b;
    }
}
