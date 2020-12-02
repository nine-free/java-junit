import cn.soft1010.java.junit.JunitSpringRunner;
import cn.soft1010.java.junit.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JunitSpringRunner.class)
public class UserServiceTest {
    @Resource
    UserService userService;

    @Test
    public void test() {
        Assert.assertEquals(new Integer(10), userService.queryById(10).getId());
    }
}
