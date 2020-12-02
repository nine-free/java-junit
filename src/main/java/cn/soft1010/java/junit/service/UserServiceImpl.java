package cn.soft1010.java.junit.service;

import cn.soft1010.java.junit.vo.UserPo;
import org.springframework.stereotype.Service;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@Service
public class UserServiceImpl implements UserService {

    public UserPo queryById(Integer id) {
        UserPo userPo = new UserPo();
        userPo.setId(id);
        userPo.setName("name");
        return userPo;
    }
}
