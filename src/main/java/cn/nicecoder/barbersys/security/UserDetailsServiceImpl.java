package cn.nicecoder.barbersys.security;

import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.service.BarberRoleService;
import cn.nicecoder.barbersys.service.BarberUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
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
    BarberUserService barberUserService;

    @Autowired
    BarberRoleService BarberRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义用户认证逻辑，传递的用户名是："+username);
        if(StrUtil.isEmpty(username)){
            throw new RuntimeException("用户名不能为空");
        }
        System.out.println("自定义用户认证逻辑，登陆的用户是："+username);
        //获取用户
        BarberUser barberUser = barberUserService.getOne(
                new LambdaQueryWrapper<BarberUser>().eq(BarberUser::getUsername, username));
        if(barberUser == null){
            throw new UsernameNotFoundException(String.format("%s这个用户不存在",username));
        }
        //获取角色
        List<BarberRole> barberRoleList = BarberRoleService.getRoleByUsername(username);
        List<GrantedAuthority> authoritys = new ArrayList<>();
        for (BarberRole barberRole: barberRoleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(barberRole.getCode());
            authoritys.add(authority);
        }
        return new User(barberUser.getUsername(), barberUser.getPassword(),authoritys);
    }
}
