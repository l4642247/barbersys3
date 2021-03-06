package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberRole;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
