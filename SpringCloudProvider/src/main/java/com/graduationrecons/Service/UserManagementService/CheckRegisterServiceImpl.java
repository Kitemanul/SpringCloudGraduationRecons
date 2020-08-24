package com.graduationrecons.Service.UserManagementService;


import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api("未注册用户操作类")
@Slf4j
public class CheckRegisterServiceImpl implements CheckRegisterService
{
    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value = "RegisterUser")
    @RequestMapping("/getAllUnregisterUser")
    @ApiOperation("搜索未注册用户")
    public List<User> getAllUnRegisterUser(@RequestBody User user) {
        List<User> res=new ArrayList<>();
        List<User> temp=userMapper.getUsers(user);
        for(User u:temp)
        {
            if(u.getPass()==0)
            {
                res.add(u);
            }
        }
        return res;
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    @RequestMapping(value = "/CheckUser")
    @ApiOperation("审核用户")
    public int Check(@RequestParam("username") String username) {
        return userMapper.UpdatePass(username);
    }
}
