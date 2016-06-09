package com.bsu.mvt.server.core.service.impl;

import com.bsu.mvt.server.core.dao.*;
import com.bsu.mvt.server.core.entity.*;
import com.bsu.mvt.server.core.exception.*;
import com.bsu.mvt.server.core.service.ConfigService;
import com.bsu.mvt.server.core.util.ValidationHelper;
import com.bsu.mvt.server.rest.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

public class BaseServiceImpl {
    @Autowired
    @Qualifier("catalogDAO")
    protected CatalogDAO catalogDAO;

    @Autowired
    @Qualifier("qualifierKeyDAO")
    protected QualifierKeyDAO qualifierKeyDAO;

    @Autowired
    private ConfigService configService;

    private String baseFilePath;

    public void validateName(String msg, String name) throws MVTException {
        ValidationErrorBean eb = ValidationHelper.validateName(name, "name");
        if (eb != null) {
            throw new MVTException(msg, MVTErrorConst.FIELD_INVALID, eb);
        }
    }

    public void validateLoginPassword(String msg, String login, String password) throws MVTException {
        ValidationErrorBean eb = ValidationHelper.validateFieldIsEmpty(login, "login");
        ValidationErrorBean eb2 = ValidationHelper.validateFieldIsEmpty(password, "password");
        if (eb != null || eb2 != null) {
            ArrayList<ValidationErrorBean> l = new ArrayList<>();
            if (eb != null) {
                l.add(eb);
            }
            if (eb2 != null) {
                l.add(eb2);
            }

            throw new MVTException(msg, MVTErrorConst.FIELD_INVALID, l.toArray(new ValidationErrorBean[l.size()]));
        }
    }

    public void validateFile(String msg, String filePath, String hash) throws MVTException {
        ValidationErrorBean eb = ValidationHelper.validateFile(filePath, hash, "filePath", "hash");
        if (eb != null) {
            throw new MVTException(msg, MVTErrorConst.FILE_INVALID, eb);
        }
    }

    public void validateReglament(String msg, Reglament reglament) throws MVTException {
        ValidationErrorBean eb1 = ValidationHelper.validateMinMax(reglament.getMaxQualificationError(), "maxQualificationError", 0f, 100f);
        ValidationErrorBean eb2 = ValidationHelper.validateMinMax(reglament.getMaxClassificationError(), "maxClassificationError", 0f, 100f);
        ValidationErrorBean eb3 = ValidationHelper.validateMinMax(reglament.getMinActivityMatch(), "minActivityMatch", 0f, 100f);

        if (eb1 != null || eb2 != null || eb3 != null) {
            ArrayList<ValidationErrorBean> l = new ArrayList<>();
            if (eb1 != null) {
                l.add(eb1);
            }
            if (eb2 != null) {
                l.add(eb2);
            }
            if (eb3 != null) {
                l.add(eb3);
            }
            throw new MVTException(msg, MVTErrorConst.FIELD_RANGE_ERROR, l.toArray(new ValidationErrorBean[l.size()]));
        }
    }

    public void validateSportForMotionType(String msg, List<Sport> sportList) throws MVTException {
        ValidationErrorBean eb = ValidationHelper.validateListIsEmpty(sportList, "List<Sport> sportList");
        if (eb != null) {
            throw new MVTException(msg, MVTErrorConst.PARAM_INVALID, eb);
        }

        ArrayList<ValidationErrorBean> ebList = new ArrayList<>();
        for (Sport s : sportList) {
            eb = ValidationHelper.validateId(s.getId(), "Sport {id}");
            if (eb != null) {
                ebList.add(eb);
            }
        }
        if (ebList.size() > 0) {
            throw new MVTException(msg, MVTErrorConst.PARAM_INVALID, ebList.toArray(new ValidationErrorBean[ebList.size()]));
        }
    }

