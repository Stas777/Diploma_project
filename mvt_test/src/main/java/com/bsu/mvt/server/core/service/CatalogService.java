package com.bsu.mvt.server.core.service;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.rest.model.*;

import java.util.List;

public interface CatalogService {
    // sport
    public Sport createSport(Sport sport) throws MVTException;

    public Sport readSport(Long id);

    public Sport updateSport(Sport sport) throws MVTException;

    public Sport deleteSport(Long id);

    public List<Sport> listSport();

    public List<Sport> listSport(Long motionTypeId);

    // sensor location
    public SensorLocation createSensorLocation(SensorLocation sensorLocation) throws MVTException;

    public SensorLocation readSensorLocation(Long id);

    public SensorLocation updateSensorLocation(SensorLocation sensorLocation) throws MVTException;

    public SensorLocation deleteSensorLocation(Long id);

    public List<SensorLocation> listSensorLocation();

    // motion type
    public MotionType createMotionType(MotionType motionType) throws MVTException;

    public MotionType readMotionType(Long id);

    public MotionType updateMotionType(MotionType motionType) throws MVTException;

    public MotionType deleteMotionType(Long id);

    List<MotionType> listMotionType(Long sportId);

    // reglament
    public Reglament createReglament(Reglament motionType) throws MVTException;

    public Reglament readReglament(Long id);

    public Reglament updateReglament(Reglament motionType) throws MVTException;

    public Reglament deleteReglament(Long id);


    public List<Reglament> listReglament();

    // qk
    public QualifierKey readQualifierKey(Long id);

}
