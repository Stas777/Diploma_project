package com.bsu.mvt.server.rest.controller;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.ConfigService;
import com.bsu.mvt.server.rest.model.Config;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Config> getConfig(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH,
            required = true) String session) throws MVTException {
        return new ResponseEntity<Config>(configService.readConfig(), HttpStatus.OK);

    }


}
