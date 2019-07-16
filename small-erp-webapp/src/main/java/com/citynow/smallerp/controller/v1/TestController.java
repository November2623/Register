package com.citynow.smallerp.controller.v1;

import com.citynow.smallerp.annotation.APIVersion;
import com.citynow.smallerp.entity.User;
import com.citynow.smallerp.mapper.UserMapper;
import com.citynow.smallerp.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@APIVersion
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/index")
    public ResponseEntity<?> index() {
        logger.info("test api");

        UserModel userM = new UserModel();
        userM.setUsername("username");
        User userR = UserMapper.INSTANCE.userModelToUser(userM);

        return ResponseEntity.ok("test auth api");
    }
}
