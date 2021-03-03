package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberUserService extends IService<BarberUser> {
    /**
     * 创建用户
     * @param barberUserSave
     * @return
     */
    public BarberUser createBarberUser(BarberUser barberUserSave);

    /**
     * 修改密码
     * @param oldPassword
     * @param password
     * @return
     */
    public BarberUser passwordModify(String oldPassword, String password);
}
