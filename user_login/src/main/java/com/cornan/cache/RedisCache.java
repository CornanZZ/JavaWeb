package com.cornan.cache;

import com.cornan.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
public class RedisCache implements Cache {
    //id为mapper中的命名空间
    private String id;

    public RedisCache(String id){
        log.info("id:[{}]", id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log.info("缓存的key:[{}]", key);
        getRedisTemplate().opsForHash().put(id, key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        log.info("获取的key:[{}]", key);
        return getRedisTemplate().opsForHash().get(id, key.toString());
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        getRedisTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    //封装获取redisTemplate的方法
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //配置序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
