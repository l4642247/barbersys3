package cn.nicecoder.barbersys.entity.comm;

import lombok.Data;

/**
 * TODO
 * @author: xxxxx
 * @date: 2021/3/10 下午4:24
 */
@Data
public class Status {
    private Integer code;
    private String message;

    public Status(int code, String msg){
        this.code = code;
        this.message = msg;
    }
}
