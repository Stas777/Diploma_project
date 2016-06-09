package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.QualifierKey;

import java.util.List;

public interface QualifierKeyService {
    // qualifierKey
    public QualifierKey createQualifierKey(QualifierKey qualifierKey) throws MVTException;

    public QualifierKey readQualifierKey(Long id);

    public QualifierKey updateQualifierKey(QualifierKey qualifierKey) throws MVTException;

    public QualifierKey deleteQualifierKey(Long id);

    public List<QualifierKey> listQualifierKey();
}
