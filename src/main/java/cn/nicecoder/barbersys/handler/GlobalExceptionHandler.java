package cn.nicecoder.barbersys.handler;

import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常拦截器
 * @author: xxxxx
 * @date: 2021/5/17 下午3:50
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String INTERNAL_SERVER_ERROR = "系统异常，请联系管理员。";

    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Resp bizExceptionHandler(ServiceException e) {
        return Resp.fail(e.getErrorMsg());
    }

    /**
     * 处理其他异常(暂不处理)
     *
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Resp exceptionHandler(Exception e) {
        return Resp.fail(INTERNAL_SERVER_ERROR);
    }
    */
}