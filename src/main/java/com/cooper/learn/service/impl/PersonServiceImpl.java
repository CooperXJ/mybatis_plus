package com.cooper.learn.service.impl;

import com.cooper.learn.entity.Person;
import com.cooper.learn.mapper.PersonMapper;
import com.cooper.learn.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cooper
 * @since 2020-10-24
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
