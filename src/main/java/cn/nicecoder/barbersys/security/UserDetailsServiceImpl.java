package cn.nicecoder.barbersys.security;

import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.SysUser;
import cn.nicecoder.barbersys.entity.SysRole;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.SysRoleService;
import cn.nicecoder.barbersys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证
 * @author: longt
 * @date: 2021/2/27 下午10:04
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService SysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义用户认证逻辑，传递的用户名是："+username);
        if(StrUtil.isEmpty(username)){
            throw new RuntimeException("用户名不能为空");
        }
        //获取用户
        SysUser sysUser = sysUserService.getOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username).eq(SysUser::getStatus, CommonEnum.NORMAL.getCode()));
        if(sysUser == null){
            throw new RuntimeException("用户不存在");
        }
        //获取角色
        List<SysRole> sysRoleList = SysRoleService.getRoleByUsername(username);

        List<GrantedAuthority> authoritys = new ArrayList<>();
        for (SysRole sysRole : sysRoleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(sysRole.getCode());
            authoritys.add(authority);
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), authoritys);
    }


}
