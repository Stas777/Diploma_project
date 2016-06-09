package com.bsu.mvt.server.rest.controller;

import com.mangofactory.swagger.annotations.*;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.ActivityService;
import com.bsu.mvt.server.rest.model.Activity;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    // activity
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Activity> createActivity(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                   @RequestBody Activity activity) throws MVTException {
        // validate activity bean
        Activity newActivity = activityService.createActivity(activity);
        if (newActivity != null) {
            return new ResponseEntity<Activity>(newActivity, HttpStatus.CREATED);
        } else {
            String msg = "Cannot create activity bean: " + activity;
            logger.debug(msg);
            throw new MVTException("Cannot create activity bean");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Activity> updateActivity(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                   @RequestBody Activity activity) throws MVTException {
        // validate activity bean
        Activity s = activityService.updateActivity(activity);
        if (s != null) {
            return new ResponseEntity<Activity>(s, HttpStatus.OK);
        } else {
            String msg = "Cannot update activity bean: " + activity;
            logger.debug(msg);
            throw new MVTException("Cannot update activity bean");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "get Activity", responseClass = "Activity")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Invalid ID supplied"),
            @ApiError(code = 404, reason = "Activity not found")
    })
    @ResponseBody
    public ResponseEntity<Activity> getActivity(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                @PathVariable Long id) throws MVTException {
        Activity activity = activityService.readActivity(id);
        if (activity != null) {
            return new ResponseEntity<Activity>(activity, HttpStatus.OK);
        } else {
            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Activity>> listActivity(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                       @RequestParam(value = "sample-id", required = false) Long sampleId) throws MVTException {
        List<Activity> list;
        list = activityService.listActivity(sampleId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
