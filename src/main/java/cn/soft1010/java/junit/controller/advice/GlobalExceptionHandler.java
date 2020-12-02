package cn.soft1010.java.junit.controller.advice;

import cn.soft1010.java.junit.annotation.Login;
import cn.soft1010.java.junit.exception.LoginException;
import cn.soft1010.java.junit.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public Result handle(LoginException e) {

        return new Result(-1, "为登录", null);
    }

    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {

        return new Result(-10000, "未知异常", null);
    }
}
