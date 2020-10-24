package com.cooper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cooper.Mapper.UserMapper;
import com.cooper.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WrapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void test1(){
        //按照倒序查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2(){
        //集合查询 in
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from user where id <1319831208233275396");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
