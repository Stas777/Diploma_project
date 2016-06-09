package com.bsu.mvt.server.rest.controller;


import com.mangofactory.swagger.annotations.*;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.rest.model.*;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private QualifierKeyService qualifierKeyService;

    // sport
    @RequestMapping(method = RequestMethod.POST, value = "/sport")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong sport name")
    })
    public ResponseEntity<Sport> createSport(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                             @RequestBody Sport sport) throws MVTException {
        // validate sport bean
        Sport newSport = catalogService.createSport(sport);
        if (newSport != null) {
            return new ResponseEntity<>(newSport, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create sport bean: " + sport;
            logger.debug(msg);
            throw new MVTException("Cannot create sport bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sport")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong sport name")
    })
    public ResponseEntity<Sport> updateSport(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                             @RequestBody Sport sport) throws MVTException {
        // validate sport bean
        Sport s = catalogService.updateSport(sport);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update sport bean: " + sport;
            logger.debug(msg);
            throw new MVTException("Cannot update sport bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sport/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "Sport not found")
    })
    @ResponseBody
    public ResponseEntity<Sport> getSport(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                          @PathVariable Long id) throws MVTException {
        Sport sport = catalogService.readSport(id);
        if (sport != null) {
            return new ResponseEntity<>(sport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sport")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Sport>> listSport(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session) throws MVTException {
        List<Sport> list = catalogService.listSport();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // sensor location
    @RequestMapping(method = RequestMethod.POST, value = "/sensor-location")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong sensorLocation name")
    })
    public ResponseEntity<SensorLocation> createSensorLocation(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                               @RequestBody SensorLocation sensorLocation) throws MVTException {
        // validate  bean
        SensorLocation sl = catalogService.createSensorLocation(sensorLocation);
        if (sl != null) {
            return new ResponseEntity<>(sl, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create SensorLocation bean: " + sensorLocation;
            logger.debug(msg);
            throw new MVTException("Cannot create SensorLocation bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sensor-location")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong sensorLocation name")
    })
    public ResponseEntity<SensorLocation> updateSensorLocation(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                               @RequestBody SensorLocation sensorLocation) throws MVTException {
        // validate sensorLocation bean
        SensorLocation s = catalogService.updateSensorLocation(sensorLocation);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update sensorLocation bean: " + sensorLocation;
            logger.debug(msg);
            throw new MVTException("Cannot update sensorLocation bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sensor-location/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "SensorLocation not found")
    })
    @ResponseBody
    public ResponseEntity<SensorLocation> getSensorLocation(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                            @PathVariable Long id) throws MVTException {
        SensorLocation sensorLocation = catalogService.readSensorLocation(id);
        if (sensorLocation != null) {
            return new ResponseEntity<>(sensorLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sensor-location")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<SensorLocation>> listSensorLocation(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session) throws MVTException {
        List<SensorLocation> list = catalogService.listSensorLocation();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // motion type
    @RequestMapping(method = RequestMethod.POST, value = "/motion-type")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong motionType name, Duplicate value")
    })
    public ResponseEntity<MotionType> createMotionType(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                       @RequestBody MotionType motionType) throws MVTException {
        // validate  bean
        MotionType sl = catalogService.createMotionType(motionType);
        if (sl != null) {
            return new ResponseEntity<>(sl, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create MotionType bean: " + motionType;
            logger.debug(msg);
            throw new MVTException("Cannot create MotionType bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/motion-type")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong motionType name")
    })
    public ResponseEntity<MotionType> updateMotionType(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                       @RequestBody MotionType motionType) throws MVTException {
        // validate motionType bean
        MotionType s = catalogService.updateMotionType(motionType);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update motionType bean: " + motionType;
            logger.debug(msg);
            throw new MVTException("Cannot update motionType bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/motion-type/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "MotionType not found")
    })
    @ResponseBody
    public ResponseEntity<MotionType> getMotionType(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                    @PathVariable Long id) throws MVTException {
        MotionType motionType = catalogService.readMotionType(id);
        if (motionType != null) {
            return new ResponseEntity<>(motionType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/motion-type")
    @ResponseBody
    public ResponseEntity<List<MotionType>> listMotionType(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                           @RequestParam(value = "sport-id", required = false) Long sportId) throws MVTException {
        List<MotionType> list;
        list = catalogService.listMotionType(sportId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // reglament
    @RequestMapping(method = RequestMethod.POST, value = "/reglament")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong reglament fields value")
    })
    public ResponseEntity<Reglament> createReglament(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                     @RequestBody Reglament reglament) throws MVTException {
        // validate  bean
        Reglament sl = catalogService.createReglament(reglament);
        if (sl != null) {
            return new ResponseEntity<>(sl, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create Reglament bean: " + reglament;
            logger.debug(msg);
            throw new MVTException("Cannot create Reglament bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reglament")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong reglament fields value")
    })
    public ResponseEntity<Reglament> updateReglament(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                     @RequestBody Reglament reglament) throws MVTException {
        // validate reglament bean
        Reglament s = catalogService.updateReglament(reglament);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update reglament bean: " + reglament;
            logger.debug(msg);
            throw new MVTException("Cannot update reglament bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reglament/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "Reglament not found")
    })
    @ResponseBody
    public ResponseEntity<Reglament> getReglament(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                  @PathVariable Long id) throws MVTException {
        Reglament reglament = catalogService.readReglament(id);
        if (reglament != null) {
            return new ResponseEntity<>(reglament, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reglament")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Reglament>> listReglament(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session) throws MVTException {
        List<Reglament> list = catalogService.listReglament();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // qualifierKey
    @RequestMapping(method = RequestMethod.POST, value = "/qualifier-key")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong qualifierKey name")
    })
    public ResponseEntity<QualifierKey> createQualifierKey(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                           @RequestBody QualifierKey qualifierKey) throws MVTException {
        // validate qualifierKey bean
        QualifierKey newQualifierKey = qualifierKeyService.createQualifierKey(qualifierKey);
        if (newQualifierKey != null) {
            return new ResponseEntity<QualifierKey>(newQualifierKey, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create qualifierKey bean: " + qualifierKey;
            logger.debug(msg);
            throw new MVTException("Cannot create qualifierKey bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/qualifier-key")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Wrong qualifierKey name")
    })
    public ResponseEntity<QualifierKey> updateQualifierKey(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                           @RequestBody QualifierKey qualifierKey) throws MVTException {
        // validate qualifierKey bean
        QualifierKey s = qualifierKeyService.updateQualifierKey(qualifierKey);
        if (s != null) {
            return new ResponseEntity<QualifierKey>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update qualifierKey bean: " + qualifierKey;
            logger.debug(msg);
            throw new MVTException("Cannot update qualifierKey bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/qualifier-key/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "QualifierKey not found")
    })
    @ResponseBody
    public ResponseEntity<QualifierKey> getQualifierKey(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                        @PathVariable Long id) throws MVTException {
        QualifierKey qualifierKey = qualifierKeyService.readQualifierKey(id);
        if (qualifierKey != null) {
            return new ResponseEntity<QualifierKey>(qualifierKey, HttpStatus.OK);
        } else {
            return new ResponseEntity<QualifierKey>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/qualifier-key")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<QualifierKey>> listQualifierKey(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session) throws MVTException {
        List<QualifierKey> list = qualifierKeyService.listQualifierKey();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

