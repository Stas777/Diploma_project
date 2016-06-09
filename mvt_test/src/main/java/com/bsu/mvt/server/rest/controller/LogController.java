package com.bsu.mvt.server.rest.controller;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.LogService;
import com.bsu.mvt.server.rest.model.Log;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;


    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Log>> listLogByUser(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                                   @RequestParam(value = "user-id", required = false) Long userId,
                                                   @RequestParam(value = "sample-id", required = false) Long sampleId
                                                   ) throws MVTException {
        List<Log> list;
        if (userId != null && sampleId != null) {
            list = logService.listLogByUserAndSample(userId, sampleId);
        } else if (userId != null) {
            list = logService.listLogByUser(userId);
        } else if (sampleId != null) {
            list = logService.listLogBySample(sampleId);
        } else {
            list = logService.listLog();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
