package com.bsu.mvt.server.rest.controller;

import com.mangofactory.swagger.annotations.*;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.ExtraFileService;
import com.bsu.mvt.server.core.service.FileService;
import com.bsu.mvt.server.rest.model.*;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private ExtraFileService extraFileService;

    // video  file
    @RequestMapping(method = RequestMethod.POST, value = "/video")
    public ResponseEntity<VideoFile> createVideoFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                     @RequestBody VideoFile file) throws MVTException {
        // validate file bean
        VideoFile newFile = fileService.createVideoFile(file);
        if (newFile != null) {
            return new ResponseEntity<VideoFile>(newFile, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create file bean: " + file;
            logger.debug(msg);
            throw new MVTException("Cannot create file bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/video")
    public ResponseEntity<VideoFile> updateVideoFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                     @RequestBody VideoFile file) throws MVTException {
        // validate file bean
        VideoFile s = fileService.updateVideoFile(file);
        if (s != null) {
            return new ResponseEntity<VideoFile>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update file bean: " + file;
            logger.debug(msg);
            throw new MVTException("Cannot update file bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/video/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "VideoFile not found")
    })
    @ResponseBody
    public ResponseEntity<VideoFile> getVideoFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                  @PathVariable Long id) throws MVTException {
        VideoFile file = fileService.readVideoFile(id);
        if (file != null) {
            return new ResponseEntity<VideoFile>(file, HttpStatus.OK);
        } else {
            return new ResponseEntity<VideoFile>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/video")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<VideoFile>> listVideoFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session) throws MVTException {
        List<VideoFile> list = fileService.listVideoFile(null);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // sensor data file
    @RequestMapping(method = RequestMethod.POST, value = "/sensor-data")
    public ResponseEntity<SensorDataFile> createSensorDataFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                               @RequestBody SensorDataFile file) throws MVTException {
        // validate file bean
        SensorDataFile newFile = fileService.createSensorDataFile(file);
        if (newFile != null) {
            return new ResponseEntity<SensorDataFile>(newFile, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create file bean: " + file;
            logger.debug(msg);
            throw new MVTException("Cannot create sensor data file bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sensor-data")
    public ResponseEntity<SensorDataFile> updateSensorDataFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                               @RequestBody SensorDataFile file) throws MVTException {
        // validate file bean
        SensorDataFile s = fileService.updateSensorDataFile(file);
        if (s != null) {
            return new ResponseEntity<SensorDataFile>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update file bean: " + file;
            logger.debug(msg);
            throw new MVTException("Cannot update file bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sensor-data/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "SensorDataFile not found")
    })

    @ResponseBody
    public ResponseEntity<SensorDataFile> getSensorDataFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                            @PathVariable Long id) throws MVTException {
        SensorDataFile file = fileService.readSensorDataFile(id);
        if (file != null) {
            return new ResponseEntity<SensorDataFile>(file, HttpStatus.OK);
        } else {
            return new ResponseEntity<SensorDataFile>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sensor-data")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<SensorDataFile>> listSensorDataFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                                   @RequestParam(value = "sample-id", required = false) Long sampleId) throws MVTException {
        List<SensorDataFile> list = fileService.listSensorDataFile(sampleId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // extra  file
    @RequestMapping(method = RequestMethod.POST, value = "/extra")
    public ResponseEntity<ExtraFile> createExtraFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                     @RequestBody ExtraFile file) throws MVTException {
        // validate file bean
        ExtraFile newFile = extraFileService.createExtraFile(file);
        if (newFile != null) {
            return new ResponseEntity<ExtraFile>(newFile, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create file bean: " + file;
            logger.debug(msg);
            throw new MVTException("Cannot create file bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/extra")
    public ResponseEntity<ExtraFile> updateVideoFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                     @RequestBody ExtraFile file) throws MVTException {
        // validate file bean
        ExtraFile s = extraFileService.updateExtraFile(file);
        if (s != null) {
            return new ResponseEntity<ExtraFile>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update file bean: " + file;
            logger.debug(msg);
            throw new MVTException("Cannot update file bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/extra/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "ExtraFile not found")
    })
    @ResponseBody
    public ResponseEntity<ExtraFile> getExtraFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                  @PathVariable Long id) throws MVTException {
        ExtraFile file = extraFileService.readExtraFile(id);
        if (file != null) {
            return new ResponseEntity<ExtraFile>(file, HttpStatus.OK);
        } else {
            return new ResponseEntity<ExtraFile>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/extra")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<ExtraFile>> listExtraFile(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                         @RequestParam(value = "sample-id", required = false) Long sampleId) throws MVTException {
        List<ExtraFile> list = extraFileService.listExtraFile(sampleId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
