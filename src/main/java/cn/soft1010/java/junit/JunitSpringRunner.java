package cn.soft1010.java.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.soft1010.java.junit")
public class JunitSpringRunner {

    public static void main(String[] args) {
        SpringApplication.run(JunitSpringRunner.class, args);
    }
}
