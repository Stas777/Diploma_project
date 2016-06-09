package com.bsu.mvt.server.core.dao;

import com.bsu.mvt.server.core.entity.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CatalogDAO {
    // sport
    public SportEntity readSport(Long id) throws DataAccessException;

    public Long createSport(SportEntity e) throws DataAccessException;

    public boolean updateSport(SportEntity e) throws DataAccessException;

    public List<SportEntity> listSport() throws DataAccessException;

    public List<SportEntity> listSport(Long motionTypeId) throws DataAccessException;

    public boolean assignSportToMotionType(Long sportId, List<Long> motionTypeIdList) throws DataAccessException;

    public boolean removeSportFromMotionType(Long sportId) throws DataAccessException;

    // sensor location
    public SensorLocationEntity readSensorLocation(Long id) throws DataAccessException;

    public Long createSensorLocation(SensorLocationEntity e) throws DataAccessException;

    public boolean updateSensorLocation(SensorLocationEntity e) throws DataAccessException;

    public List<SensorLocationEntity> listSensorLocation() throws DataAccessException;

    // motion type
    public MotionTypeEntity readMotionType(Long id) throws DataAccessException;

    public Long createMotionType(MotionTypeEntity e) throws DataAccessException;

    public boolean updateMotionType(MotionTypeEntity e) throws DataAccessException;

    public List<MotionTypeEntity> listMotionType() throws DataAccessException;

    public List<MotionTypeEntity> listMotionType(Long sportId) throws DataAccessException;

    public boolean assignMotionTypeToSport(Long motionTypeId, List<Long> sportIdList) throws DataAccessException;

    public boolean removeMotionTypeFromSport(Long motionTypeId) throws DataAccessException;

    // reglament
    public ReglamentEntity readReglament(Long id) throws DataAccessException;

    public Long createReglament(ReglamentEntity e) throws DataAccessException;

    public boolean updateReglament(ReglamentEntity e) throws DataAccessException;

    public List<ReglamentEntity> listReglament() throws DataAccessException;

}
