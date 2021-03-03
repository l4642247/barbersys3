package cn.nicecoder.barbersys.entity.DO;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * BarberUserDO
 * @author: longt
 * @date: 2021/2/24 下午11:27
 */
@Data
public class BarberUserDO implements Serializable {
    private static final long serialVersionUID=1L;
    private String username;
    private String phone;
    private String idCard;
}
