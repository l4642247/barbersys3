package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.SysRole;
import cn.nicecoder.barbersys.entity.DO.SysRoleDO;
import cn.nicecoder.barbersys.entity.PO.PermissionPO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 通过username获取用户角色
     * @author: xxxxx
     * @Param: [username]
     * @return: java.util.List<cn.nicecoder.barbersys.entity.BarberRole>
     * @date: 2021/3/5 下午3:14
     */
    List<SysRole> getRoleByUsername(String username);

    /**
     * 新增或更新
     * @param barberUserSave
     * @return
     */
    @Transactional
    SysRole saveOne(SysRoleDO barberUserSave);

    /**
     * 查询资源权限
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.PermissionPO>
     * @date: 2021/4/14 下午4:18
     */
    List<PermissionPO> getResourcePermission();
}
