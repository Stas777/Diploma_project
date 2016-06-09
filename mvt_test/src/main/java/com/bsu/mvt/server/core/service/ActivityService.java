package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.Activity;

import java.util.List;

public interface ActivityService {
    // activity
    public Activity createActivity(Activity activity) throws MVTException;

    public Activity readActivity(Long id);

    public Activity updateActivity(Activity activity);

    public Activity deleteActivity(Long id);

    boolean deleteActivityBySample(Long sampleId);

    public List<Activity> listActivity(Long sampleId);
}
