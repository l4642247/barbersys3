package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.DO.BarberRoleDO;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.PO.PermissionPO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberRoleService extends IService<BarberRole> {
    /**
     * 通过username获取用户角色
     * @author: longt
     * @Param: [username]
     * @return: java.util.List<cn.nicecoder.barbersys.entity.BarberRole>
     * @date: 2021/3/5 下午3:14
     */
    List<BarberRole> getRoleByUsername(String username);

    /**
     * 新增或更新
     * @param barberUserSave
     * @return
     */
    @Transactional
    BarberRole saveOne(BarberRoleDO barberUserSave);

    /**
     * 查询资源权限
     * @author: longt
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.PermissionPO>
     * @date: 2021/4/14 下午4:18
     */
    List<PermissionPO> getResourcePermission();
}
