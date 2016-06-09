package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.ExtraFileDAO;
import com.bsu.mvt.server.core.entity.EntityHelper;
import com.bsu.mvt.server.core.entity.ExtraFileEntity;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.ExtraFileService;
import com.bsu.mvt.server.rest.model.ExtraFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("extraFileService")
public class ExtraFileServiceImpl extends BaseServiceImpl implements ExtraFileService {

    @Autowired
    @Qualifier("extraFileDAO")
    private ExtraFileDAO extraFileDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ExtraFile createExtraFile(ExtraFile extraFile) throws MVTException {
        validateExtra("ExtraFileService.createExtraFile", extraFile);

        String filePath = getBasePath() + extraFile.getPath();
        validateFile("FileService.createVideoFile: wrong file", filePath, extraFile.getHash());


        ExtraFileEntity e = EntityHelper.convert2ExtraFile(extraFile);

        Long id = extraFileDAO.createExtraFile(e);
        e.setId(id);

        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public ExtraFile readExtraFile(Long id) {
        ExtraFileEntity extraFileEntity = extraFileDAO.readExtraFile(id);
        if (extraFileEntity == null) {
            return null;
        }
        return EntityHelper.convert(extraFileEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ExtraFile updateExtraFile(ExtraFile extraFile) {
        validateExtra("ExtraFileService.updateExtraFile", extraFile);

        String filePath = getBasePath() + extraFile.getPath();
        validateFile("FileService.createVideoFile: wrong file", filePath, extraFile.getHash());

        ExtraFileEntity extraFileEntity = EntityHelper.convert2ExtraFile(extraFile);
        boolean b = extraFileDAO.updateExtraFile(extraFileEntity);
        if (b) {
            return EntityHelper.convert(extraFileEntity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ExtraFile deleteExtraFile(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean deleteExtraFileBySample(Long sampleId) {
        return extraFileDAO.deleteExtraFileBySample(sampleId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<ExtraFile> listExtraFile(Long sampleId) {
        List<ExtraFileEntity> list;
        if (sampleId == null) {
            list = extraFileDAO.listExtraFile();
        } else {
            list = extraFileDAO.listExtraFile(sampleId);
        }

        if (list == null) {
            return null;
        }
        List<ExtraFile> res = new ArrayList<>(list.size());
        for (ExtraFileEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

   
}
