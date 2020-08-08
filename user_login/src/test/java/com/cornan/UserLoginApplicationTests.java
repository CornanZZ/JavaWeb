package com.cornan;

import com.cornan.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/*
@SpringBootTest
class UserLoginApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    //private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        //stringRedisTemplate.opsForValue().set("name", "cornan");
        User user = (User) redisTemplate.opsForHash().get("com.cornan.dao.UserDAO", "3");
        System.out.println(user);
    }

}*/
