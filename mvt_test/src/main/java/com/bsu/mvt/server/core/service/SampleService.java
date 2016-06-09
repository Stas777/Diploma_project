package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.Sample;

import java.util.List;

public interface SampleService {
    // sample
    public Sample createSample(Sample sample) throws MVTException;

    public Sample readSample(Long id) throws MVTException;

    public Sample updateSample(Sample sample) throws MVTException;

    public Sample deleteSample(Long id);

    public List<Sample> listSample(Long sportId, Boolean skipActivity) throws MVTException;
}
