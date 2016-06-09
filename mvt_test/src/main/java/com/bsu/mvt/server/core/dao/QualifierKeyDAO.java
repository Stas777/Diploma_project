package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.QualifierKeyEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface QualifierKeyDAO {
    public QualifierKeyEntity readQualifierKey(Long id) throws DataAccessException;

    public Long createQualifierKey(QualifierKeyEntity e) throws DataAccessException;

    public boolean updateQualifierKey(QualifierKeyEntity e) throws DataAccessException;

    public List<QualifierKeyEntity> listQualifierKey() throws DataAccessException;
}
