package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.mapper.BarberRoleMapper;
import cn.nicecoder.barbersys.service.BarberRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BarberRoleServiceImpl extends ServiceImpl<BarberRoleMapper, BarberRole> implements BarberRoleService {

    @Override
    public List<BarberRole> getRoleByUsername(String username) {
        return this.baseMapper.getRoleByUsername(username);
    }
}
