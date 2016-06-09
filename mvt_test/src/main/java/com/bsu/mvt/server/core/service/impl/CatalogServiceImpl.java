package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.CatalogService;
import com.bsu.mvt.server.core.util.ValidationHelper;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

//TODO: process duplicate value in sensor location, sport, motion type

@Service("catalogService")
public class CatalogServiceImpl extends BaseServiceImpl implements CatalogService {

    // sport
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Sport createSport(Sport sport) throws MVTException {
        // validate
        validateName("CatalogService.createSport: wrong name", sport.getName());

        Sport s;
        SportEntity se = EntityHelper.convert(sport);
        try {
            Long id = catalogDAO.createSport(se);
            se.setId(id);
//            s = convertWithInnerFill(se);
            s = EntityHelper.convert(se);

            List<MotionType> motionTypeList = sport.getMotionTypeList();
            catalogDAO.removeSportFromMotionType(id);
            if(motionTypeList != null && !motionTypeList.isEmpty()) {
                List<Long> motionTypeIdList = new ArrayList<>(motionTypeList.size());
                for (MotionType motionType : motionTypeList) {
                    motionTypeIdList.add(motionType.getId());
                }
                catalogDAO.assignSportToMotionType(id, motionTypeIdList);
            }

            // double filled list
//            s.setMotionTypeList(listMotionType(id));
            s.setMotionTypeList(getMotionTypeListBySportId(id));
        } catch (DuplicateKeyException e) {
            ValidationErrorBean b = ValidationHelper.validateDatabaseDuplicateKey(e.getMessage(), "name");
            throw new MVTException(e, "CatalogService.createSport: duplicate entity", MVTErrorConst.DB_DUPLICATE_VALUE, b);
        }

        return s;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Sport readSport(Long id) {
        SportEntity sportEntity = catalogDAO.readSport(id);
        if (sportEntity == null) {
            return null;
        }
        return convertWithInnerFill(sportEntity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Sport updateSport(Sport sport) throws MVTException {
        // validate
        validateName("CatalogService.updateSport: wrong name", sport.getName());

        Sport s;
        SportEntity sportEntity = EntityHelper.convert(sport);
        if (catalogDAO.updateSport(sportEntity)) {
//            s = convertWithInnerFill(sportEntity);
            s = EntityHelper.convert(sportEntity);

            List<MotionType> motionTypeList = sport.getMotionTypeList();
            catalogDAO.removeSportFromMotionType(s.getId());
            List<Long> motionTypeIdList = new ArrayList<>(motionTypeList.size());
            if(motionTypeList != null && !motionTypeList.isEmpty()) {
                for (MotionType motionType : motionTypeList) {
                    motionTypeIdList.add(motionType.getId());
                }
                catalogDAO.assignSportToMotionType(s.getId(), motionTypeIdList);
            }

            // double filled list
//            s.setMotionTypeList(listMotionType(id));
            s.setMotionTypeList(getMotionTypeListBySportId(s.getId()));

            return s;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Sport deleteSport(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Sport> listSport() {
        List<SportEntity> list = catalogDAO.listSport();
        if (list == null) {
            return null;
        }
        List<Sport> res = new ArrayList<>(list.size());
        for (SportEntity e : list) {
            res.add(convertWithInnerFill(e));
        }
        return res;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Sport> listSport(Long motionTypeId) {
        List<SportEntity> list = catalogDAO.listSport(motionTypeId);
        if (list == null) {
            return null;
        }
        List<Sport> res = new ArrayList<>(list.size());
        for (SportEntity e : list) {
            res.add(convertWithInnerFill(e));
        }
        return res;
    }

    // sensor location
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public SensorLocation createSensorLocation(SensorLocation sensorLocation) throws MVTException {
        // validate
        validateName("CatalogService.createSensorLocation: wrong name", sensorLocation.getName());

        SensorLocationEntity e = EntityHelper.convert(sensorLocation);
        Long id = catalogDAO.createSensorLocation(e);
        e.setId(id);

        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public SensorLocation readSensorLocation(Long id) {
        SensorLocationEntity e = catalogDAO.readSensorLocation(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public SensorLocation updateSensorLocation(SensorLocation sensorLocation) throws MVTException {
        // validate
        validateName("CatalogService.updateSensorLocation: wrong name", sensorLocation.getName());

        SensorLocationEntity e = EntityHelper.convert(sensorLocation);
        if (catalogDAO.updateSensorLocation(e)) {
            return EntityHelper.convert(e);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public SensorLocation deleteSensorLocation(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<SensorLocation> listSensorLocation() {
        List<SensorLocationEntity> list = catalogDAO.listSensorLocation();
        if (list == null) {
            return null;
        }
        List<SensorLocation> res = new ArrayList<>(list.size());
        for (SensorLocationEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    // motion type
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public MotionType createMotionType(MotionType motionType) throws MVTException {
        // validate
        validateName("CatalogService.createMotionType: wrong name", motionType.getName());
        validateSportForMotionType("CatalogService.createMotionType: wrong sport list parameter", motionType.getSportList());

        MotionType mt;
        try {
            MotionTypeEntity mte = EntityHelper.convert(motionType);
            Long id = catalogDAO.createMotionType(mte);
            mte.setId(id);
            mt = EntityHelper.convert(mte);

            List<Sport> sportList = motionType.getSportList();
            List<Long> sportIdList = new ArrayList<>(sportList.size());
            for (Sport sport : sportList) {
                sportIdList.add(sport.getId());
            }
            catalogDAO.removeMotionTypeFromSport(id);
            catalogDAO.assignMotionTypeToSport(id, sportIdList);

            // double filled list
//            mt.setSportList(listSport(id));
            mt.setSportList(getSportListByMotionTypeId(id));

        } catch (DuplicateKeyException e) {
            throw new MVTException(e, "CatalogService.createMotionType: duplicate motion type name or value", MVTErrorConst.DB_DUPLICATE_VALUE);
        }

        return mt;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public MotionType readMotionType(Long id) {
        MotionTypeEntity e = catalogDAO.readMotionType(id);
        if (e == null) {
            return null;
        }
        return convertWithInnerFill(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public MotionType updateMotionType(MotionType motionType) throws MVTException {
        // validate
        validateName("CatalogService.updateMotionType: wrong name", motionType.getName());

        MotionType mt;
        MotionTypeEntity e = EntityHelper.convert(motionType);
        if (catalogDAO.updateMotionType(e)) {
            mt = EntityHelper.convert(e);

            List<Sport> sportList = motionType.getSportList();
            List<Long> sportIdList = new ArrayList<>(sportList.size());
            for (Sport sport : sportList) {
                sportIdList.add(sport.getId());
            }
            catalogDAO.removeMotionTypeFromSport(mt.getId());
            catalogDAO.assignMotionTypeToSport(mt.getId(), sportIdList);

            // double filled list
//            mt.setSportList(listSport(mt.getId()));
            mt.setSportList(getSportListByMotionTypeId(mt.getId()));
            return mt;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public MotionType deleteMotionType(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<MotionType> listMotionType(Long sportId) {
        List<MotionTypeEntity> list;
        if (sportId == null) {
            list = catalogDAO.listMotionType();
        } else {
            list = catalogDAO.listMotionType(sportId);
        }
        if (list == null) {
            return null;
        }
        List<MotionType> res = new ArrayList<>(list.size());
        for (MotionTypeEntity e : list) {
            res.add(convertWithInnerFill(e));
        }
        return res;
    }

    // reglament
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Reglament createReglament(Reglament reglament) throws MVTException {
        validateReglament("CatalogService.createReglament", reglament);
        ReglamentEntity e = EntityHelper.convert(reglament);
        Long id = catalogDAO.createReglament(e);
        e.setId(id);
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public Reglament readReglament(Long id) {
        ReglamentEntity e = catalogDAO.readReglament(id);
        if (e == null) {
            return null;
        }
        return EntityHelper.convert(e);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Reglament updateReglament(Reglament reglament) throws MVTException {
        validateReglament("CatalogService.updateReglament", reglament);
        ReglamentEntity e = EntityHelper.convert(reglament);
        if (catalogDAO.updateReglament(e)) {
            return EntityHelper.convert(e);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Reglament deleteReglament(Long id) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public List<Reglament> listReglament() {
        List<ReglamentEntity> list = catalogDAO.listReglament();
        if (list == null) {
            return null;
        }
        List<Reglament> res = new ArrayList<>(list.size());
        for (ReglamentEntity e : list) {
            res.add(EntityHelper.convert(e));
        }
        return res;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public QualifierKey readQualifierKey(Long id) {
       return super.getQualifierKey(id);
    }

    private Sport convertWithInnerFill(SportEntity e) {
        Sport s = EntityHelper.convert(e);
        s.setReglament(getReglament(e.getReglament_id()));
        s.setMotionTypeList(getMotionTypeListBySportId(e.getId()));
        return s;
    }

    private MotionType convertWithInnerFill(MotionTypeEntity e) {
        MotionType mt = EntityHelper.convert(e);
        // fill with reglament
        mt.setReglament(getReglament(e.getReglament_id()));
        // fill with sport
        mt.setSportList(getSportListByMotionTypeId(e.getId()));
        return mt;
    }
}
