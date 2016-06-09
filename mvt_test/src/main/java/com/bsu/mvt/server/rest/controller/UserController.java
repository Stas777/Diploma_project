package com.bsu.mvt.server.rest.controller;

import com.mangofactory.swagger.annotations.*;
import com.bsu.mvt.server.core.exception.MVTException;
import com.bsu.mvt.server.core.service.UserService;
import com.bsu.mvt.server.rest.model.*;
import com.bsu.mvt.server.rest.model.constant.SecurityConstants;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<User>> listUser(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session) throws MVTException {
        List<User> list = userService.listUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiErrors(errors = {
            @ApiError(code = 404, reason = "User not found")
    })
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestHeader(value = SecurityConstants.HEADER_NAME_AUTH, required = true) String session,
                                          @PathVariable Long id) throws MVTException {
        User user = userService.readUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
