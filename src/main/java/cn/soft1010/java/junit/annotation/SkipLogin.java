package cn.soft1010.java.junit.annotation;

import java.lang.annotation.*;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipLogin {
}
