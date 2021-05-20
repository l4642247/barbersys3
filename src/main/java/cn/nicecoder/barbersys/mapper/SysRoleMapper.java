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

    /**
     * 根据username查询角色
     * @author: xxxxx
     * @Param: [username]
     * @return: java.util.List<cn.nicecoder.barbersys.entity.SysRole>
     * @date: 2021/5/20 上午10:34
     */
    List<SysRole> getRoleByUsername(String username);

    /**
     * 获取资源-权限信息
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.PermissionPO>
     * @date: 2021/5/20 上午10:34
     */
    List<PermissionPO> getResourcePermission();
}
