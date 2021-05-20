package cn.nicecoder.barbersys.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义异常类
 * @author: xxxxx
 * @date: 2021/5/17 下午3:44
 */
@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException{
    protected Integer errorCode;
    protected String errorMsg;
}