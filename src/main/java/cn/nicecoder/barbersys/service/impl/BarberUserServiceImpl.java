package cn.nicecoder.barbersys.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.BarberUserRole;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import cn.nicecoder.barbersys.mapper.BarberUserMapper;
import cn.nicecoder.barbersys.service.BarberRoleService;
import cn.nicecoder.barbersys.service.BarberUserRoleService;
import cn.nicecoder.barbersys.service.BarberUserService;
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
public class BarberUserServiceImpl extends ServiceImpl<BarberUserMapper, BarberUser> implements BarberUserService {
    private static final String PASSWORD_DEFAULT_ORIGIN = "123456";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BarberRoleService barberRoleService;

    @Autowired
    private BarberUserRoleService barberUserRoleService;

    @Override
    public BarberUserVO getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BarberUserVO oneByUsername = this.baseMapper.getOneByUsername(userDetails.getUsername());
        return oneByUsername;
    }

    @Override
    public BarberUser createBarberUser(BarberUser barberUserSave) {
        barberUserSave.setPassword(passwordEncoder.encode(PASSWORD_DEFAULT_ORIGIN));
        return barberUserSave;
    }

    @Override
    public BarberUser passwordModify(String oldPassword, String password) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        BarberUser barberUserOrigin = this.getOne(new LambdaQueryWrapper<BarberUser>().eq(BarberUser::getUsername, username));
        String passwordOrigin = barberUserOrigin.getPassword();
        boolean flag = passwordEncoder.matches(oldPassword, passwordOrigin);
        if(!flag){
            throw new RuntimeException("密码校验失败");
        }
        barberUserOrigin.setPassword(passwordEncoder.encode(password));
        this.updateById(barberUserOrigin);
        return barberUserOrigin;
    }

    @Override
    public BarberUserVO getOneByUsername(String username) {
        BarberUserVO barberUserVO =  this.baseMapper.getOneByUsername(username);
        List<BarberRole> barberRoleList = barberRoleService.getRoleByUsername(username);
        if(barberRoleList.size() > 0) {
            barberUserVO.setRoleList(barberRoleList);
            StringBuffer roleBuffer = new StringBuffer();
            barberRoleList.stream().forEach(item -> {
                roleBuffer.append(item.getName()).append(",");
            });
            roleBuffer.deleteCharAt(roleBuffer.length() - 1);
            barberUserVO.setRoleStr(roleBuffer.toString());
        }
        return barberUserVO;
    }

    @Override
    public Page<BarberUserVO> listPageBarberUser(Page<BarberUser> page, BarberUserDO barberUserDO) {
        Page<BarberUserVO> pageResult =  this.baseMapper.listPageBarberUser(page, barberUserDO);
        for (BarberUserVO barberUserVO :pageResult.getRecords()){
            List<BarberRole> barberRoleList = barberRoleService.getRoleByUsername(barberUserVO.getUsername());
            if(barberRoleList.size() > 0) {
                barberUserVO.setRoleList(barberRoleList);
                StringBuffer roleBuffer = new StringBuffer();
                barberRoleList.stream().forEach(item -> {
                    roleBuffer.append(item.getName()).append(",");
                });
                roleBuffer.deleteCharAt(roleBuffer.length() - 1);
                barberUserVO.setRoleStr(roleBuffer.toString());
            }
        }
        return pageResult;
    }

    @Override
    public BarberUser saveOne(BarberUserDO barberUserSave) {
        String[] roleStr = barberUserSave.getRoleStr().split(",");
        this.saveOrUpdate(createBarberUser(barberUserSave));
        // 更新角色
        if(barberUserSave.getId() != null) {
            barberUserRoleService.remove(new LambdaQueryWrapper<BarberUserRole>()
                    .eq(BarberUserRole::getUserId, barberUserSave.getId()));
        }
        for (String roleId : roleStr ){
            BarberUserRole barberUserRole = new BarberUserRole();
            barberUserRole.setRoleId(Long.parseLong(roleId));
            barberUserRole.setUserId(barberUserSave.getId());
            barberUserRoleService.save(barberUserRole);
        }
        this.saveOrUpdate(barberUserSave);
        return barberUserSave;
    }


}
