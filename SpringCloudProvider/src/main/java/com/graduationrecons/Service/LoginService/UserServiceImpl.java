package com.graduationrecons.Service.LoginService;


import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("用户Service")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    @RequestMapping(value = "/getLoginUser",method = RequestMethod.GET)
    @ApiOperation("获取登录用户")
    public User LoginUser(@RequestParam("username") String username) {

        log.info(username);
        return userMapper.getUserbyName(username);
    }


}
