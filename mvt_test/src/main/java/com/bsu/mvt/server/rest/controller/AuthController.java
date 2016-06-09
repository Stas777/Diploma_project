package com.bsu.mvt.server.rest.controller;

import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.*;
import com.bsu.mvt.server.rest.model.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Session> login(@RequestBody Auth auth) throws MVTException {
        Session s = authService.login(auth.getLogin(), auth.getPassword());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
