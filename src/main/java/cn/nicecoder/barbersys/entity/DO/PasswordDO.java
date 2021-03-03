package cn.nicecoder.barbersys.entity.DO;

import lombok.Data;

/**
 * @description: TODO
 * @author: longt
 * @date: 2021/3/3 下午5:22
 */
@Data
public class PasswordDO {
    private String oldPassword;
    private String password;
}
