package com.cooper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cooper.Mapper.UserMapper;
import com.cooper.learn.entity.Person;
import com.cooper.learn.mapper.PersonMapper;
import com.cooper.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PersonMapper personMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void insert(){
        User user = new User();
        user.setUsername("test2");
        user.setPassword("123456");
        int insert = userMapper.insert(user);//返回值为插入对象的id
        System.out.println(insert);
    }

    @Test
    void update(){
        //可以动态拼接SQL
        User user = userMapper.selectById(1319831208233275395L);
        user.setPassword("1111");
        user.setUsername("1111");
        userMapper.updateById(user);
    }

    //批量查询
    @Test
    void selectBatch(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    //按条件查询
    @Test
    void selectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","Cooper");
        map.put("password","2222");
        List list = userMapper.selectByMap(map);
        System.out.println(list);
    }

    //测试乐观锁
    @Test
    void testOptimisticLocker(){
        //此处需要注意一下，必须先将user查询出来再进行修改，因此直接new User()的话 version无法确定
        User user = userMapper.selectById(1319831208233275396L);
        user.setPassword("1111");
        user.setUsername("1111");

        //模拟插队操作
        User user1 = userMapper.selectById(1319831208233275396L);
        user1.setPassword("2222");
        userMapper.updateById(user1);//因为此时的更新已经将version更新了，也就是已经改变了查询的结果，version作出了改变

        //第一次的update因为有延迟所以没有立刻进行更新  因此查询的user已经更新了，所以再次更新的时候version已经不是原来的version了
        userMapper.updateById(user);
    }

    //测试分页
    @Test
    void testPage(){
        Page<User> page = new Page<>(1,5);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        userIPage.getRecords().forEach(System.out::println);
    }

    //此处是逻辑删除
    @Test
    void testDelete(){
        System.out.println(userMapper.deleteById(1319831125957844993L));
    }

    @Test
    void test(){
        Person person = new Person();
        person.setName("Cooper");
        person.setAge(20);
        System.out.println(personMapper.insert(person));
    }
}
