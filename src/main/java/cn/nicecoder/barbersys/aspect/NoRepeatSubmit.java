package cn.nicecoder.barbersys.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NoRepeatSubmit
 * @description 重复点击的切面
 * @author daleyzou
 * @date 2020年09月23日 14:35
 * @version 1.4.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 锁的请求地址
     */
    String location() default "NoRepeatSubmit";

    /**
     * 过期时间
     */
    int expireMillis() default 5;
}
