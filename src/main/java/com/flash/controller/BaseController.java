package com.flash.controller;

import com.flash.enity.Base;
import com.flash.enity.Result;
import com.flash.enity.StatusCode;
import com.flash.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private BaseService baseService;


    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public Result findAll(@PathVariable String userId){
        return new Result(true, StatusCode.OK,"查询成功",baseService.findByUserid(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result findAll(@RequestBody Base base){
        baseService.save(base);
        return new Result(true, StatusCode.OK,"保存成功");
    }
}
