package cn.nicecoder.barbersys.entity.DO;

import lombok.Data;

import java.io.Serializable;

/**
 * BarberRoleDO
 * @author: longt
 * @date: 2021/2/24 下午11:27
 */
@Data
public class BarberRoleDO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String code;
    private String name;
}
