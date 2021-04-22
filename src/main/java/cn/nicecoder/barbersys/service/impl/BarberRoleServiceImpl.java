package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.BarberMenu;
import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.entity.BarberRoleMenu;
import cn.nicecoder.barbersys.entity.BarberUserRole;
import cn.nicecoder.barbersys.entity.DO.BarberRoleDO;
import cn.nicecoder.barbersys.entity.PO.PermissionPO;
import cn.nicecoder.barbersys.mapper.BarberRoleMapper;
import cn.nicecoder.barbersys.service.BarberMenuService;
import cn.nicecoder.barbersys.service.BarberRoleMenuService;
import cn.nicecoder.barbersys.service.BarberRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Autowired
    private BarberRoleMenuService barberRoleMenuService;

    @Autowired
    private BarberMenuService barberMenuService;

    @Override
    public List<BarberRole> getRoleByUsername(String username) {
        return this.baseMapper.getRoleByUsername(username);
    }

    @Override
    public BarberRole saveOne(BarberRoleDO barberRoleDO) {
        String[] menuStr = barberRoleDO.getSelTree1_select_nodeId().split(",");
        this.saveOrUpdate(barberRoleDO);
        // 更新关联菜单
        if(barberRoleDO.getId() != null) {
            barberRoleMenuService.remove(new LambdaQueryWrapper<BarberRoleMenu>()
                    .eq(BarberRoleMenu::getRoleId, barberRoleDO.getId()));
        }
        for (String menuId : menuStr ){
            saveRoleMenu(Long.parseLong(menuId), barberRoleDO.getId());
        }
        this.saveOrUpdate(barberRoleDO);
        return barberRoleDO;
    }

    @Override
    public List<PermissionPO> getResourcePermission() {
        return this.baseMapper.getResourcePermission();
    }


    public void saveRoleMenu(Long menuId, Long roleId){
        BarberRoleMenu barberRoleMenu = new BarberRoleMenu();
        barberRoleMenu.setMenuId(menuId);
        barberRoleMenu.setRoleId(roleId);
        barberRoleMenuService.save(barberRoleMenu);

        //如果有父节点一起保存
        BarberMenu barberMenu = barberMenuService.getById(menuId);
        if(barberMenu.getParentId() > 0){
            BarberRoleMenu barberRoleMenuParent = barberRoleMenuService.getOne(new LambdaQueryWrapper<BarberRoleMenu>()
                    .eq(BarberRoleMenu::getMenuId, barberMenu.getParentId())
                    .eq(BarberRoleMenu::getRoleId, roleId));
            if(ObjectUtils.isEmpty(barberRoleMenuParent)) {
                saveRoleMenu(barberMenu.getParentId(), roleId);
            }
        }
    }
}
