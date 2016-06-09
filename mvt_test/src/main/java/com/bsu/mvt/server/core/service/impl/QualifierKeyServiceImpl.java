package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.QualifierKeyService;
import com.bsu.mvt.server.rest.model.QualifierKey;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service("qualifierKeyService")
public class QualifierKeyServiceImpl extends BaseServiceImpl implements QualifierKeyService {

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public QualifierKey createQualifierKey(QualifierKey qualifierKey) throws MVTException {
        validateName("CatalogService.createQualifierKey: wrong name", qualifierKey.getName());

        QualifierKeyEntity q = EntityHelper.convert(qualifierKey);
        try {
            Long id = qualifierKeyDAO.createQualifierKey(q);
            q.setId(id);
        } catch (DuplicateKeyException e) {
            throw new MVTException(e, "CatalogService.createQualifierKey: duplicate qualifierKey name", MVTErrorConst.DB_DUPLICATE_VALUE);
        }

        return EntityHelper.convert(q);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public QualifierKey readQualifierKey(Long id) {
        QualifierKeyEntity qualifierKeyEntity = qualifierKeyDAO.readQualifierKey(id);
        if (qualifierKeyEntity == null) {
            return null;
        }
        return EntityHelper.convert(qualifierKeyEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public QualifierKey updateQualifierKey(QualifierKey qualifierKey) throws MVTException {
        validateName("CatalogService.updateQualifierKey: wrong name", qualifierKey.getName());

        QualifierKeyEntity qualifierKeyEntity = EntityHelper.convert(qualifierKey);
        boolean b = qualifierKeyDAO.updateQualifierKey(qualifierKeyEntity);
        if (b) {
            return EntityHelper.convert(qualifierKeyEntity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public QualifierKey deleteQualifierKey(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<QualifierKey> listQualifierKey() {
        List<QualifierKeyEntity> list = qualifierKeyDAO.listQualifierKey();
        if (list == null) {
            return null;
        }
        List<QualifierKey> res = new ArrayList<>(list.size());
        for (QualifierKeyEntity e : list) {
            res.add(convertWithInnerFill(e));
        }
        return res;
    }

    private QualifierKey convertWithInnerFill(QualifierKeyEntity e) {
        QualifierKey qk = EntityHelper.convert(e);

        // fill with sensorLocation
        qk.setSensorLocation(getSensorLocation(e.getSensor_location_id()));

        // fill with sport
        qk.setSport(getSport(e.getSport_id()));

        return qk;
    }

    private QualifierKey convert(QualifierKeyEntity e) {
        QualifierKey qk = EntityHelper.convert(e);
        return qk;
    }
}
