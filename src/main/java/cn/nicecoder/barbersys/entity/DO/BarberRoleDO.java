package cn.nicecoder.barbersys.entity.DO;

import cn.nicecoder.barbersys.entity.BarberRole;
import lombok.Data;

import java.io.Serializable;

/**
 * BarberRoleDO
 * @author: longt
 * @date: 2021/2/24 下午11:27
 */
@Data
public class BarberRoleDO extends BarberRole {
    private String selTree1_select_nodeId;
}
