package cn.cenjp.platform.redis;

import cn.cenjp.platform.Prefix.KeyPrefix;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class RedisService {
    private static Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            log.info(realKey,clazz);
            T t =stringToBean(str,clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    public void incrAll(KeyPrefix prefix, String key){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            jedis.incr(prefix.getPrefix()+key);
        }finally {
            returnToPool(jedis);
        }
    }

    public String get(String key){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            return str;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str==null||str.length()<=0)
                return false;
            String realKey  =""+ prefix.getPrefix() + key;
            int seconds =  prefix.expireSeconds();
            if(seconds <= 0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public void offset(KeyPrefix prefix,String time, Integer offset){
        Jedis jedis = null;
        String key;
        try {
            jedis =  jedisPool.getResource();
            key=prefix.getPrefix()+time;
            System.out.println("----------"+key+"--------"+offset);
            jedis.setbit(key,offset,"1");
            //生成真正的key
        }finally {
            returnToPool(jedis);
        }
    }

    public Integer getCount(KeyPrefix keyPrefix,String time){
        Jedis jedis = null;
        Integer bitcount;
        try {
            jedis =  jedisPool.getResource();
            String key=keyPrefix.getPrefix()+time;
            bitcount = jedis.bitcount(key).intValue();
            return bitcount;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     * */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            System.out.println(realKey);
            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }


    public static <T> String beanToString(T value) {
        if (value==null)
            return  null;
        Class<?> clazz = value.getClass();
        if (clazz==int.class){
            return ""+value;
        }else if (clazz==String.class){
            return (String) value;
        }else if (clazz==Long.class){
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }


    public static  <T> T stringToBean(String str, Class<T> clazz) {
        if (str==null||str.length()<=0||clazz==null){
            return null;
        }
        if (clazz==int.class||clazz==Integer.class){
            return (T) Integer.valueOf(str);
        }else if (clazz==String.class){
            return (T) str;
        }else if (clazz==Long.class||clazz == long.class ){
            return (T) Long.valueOf(str);
        }else if(clazz==Double.class||clazz==double.class){
            return (T) Double.valueOf(str);
        }else if (clazz== List.class){
            return (T) JSONObject.parseArray(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }



    private void returnToPool(Jedis jedis) {
        if (jedis!=null){
            jedis.close();
        }
    }

}
