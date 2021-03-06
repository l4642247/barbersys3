package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * @param username
     * @return
     */
    public BarberUserVO getOneByUsername(String username);

    /**
     * 分页查询
     * @param page
     * @param barberUserDO
     * @return
     */
    Page<BarberUserVO> listPageBarberUser(Page<BarberUser> page, BarberUserDO barberUserDO);
}
