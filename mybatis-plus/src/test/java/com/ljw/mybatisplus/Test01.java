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

    /**
     * 插入测试
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("王五11");
        user.setAge(30);
        user.setEmail("1234556@qq.com");

        //影响行数
        int result = userMapper.insert(user);

        System.out.println(result);
        System.out.println(user);
    }


}
