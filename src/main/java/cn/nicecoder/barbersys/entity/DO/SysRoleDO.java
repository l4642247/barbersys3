package cn.nicecoder.barbersys.entity.DO;

import cn.nicecoder.barbersys.entity.SysRole;
import lombok.Data;

/**
 * 角色数据传输对象
 * @author: xxxxx
 * @date: 2021/2/24 下午11:27
 */
@Data
public class SysRoleDO extends SysRole {
    private String selTree1_select_nodeId;
}
