package com.bsu.mvt.server.rest.controller;

import com.mangofactory.swagger.annotations.*;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.rest.model.*;
import com.bsu.mvt.server.rest.model.constant.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/sample")
public class SampleController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private SampleService sampleService;

    @Autowired
    private LogService logService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AuthService authService;

    // sample
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Sample> createSample(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                               @RequestBody Sample sample) throws MVTException {
        // validate sample bean
        Sample newSample = sampleService.createSample(sample);
        if (newSample != null) {
            User user = new User(authService.getUserId(session));
            logService.createLog(new Log(user, new Sample(newSample.getId()), new Action(ActionConstants.ACTION_CREATE_SAMPLE, new Date())));
            mailService.pushToSend(user.getId(), newSample, ActionConstants.ACTION_CREATE_SAMPLE);
            return new ResponseEntity<Sample>(newSample, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create sample bean: " + sample;
            logger.debug(msg);
            throw new MVTException("Cannot create sample bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Sample> updateSample(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                               @RequestBody Sample sample) throws MVTException {
        // validate sample bean
        Sample s = sampleService.updateSample(sample);
        if (s != null) {
            User user = new User(authService.getUserId(session));
            logService.createLog(new Log(user, new Sample(s.getId()), new Action(ActionConstants.ACTION_UPDATE_SAMPLE, new Date())));
//            mailService.pushToSend(user.getId(), s, ActionConstants.ACTION_UPDATE_SAMPLE);
            return new ResponseEntity<Sample>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update sample bean: " + sample;
            logger.debug(msg);
            throw new MVTException("Cannot update sample bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "Sample not found")
    })
    @ResponseBody
    public ResponseEntity<Sample> getSample(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                            @PathVariable Long id) throws MVTException {
        Sample sample = sampleService.readSample(id);
        if (sample != null) {
            return new ResponseEntity<>(sample, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Sample>> listSample(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                   @RequestParam(value = "sport-id", required = false) Long sportId,
                                                   @RequestParam(value = "skip-activity", required = false, defaultValue = "false") Boolean skipActivity) throws MVTException {
        List<Sample> list = sampleService.listSample(sportId, skipActivity);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
