package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.SysUser;
import cn.nicecoder.barbersys.entity.DO.SysUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * @Description: 获取当前用户id
     * @author: longt
     * @Param: []
     * @return: java.lang.Long
     * @date: 2021/4/28 下午3:16
     */
    public BarberUserVO getCurrentUser();

    /**
     * 创建用户
     * @param sysUserSave
     * @return
     */
    public SysUser createBarberUser(SysUser sysUserSave);

    /**
     * 修改密码
     * @param oldPassword
     * @param password
     * @return
     */
    public SysUser passwordModify(String oldPassword, String password);

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
    Page<BarberUserVO> listPageBarberUser(Page<SysUser> page, SysUserDO barberUserDO);


    /**
     * 新增或更新
     * @param barberUserSave
     * @return
     */
    @Transactional
    SysUser saveOne(SysUserDO barberUserSave);
}
