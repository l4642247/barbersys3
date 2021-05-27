package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.SysRole;
import cn.nicecoder.barbersys.entity.SysUser;
import cn.nicecoder.barbersys.entity.SysUserRole;
import cn.nicecoder.barbersys.entity.DO.SysUserDO;
import cn.nicecoder.barbersys.entity.VO.SysUserVO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.exception.ServiceException;
import cn.nicecoder.barbersys.mapper.SysUserMapper;
import cn.nicecoder.barbersys.service.SysRoleService;
import cn.nicecoder.barbersys.service.SysUserRoleService;
import cn.nicecoder.barbersys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final String PASSWORD_DEFAULT_ORIGIN = "123456";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService barberUserRoleService;

    @Override
    public SysUserVO getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUserVO oneByUsername = this.baseMapper.getOneByUsername(userDetails.getUsername());
        return oneByUsername;
    }

    @Override
    public SysUser createBarberUser(SysUser sysUserSave) {
        SysUserVO sysUser = sysUserService.getOneByUsername(sysUserSave.getUsername());
        if(sysUser != null){
            throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(), "该用户名已存在");
        }
        sysUserSave.setPassword(passwordEncoder.encode(PASSWORD_DEFAULT_ORIGIN));
        return sysUserSave;
    }

    @Override
    public SysUser passwordModify(String oldPassword, String password) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        SysUser sysUserOrigin = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        String passwordOrigin = sysUserOrigin.getPassword();
        boolean flag = passwordEncoder.matches(oldPassword, passwordOrigin);
        if(!flag){
            throw new RuntimeException("密码校验失败");
        }
        sysUserOrigin.setPassword(passwordEncoder.encode(password));
        this.updateById(sysUserOrigin);
        return sysUserOrigin;
    }

    @Override
    public SysUserVO getOneByUsername(String username) {
        SysUserVO sysUserVO =  this.baseMapper.getOneByUsername(username);
        List<SysRole> sysRoleList = sysRoleService.getRoleByUsername(username);
        if(sysRoleList.size() > 0) {
            sysUserVO.setRoleList(sysRoleList);
            StringBuffer roleBuffer = new StringBuffer();
            sysRoleList.stream().forEach(item -> {
                roleBuffer.append(item.getName()).append(",");
            });
            roleBuffer.deleteCharAt(roleBuffer.length() - 1);
            sysUserVO.setRoleStr(roleBuffer.toString());
        }
        return sysUserVO;
    }

    @Override
    public Page<SysUserVO> listPageBarberUser(Page<SysUser> page, SysUserDO barberUserDO) {
        Page<SysUserVO> pageResult =  this.baseMapper.listPageBarberUser(page, barberUserDO);
        for (SysUserVO SysUserVO :pageResult.getRecords()){
            List<SysRole> sysRoleList = sysRoleService.getRoleByUsername(SysUserVO.getUsername());
            if(sysRoleList.size() > 0) {
                SysUserVO.setRoleList(sysRoleList);
                StringBuffer roleBuffer = new StringBuffer();
                sysRoleList.stream().forEach(item -> {
                    roleBuffer.append(item.getName()).append(",");
                });
                roleBuffer.deleteCharAt(roleBuffer.length() - 1);
                SysUserVO.setRoleStr(roleBuffer.toString());
            }
        }
        return pageResult;
    }

    @Override
    public SysUser saveOne(SysUserDO barberUserSave) {
        this.saveOrUpdate(createBarberUser(barberUserSave));
        if(barberUserSave.getRoleStr() != null) {
            // 更新角色
            if(barberUserSave.getId() != null) {
                barberUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, barberUserSave.getId()));
            }
            String[] roleStr = barberUserSave.getRoleStr().split(",");
            for (String roleId : roleStr) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(Long.parseLong(roleId));
                sysUserRole.setUserId(barberUserSave.getId());
                barberUserRoleService.save(sysUserRole);
            }
        }
        this.saveOrUpdate(barberUserSave);
        return barberUserSave;
    }


}
