package cn.nicecoder.barbersys.entity.DO;

import lombok.Data;

/**
 * 密码数据传输对象
 * @author: xxxxx
 * @date: 2021/3/3 下午5:22
 */
@Data
public class PasswordDO {
    private String oldPassword;
    private String password;
}
