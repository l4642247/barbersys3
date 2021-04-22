package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.entity.PO.PermissionPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberRoleMapper extends BaseMapper<BarberRole> {

    List<BarberRole> getRoleByUsername(String username);

    List<PermissionPO> getResourcePermission();
}
