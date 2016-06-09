package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.ExtraFile;

import java.util.List;

public interface ExtraFileService {
    public ExtraFile createExtraFile(ExtraFile extraFile) throws MVTException;

    public ExtraFile readExtraFile(Long id);

    public ExtraFile updateExtraFile(ExtraFile extraFile);

    public ExtraFile deleteExtraFile(Long id);

    boolean deleteExtraFileBySample(Long sampleId);

    public List<ExtraFile> listExtraFile(Long sampleId);


}
