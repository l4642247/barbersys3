package cn.nicecoder.barbersys.aspect;

import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.exception.ServiceException;
import cn.nicecoder.barbersys.util.KeyUtil;
import cn.nicecoder.barbersys.util.RedisClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * 防止重复提交
 * @author: xxxxx
 * @date: 2021/5/18 下午5:52
 */
@Aspect
@Component
public class NoRepeatSubmitAspect {
    /**
     * redis缓存key的模板
     */
    private static final String KEY_TEMPLATE = "noRepeatSubmit_%s";


    @Autowired
    RedisClient redisClient;

    /**
     * 横切点
     */
    @Pointcut("@annotation(noRepeatSubmit)")
    public void repeatPoint(NoRepeatSubmit noRepeatSubmit) {
    }

    /**
     *  接收请求，并记录数据
     */
    @Around(value = "repeatPoint(noRepeatSubmit)")
    public Object doBefore(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String key = String.format(KEY_TEMPLATE, noRepeatSubmit.location() + "_" + KeyUtil.generate(method, joinPoint.getArgs()));

        if(redisClient.get(key) == null){
            redisClient.set(key, "", noRepeatSubmit.expireMillis());
        }else{
            return Resp.fail("操作过于频繁，请稍后重试。");
        }

        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throw new ServiceException(((ServiceException) throwable).getErrorCode(),
                    ((ServiceException) throwable).getErrorMsg());
        }

    }
}
