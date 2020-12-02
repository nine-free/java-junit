package cn.soft1010.java.junit.interceptor;

import cn.soft1010.java.junit.annotation.SkipLogin;
import cn.soft1010.java.junit.exception.LoginException;
import com.sun.tools.internal.ws.wsdl.document.schema.SchemaKinds;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by zhangjifu on 2020/12/2.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            SkipLogin skipLogin = handlerMethod.getMethodAnnotation(SkipLogin.class);
            if (skipLogin == null) {
                checkLogin(request);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    private void checkLogin(HttpServletRequest request) {
        //登录校验
        String token = request.getHeader("token");
        if (token == null || token.equalsIgnoreCase("")) {
            throw new LoginException("未登录");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
