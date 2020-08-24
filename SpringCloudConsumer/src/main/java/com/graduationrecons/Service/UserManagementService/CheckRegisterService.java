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
public interface CheckRegisterService {

    @RequestMapping(value = "/CheckUser")
    public int Check(String username);

    @RequestMapping(value = "/getAllUnregisterUser")
    public List<User> getAllUnRegisterUser(@RequestBody User user);


}
