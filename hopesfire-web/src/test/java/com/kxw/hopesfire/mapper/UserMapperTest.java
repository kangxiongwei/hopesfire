package com.kxw.hopesfire.mapper;

import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.dao.mapper.UserMapper;
import com.kxw.hopesfire.web.HopesfireWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020-07-23 23:27
 */
@SpringBootTest(classes = HopesfireWebApplication.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testGetByNickname() {
        UserEntity nickname = userMapper.getUserByNickname("管理员");
        System.out.println(nickname);
    }
}
