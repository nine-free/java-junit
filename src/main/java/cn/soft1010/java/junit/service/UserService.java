package cn.soft1010.java.junit.service;

import cn.soft1010.java.junit.vo.UserPo;

/**
 * Created by zhangjifu on 2020/12/2.
 */
public interface UserService {

    UserPo queryById(Integer id);
}
