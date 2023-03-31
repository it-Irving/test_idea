package com.ljw.mybatisplus;

import com.ljw.mybatisplus.entity.User;
import com.ljw.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.List;

@SpringBootTest
public class Test01 {
    @Autowired
    private UserMapper userMapper;

    /**
     * 找到所有用户
     */
    @Test
    public void findAllUsers() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

}
