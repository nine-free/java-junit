package cn.soft1010.java.junit.controller;

import cn.soft1010.java.junit.annotation.Login;
import cn.soft1010.java.junit.annotation.SkipLogin;
import cn.soft1010.java.junit.service.UserService;
import cn.soft1010.java.junit.vo.Result;
import cn.soft1010.java.junit.vo.UserPo;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@RestController
@RequestMapping(value = {"user"})
public class UserController extends BaseController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //    @Login
//    @SkipLogin
    @RequestMapping(value = "detail/{id}", method = {RequestMethod.POST})
    public Result detail(@PathVariable(value = "id") Integer id) {
        logger.info("请求user/detail/{}", id);
        UserPo userPo = userService.queryById(id);
        Result result = new Result(0, "success", userPo);
        return result;
    }

    @RequestMapping(value = "detail2", method = {RequestMethod.POST})
    public Result detail2(@RequestParam(value = "id") Integer id) {
        logger.info("请求user/detail2?id={}", id);
        UserPo userPo = userService.queryById(id);
        Result result = new Result(0, "success", userPo);
        return result;
    }

    //    @Login
    @SkipLogin
    @RequestMapping(value = {"save"}, method = {RequestMethod.POST})
    public Result save(@RequestBody UserPo userPo) {
        logger.info("请求:{}", JSONObject.toJSONString(userPo));
        return new Result(0, "success", userPo);
    }
}
