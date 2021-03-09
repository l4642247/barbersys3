package cn.nicecoder.barbersys.entity.DO;

import cn.nicecoder.barbersys.entity.BarberUser;
import lombok.Data;

/**
 * BarberUserDO
 * @author: longt
 * @date: 2021/2/24 下午11:27
 */
@Data
public class BarberUserDO extends BarberUser{
    private String roleStr;
}
