package com.ljw.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljw.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户映射器
 *
 * @author Luo
 * @date 2022/11/25
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
