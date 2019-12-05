package com.lxg.controller;

import com.lxg.common.Result;
import com.lxg.common.enums.CommonEnum;
import com.lxg.entity.User;
import com.lxg.exception.BizException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常测试控制器
 * @author XIANGUO LI
 * @date 2019-11-19 14:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping
    public Result insert(@RequestBody User user) {
        if (user.getName() == null) {
            //抛出一个自定义异常
            throw new BizException(-1, "用户名不能为空！！！");
        }
        //如果没有异常，则返回数据为上传的user
        return new Result(CommonEnum.SUCCESS,user);
    }

    @DeleteMapping
    public Result delete() {
        //制造一个空指针异常，并且不进行处理，会被全局捕获
        String str=null;
        str.equals("test");
        //如果没有异常，则返回请求成功
        return new Result(CommonEnum.SUCCESS);
    }

    @PutMapping
    public Result update() {
        //这里故意造成数字异常，并且不进行处理
        int i = 1 / 0;
        //如果没有异常，则返回请求成功,返回数据为i
        return new Result(CommonEnum.SUCCESS,i);
    }

    @GetMapping
    public Result find() {
        /**
         * 这里故意造成索引越界异常，并且不进行处理,
         * 因为没有进行处理，全局异常中也没有进行具体异常的捕获
         * 所以被最后的Exception捕获了
         */
        List<String> list = new ArrayList<>();
        String str = list.get(0);
        //如果没有异常，则返回请求成功,返回数据为str
        return new Result(CommonEnum.SUCCESS,str);
    }
}