    protected void validateSample(String msg, Sample sample) throws MVTException {
        ValidationErrorBean eb1 = ValidationHelper.validateFieldIsEmpty(sample.getSport(), "sport");
        ValidationErrorBean eb2 = ValidationHelper.validateFieldIsEmpty(sample.getUsage(), "usage");
        ValidationErrorBean eb3 = ValidationHelper.validateFieldIsEmpty(sample.getVideoFile(), "videoFile");
        ValidationErrorBean eb4 = ValidationHelper.validateListIsEmpty(sample.getDataFileList(), "List<SensorDataFile> dataFileList");
        ValidationErrorBean eb5 = ValidationHelper.validateFieldIsEmpty(sample.getPlayerLevel(), "playerLevel");

        if (eb1 != null || eb2 != null || eb3 != null || eb4 != null|| eb5 != null) {
            ArrayList<ValidationErrorBean> l = new ArrayList<>();
            if (eb1 != null) {
                l.add(eb1);
            }
            if (eb2 != null) {
                l.add(eb2);
            }
            if (eb3 != null) {
                l.add(eb3);
            }
            if (eb4 != null) {
                l.add(eb4);
            }
            if (eb5 != null) {
                l.add(eb5);
            }
            throw new MVTException(msg, MVTErrorConst.PARAM_INVALID, l.toArray(new ValidationErrorBean[l.size()]));
        }
    }

    protected void validateExtra(String msg, Extra extra) throws MVTException {
        ValidationErrorBean eb1 = ValidationHelper.validateFieldIsEmpty(extra.getType(), "type");
        ValidationErrorBean eb2 = ValidationHelper.validateFieldIsEmpty(extra.getKey(), "key");
        ValidationErrorBean eb3 = ValidationHelper.validateFieldIsEmpty(extra.getValue(), "value");


        ValidationErrorBean eb4 = ValidationHelper.validateFieldIsEmpty(extra.getActivity(), "Activity");
        if (eb4 == null) {
            eb4 = ValidationHelper.validateFieldIsEmpty(extra.getActivity().getId(), "Activity.id");
        }

        ValidationErrorBean eb5 = ValidationHelper.validateFieldIsEmpty(extra.getSample(), "Sample");
        if (eb5 == null) {
            eb5 = ValidationHelper.validateFieldIsEmpty(extra.getSample().getId(), "Sample.id");
        }
                                                            // one should be empty and one not empty
        if (eb1 != null || eb2 != null || eb3 != null || ( (eb4 == null && eb5 == null) || (eb4 != null && eb5 != null) )) {
            ArrayList<ValidationErrorBean> l = new ArrayList<>();
            if (eb1 != null) {
                l.add(eb1);
            }
            if (eb2 != null) {
                l.add(eb2);
            }
            if (eb3 != null) {
                l.add(eb3);
            }
            if (eb4 != null) {
                l.add(eb4);
            }
            if (eb4 == null && eb5 == null) {
                l.add(new ValidationErrorBean("id", ValidationHelper.MSG_BOTH_FIELD_EMPTY, Arrays.asList((Object) "Activity.id", "Sample.id")));
            }
            if (eb4 != null && eb5 != null) {
                l.add(new ValidationErrorBean("id", ValidationHelper.MSG_BOTH_FIELD_NOT_EMPTY, Arrays.asList((Object) "Activity.id", "Sample.id")));
            }
            throw new MVTException(msg, MVTErrorConst.PARAM_INVALID, l.toArray(new ValidationErrorBean[l.size()]));
        }
    }


    protected void validateExtra(String msg, ExtraFile extraFile) throws MVTException {
        ValidationErrorBean eb1 = ValidationHelper.validateFieldIsEmpty(extraFile.getPath(), "path");
        ValidationErrorBean eb2 = ValidationHelper.validateFieldIsEmpty(extraFile.getDisplayName(), "displayName");
        ValidationErrorBean eb3 = ValidationHelper.validateFieldIsEmpty(extraFile.getHash(), "hash");

        ValidationErrorBean eb5 = ValidationHelper.validateFieldIsEmpty(extraFile.getSample(), "Sample");
        if (eb5 == null) {
            eb5 = ValidationHelper.validateFieldIsEmpty(extraFile.getSample().getId(), "Sample.id");
        }

        if (eb1 != null || eb2 != null || eb3 != null || eb5 != null) {
            ArrayList<ValidationErrorBean> l = new ArrayList<>();
            if (eb1 != null) {
                l.add(eb1);
            }
            if (eb2 != null) {
                l.add(eb2);
            }
            if (eb3 != null) {
                l.add(eb3);
            }
            if (eb5 != null) {
                l.add(eb5);
            }
            throw new MVTException(msg, MVTErrorConst.FIELD_RANGE_ERROR, l.toArray(new ValidationErrorBean[l.size()]));
        }

    }

