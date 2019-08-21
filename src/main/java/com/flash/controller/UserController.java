package com.flash.controller;

import com.flash.enity.Result;
import com.flash.enity.StatusCode;
import com.flash.enity.User;
import com.flash.service.BaseService;
import com.flash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @RestController Controller和ResponseBody的整合注解
 * @CrossOrigin 解决跨域问题
 * @RequestMapping 前端访问路径
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BaseService baseService;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",userService.findAll());
    }


    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("userId") String userId){
        return new Result(true, StatusCode.OK,"查询成功",userService.findById(userId));
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody User user){
        if(userService.isHave(user.getUsername())==0){
            userService.save(user);
            return new Result(true, StatusCode.OK,"注册成功");
        }
        return new Result(true, StatusCode.OK,"用户名已经存在");
    }

    /**
     * 修改密码
     * @param userId
     * @param user
     * @return
     */
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("userId") String userId,@RequestBody User user){
        user.setId(userId);
        userService.update(user);
        return new Result(true, StatusCode.OK,"更新成功");
    }


    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("userId") String userId){
        userService.deleteById(userId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 用户登陆
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        User user1 = userService.findByUsername(user.getUsername());
        if(userService.isHave(user.getUsername())==0) {
            return new Result(true, StatusCode.ERROR,"用户名不存在");
        }else if (!userService.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())){
            return new Result(true, StatusCode.LOGINERROR,"用户名或密码错误");
        }
        map.put("user",user1);
        map.put("base",baseService.findByUserid(user1.getId()));
        return new Result(true, StatusCode.OK,"登陆成功",map);
    }

}
