package com.ljw.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljw.mybatisplus.entity.User;
import com.ljw.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }

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

    /**
     * 更新(动态sql)
     */
    @Test
    public void update(){
        User user = new User();
        user.setId(5L);
        user.setName("李五");
        user.setAge(57);

        //根据id，修改user
        int result = userMapper.updateById(user);

        //根据
//        Wrapper<User> userWrapper = new Wrapper<>();

    }

    /**
     * 测试乐观锁（成功）
     */
    @Test
    public void optimisticLocker(){
        User user = userMapper.selectById(5L);

        System.out.println(user); 

        //修改user
        user.setAge(33);

        int result = userMapper.updateById(user);
    }

    /**
     * 乐观锁失败
     */
    @Test
    public void optimisticLockerFail(){
        User user = userMapper.selectById(1596372880214577154L);

        System.out.println(user);

        //修改
        user.setName("小李");
        //模拟修改了版本号
        user.setVersion(user.getVersion() + 1);

        int i = userMapper.updateById(user);
    }

    /**
     * 测试查询
     */
    @Test
    public void testSelect(){
        User user = userMapper.selectById(1596352739992993794L);
        System.out.println("根据id查询记录:" + user);

        //批量
        System.out.println("批量:");
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        for(User u : users){
            System.out.println(u);
        }

        //简单的条件查询:通过map封装查询条件
        System.out.println("简单的条件查询:通过map封装查询条件");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 30);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    /**
     * 分页 selectPage
     */
    @Test
    public void page(){
        //1、创建page对象
        //传入参数：当前页 和 每页显示记录数
        Page<User> userPage = new Page<>(1, 3);

        //调用mp分页查询方法
        //调用mp分页查询过程中，底层会封装，把所有分页数据分装到page对象中
        //方式一：
        userMapper.selectPage(userPage, null);

        userPage.getRecords().forEach(System.out::println);
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getSize());
        System.out.println(userPage.getTotal());

        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }

    /**
     * 分页 selectMapsPage
     */
    @Test
    public void selectMapsPage(){
        //1、创建page对象
        //传入参数：当前页 和 每页显示记录数
        Page<User> userPage = new Page<>(1, 3);

        //调用mp分页查询方法
        //调用mp分页查询过程中，底层会封装，把所有分页数据分装到page对象中
        //方式一：
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(userPage, null);

        mapIPage.getRecords().forEach(System.out::println);
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getSize());
        System.out.println(userPage.getTotal());

        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        int i = userMapper.deleteById(5L);
        System.out.println(i);
    }

    /**
     * 删除
     */
    @Test
    public void deleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(4, 5));
        System.out.println(i);
    }

    /**
     * 删除
     */
    @Test
    public void deleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",31);

        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    /**
     * 查询包装
     */
    @Test
    public void queryWrapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .ge("age", 30)
                .like("name", "张");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 测试条件
     */
    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("name", "Jone");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * 测试条件
     */
    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .between("age", 57, 90);
        int num = userMapper.selectCount(queryWrapper);
        System.out.println(num);
    }

    @Test
    public void testSelectList(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王五");

        queryWrapper.allEq(map);

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .notLike("name", "e")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .inSql("id", "select id from user where id < 3");

        List<Object> objectList = userMapper.selectObjs(queryWrapper);
        objectList.forEach(System.out::println);

    }







}
