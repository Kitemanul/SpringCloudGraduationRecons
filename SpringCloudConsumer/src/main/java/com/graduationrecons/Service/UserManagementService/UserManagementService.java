package com.graduationrecons.Service.UserManagementService;

import com.graduationrecons.POJO.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-USER")
public interface UserManagementService {

    @RequestMapping(value = "/getUser")
    public List<User> SearchaUsers(@RequestBody User user);

    @RequestMapping(value = "/AddUser")
    public int InsertUser(@RequestBody User user);

    @RequestMapping(value = "/UpdateUser")
    public int UpdateUser(@RequestBody User user, @RequestParam(value = "username") String username);

    @RequestMapping(value = "/DeleteUser")
    public int DeleteUser(@RequestParam("username") String username);
}
