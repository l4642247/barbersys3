package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.SysRole;
import cn.nicecoder.barbersys.entity.SysUser;
import cn.nicecoder.barbersys.entity.SysUserRole;
import cn.nicecoder.barbersys.entity.DO.SysUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
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
 * @author lon't
 * @since 2021-02-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final String PASSWORD_DEFAULT_ORIGIN = "123456";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService barberUserRoleService;

    @Override
    public BarberUserVO getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BarberUserVO oneByUsername = this.baseMapper.getOneByUsername(userDetails.getUsername());
        return oneByUsername;
    }

    @Override
    public SysUser createBarberUser(SysUser sysUserSave) {
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
    public BarberUserVO getOneByUsername(String username) {
        BarberUserVO barberUserVO =  this.baseMapper.getOneByUsername(username);
        List<SysRole> sysRoleList = sysRoleService.getRoleByUsername(username);
        if(sysRoleList.size() > 0) {
            barberUserVO.setRoleList(sysRoleList);
            StringBuffer roleBuffer = new StringBuffer();
            sysRoleList.stream().forEach(item -> {
                roleBuffer.append(item.getName()).append(",");
            });
            roleBuffer.deleteCharAt(roleBuffer.length() - 1);
            barberUserVO.setRoleStr(roleBuffer.toString());
        }
        return barberUserVO;
    }

    @Override
    public Page<BarberUserVO> listPageBarberUser(Page<SysUser> page, SysUserDO barberUserDO) {
        Page<BarberUserVO> pageResult =  this.baseMapper.listPageBarberUser(page, barberUserDO);
        for (BarberUserVO barberUserVO :pageResult.getRecords()){
            List<SysRole> sysRoleList = sysRoleService.getRoleByUsername(barberUserVO.getUsername());
            if(sysRoleList.size() > 0) {
                barberUserVO.setRoleList(sysRoleList);
                StringBuffer roleBuffer = new StringBuffer();
                sysRoleList.stream().forEach(item -> {
                    roleBuffer.append(item.getName()).append(",");
                });
                roleBuffer.deleteCharAt(roleBuffer.length() - 1);
                barberUserVO.setRoleStr(roleBuffer.toString());
            }
        }
        return pageResult;
    }

    @Override
    public SysUser saveOne(SysUserDO barberUserSave) {
        String[] roleStr = barberUserSave.getRoleStr().split(",");
        this.saveOrUpdate(createBarberUser(barberUserSave));
        // 更新角色
        if(barberUserSave.getId() != null) {
            barberUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, barberUserSave.getId()));
        }
        for (String roleId : roleStr ){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Long.parseLong(roleId));
            sysUserRole.setUserId(barberUserSave.getId());
            barberUserRoleService.save(sysUserRole);
        }
        this.saveOrUpdate(barberUserSave);
        return barberUserSave;
    }


}
