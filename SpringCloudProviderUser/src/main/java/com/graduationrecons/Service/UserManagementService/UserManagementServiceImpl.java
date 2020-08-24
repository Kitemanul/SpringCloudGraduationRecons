package com.graduationrecons.Service.UserManagementService;

import com.graduationrecons.Dao.User.UserMapper;
import com.graduationrecons.POJO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("用户操作类")
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value="Users")
    @RequestMapping("/getUser")
    @ApiOperation("搜索用户")
    public List<User> SearchaUsers(@RequestBody User user) {
        return userMapper.getUsers(user);
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    @RequestMapping("/UpdateUser")
    @ApiOperation("编辑用户")
    public int UpdateUser(@RequestBody User user, @RequestParam String username) {

        return userMapper.UpdateUser(user,username);
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    @RequestMapping("/DeleteUser")
    @ApiOperation("删除用户")
    public int DeleteUser(String username) {

        return userMapper.DeleteUserbyUsername(username);
    }

    @Override
    @Caching(evict={@CacheEvict(value = "RegisterUser",allEntries = true),@CacheEvict(value = "Users",allEntries = true)})
    @RequestMapping("/AddUser")
    @ApiOperation("添加用户")
    public int InsertUser(User user) {
        if(user.getPermission()==0) {
            user.setPermission(2);
        }
       user.setMm(new BCryptPasswordEncoder().encode(user.getMm()));
       return userMapper.InsertUser(user);

    }
}
