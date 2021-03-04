package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    /**
     * 获取用户信息视图
     * @param condition
     * @return
     */
    public BarberUserVO userInfo(LambdaQueryWrapper<BarberUser> condition);
}
