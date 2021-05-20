package cn.nicecoder.barbersys.util;

import cn.hutool.json.JSONUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Key生成工具
 * @author: xxxxx
 * @date: 2021/5/20 上午10:50
 */
public class KeyUtil {

    /**
     * 根据{方法名 + 参数列表}和md5转换生成key
     */
    public static String generate(Method method, Object... args) {
        StringBuilder sb = new StringBuilder(method.toString());
        for (Object arg : args) {
            sb.append(toString(arg));
        }
        return DigestUtils.md5Hex(sb.toString());
    }

    private static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof Number) {
            return object.toString();
        }
        //调用json工具类转换成String
        return JSONUtil.toJsonStr(object);
    }
}