package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.SysRole;
import cn.nicecoder.barbersys.entity.PO.PermissionPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleByUsername(String username);

    List<PermissionPO> getResourcePermission();
}
