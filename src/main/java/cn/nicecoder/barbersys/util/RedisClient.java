package cn.nicecoder.barbersys.util;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
 
import java.util.concurrent.TimeUnit;
 
/**
 * Redis 工具类
 * @author: longt
 * @date: 2020/12/22 上午10:47
 */
@Component
public class RedisClient {
 
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 设置缓存（没有时间限制）
     * @author: longt
     * @Param: [key, value]
     * @return: void
     * @date: 2020/12/22 上午10:48
     */
    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }
 
    /**
     * 设置缓存（有时间限制，单位为 秒）
     * @author: longt
     * @Param: [key, value, timeout]
     * @return: void
     * @date: 2020/12/22 上午10:48
     */
    public void set(String key, Object value,long timeout){
        redisTemplate.opsForValue().set(key, value,timeout,TimeUnit.SECONDS);
    }
 
    /**
     * 删除缓存，并返回是否删除成功
     * @author: longt
     * @Param: [key]
     * @return: boolean
     * @date: 2020/12/22 上午10:48
     */
    public boolean delete(String key){
        redisTemplate.delete(key);
        // 如果还存在这个 key 就证明删除失败
        if(redisTemplate.hasKey(key)){
            return false;
        // 不存在就证明删除成功
        }else{
            return true;
        }
    }
 
    /**
     * 取出缓存
     * @author: longt
     * @Param: [key]
     * @return: java.lang.Object
     * @date: 2020/12/22 上午10:48
     */
    public Object get(String key){
        redisTemplate.getExpire(key);
        return redisTemplate.opsForValue().get(key);
    }
 
    /**
     * 获取失效时间（-2：失效 / -1：没有时间限制）
     * @author: longt
     * @Param: [key]
     * @return: long
     * @date: 2020/12/22 上午10:49
     */
    public long getExpire(String key){
        // 判断是否存在
        if(redisTemplate.hasKey(key)){
            return redisTemplate.getExpire(key);
        }else{
            return Long.parseLong(-2+"");
        }
    }
 
}