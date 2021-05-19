package cn.nicecoder.barbersys.entity.DO;

import lombok.Data;

import java.io.Serializable;

/**
 * BarberUserDO
 * @author: longt
 * @date: 2021/2/24 下午11:27
 */
@Data
public class BarberOrderDO implements Serializable {
    private static final long serialVersionUID=1L;
    private String memberName;
    private String barberName;
    private Integer type;
    private String dateStart;
    private String dateEnd;
    private Long barberId;
}
