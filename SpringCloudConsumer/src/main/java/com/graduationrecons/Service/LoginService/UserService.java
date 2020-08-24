package com.graduationrecons.Service.LoginService;

import com.graduationrecons.POJO.User;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-CELLER")
public interface UserService {

    @RequestMapping(value = "/getLoginUser", method= RequestMethod.GET)
    public User LoginUser(@RequestParam(value = "username") String username);

}