    /**
     * Get reglament from cache
     *
     * @param reglamentId reglament id
     * @return null if no reglament found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected Reglament getReglament(Long reglamentId) {
        // fill with reglament
        if (reglamentId != null) {
            ReglamentEntity e = catalogDAO.readReglament(reglamentId);
            return EntityHelper.convert(e);
        }
        return null;
    }

    /**
     * Get sport filled with reglament from cache
     *
     * @param sportId sport id
     * @return null if no sport found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected Sport getSport(Long sportId) {
        // fill with sport
        if (sportId != null) {
            SportEntity e = catalogDAO.readSport(sportId);
            Sport s = EntityHelper.convert(e);

            // fill sport with reglament
            if (e.getReglament_id() != null) {
                s.setReglament(getReglament(e.getReglament_id()));
            }
            return s;
        }
        return null;
    }

    /**
     * Get sensorLocation from cache
     *
     * @param sensorLocationId sensor location id
     * @return null if no sensor location found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected SensorLocation getSensorLocation(Long sensorLocationId) {
        // fill with sensor location
        if (sensorLocationId != null) {
            SensorLocationEntity e = catalogDAO.readSensorLocation(sensorLocationId);
            return EntityHelper.convert(e);
        }
        return null;
    }

    /**
     * Get motion type from cache
     *
     * @param motionTypeId motion type id
     * @return null if no motion type found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected MotionType getMotionType(Long motionTypeId) {
        // fill with motion type
        if (motionTypeId != null) {
            MotionTypeEntity e = catalogDAO.readMotionType(motionTypeId);
            MotionType mt = EntityHelper.convert(e);
            // fill motion type with sport

            // fill motion type with reglament
            if (e.getReglament_id() != null) {
                mt.setReglament(getReglament(e.getReglament_id()));
            }
            return mt;
        }
        return null;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected List<MotionType> getMotionTypeListBySportId(Long sportId) {
        if (sportId != null) {
            List<MotionTypeEntity> motionTypeEntityList = catalogDAO.listMotionType(sportId);
            if (motionTypeEntityList != null && !motionTypeEntityList.isEmpty()) {
                List<MotionType> resultList = new ArrayList<>(motionTypeEntityList.size());
                for (MotionTypeEntity motionTypeEntity : motionTypeEntityList) {
                    resultList.add(EntityHelper.convert(motionTypeEntity));
                }
                return resultList;
            }
        }
        return null;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected List<Sport> getSportListByMotionTypeId(Long motionTypeId) {
        if (motionTypeId != null) {
            List<SportEntity> sportEntityList = catalogDAO.listSport(motionTypeId);
            if (sportEntityList != null && !sportEntityList.isEmpty()) {
                List<Sport> resultList = new ArrayList<>(sportEntityList.size());
                for (SportEntity sportEntity : sportEntityList) {
                    resultList.add(EntityHelper.convert(sportEntity));
                }
                return resultList;
            }
        }
        return null;
    }

    /**
     * Get qualifier key from cache
     *
     * @param qualifierKeyId qualifier key id
     * @return null if no qualifier key found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public QualifierKey getQualifierKey(Long qualifierKeyId) {
        // fill with qualifier key
        if (qualifierKeyId != null) {
            QualifierKeyEntity e = qualifierKeyDAO.readQualifierKey(qualifierKeyId);
            QualifierKey qk = EntityHelper.convert(e);
            if (e.getSport_id() != null) {
                qk.setSport(getSport(e.getSport_id()));
            }
            if (e.getSensor_location_id() != null) {
                qk.setSensorLocation(getSensorLocation(e.getSensor_location_id()));
            }
            return qk;
        }
        return null;
    }


    /**
     * Get motionType from cache
     *
     * @param motionTypeId sport id
     * @return null if no sport found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected MotionType getMotionTypeClean(Long motionTypeId) {
        // fill with sport
        if (motionTypeId != null) {
            MotionTypeEntity e = catalogDAO.readMotionType(motionTypeId);
            return EntityHelper.convert(e);
        }
        return null;
    }


    /**
     * Get sport from cache
     *
     * @param sportId sport id
     * @return null if no sport found or null id passed
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    protected Sport getSportClean(Long sportId) {
        // fill with sport
        if (sportId != null) {
            SportEntity e = catalogDAO.readSport(sportId);
            return EntityHelper.convert(e);
        }
        return null;
    }

    protected String getBasePath() {
        if (baseFilePath == null) {
            Config config = configService.readConfig();
            if (config != null) {
                baseFilePath = config.getFtpBasePath();
            }
            return baseFilePath;
        } else {
            return baseFilePath;
        }
    }


}
