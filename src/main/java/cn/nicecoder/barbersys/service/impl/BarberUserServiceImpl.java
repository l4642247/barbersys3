package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import cn.nicecoder.barbersys.mapper.BarberUserMapper;
import cn.nicecoder.barbersys.service.BarberRoleService;
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
        barberUserVO.setRoleList(barberRoleList);
        StringBuffer roleBuffer = new StringBuffer();
        barberRoleList.stream().forEach(item -> {
            roleBuffer.append(item.getName()).append(",");
        });
        roleBuffer.deleteCharAt(roleBuffer.length()-1);
        barberUserVO.setRoleStr(roleBuffer.toString());
        return barberUserVO;
    }

    @Override
    public Page<BarberUserVO> listPageBarberUser(Page<BarberUser> page, BarberUserDO barberUserDO) {
        Page<BarberUserVO> pageResult =  this.baseMapper.listPageBarberUser(page, barberUserDO);
        for (BarberUserVO barberUserVO :pageResult.getRecords()){
            List<BarberRole> barberRoleList = barberRoleService.getRoleByUsername(barberUserVO.getUsername());
            barberUserVO.setRoleList(barberRoleList);
            StringBuffer roleBuffer = new StringBuffer();
            barberRoleList.stream().forEach(item -> {
                roleBuffer.append(item.getName()).append(",");
            });
            roleBuffer.deleteCharAt(roleBuffer.length()-1);
            barberUserVO.setRoleStr(roleBuffer.toString());
        }
        return pageResult;
    }


}
