package cn.nicecoder.barbersys.exception;

/**
 * 自定义异常类
 * @author: xxxxx
 * @date: 2021/5/17 下午3:44
 */
public class ServiceException extends RuntimeException{

    protected Integer errorCode;
    protected String errorMsg;

    public ServiceException(){

    }

    public ServiceException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}