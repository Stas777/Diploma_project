package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.Extra;

import java.util.List;

public interface ExtraService {
    // for sample
    public Extra createExtraSample(Extra extra) throws MVTException;

    public Extra readExtraSample(Long id);

    public Extra updateExtraSample(Extra extra);

    public Extra deleteExtraSample(Long id);

    boolean deleteExtraSampleBySample(Long sampleId);

    public List<Extra> listExtraSample(Long sampleId);

    // for activity
    public Extra createExtraActivity(Extra extra) throws MVTException;

    public Extra readExtraActivity(Long id);

    public Extra updateExtraActivity(Extra extra);

    public Extra deleteExtraActivity(Long id);

    boolean deleteExtraActivityByActivity(Long activityId);

    public List<Extra> listExtraActivity(Long activityId);
}
