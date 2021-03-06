package cn.nicecoder.barbersys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.BarberMenu;
import cn.nicecoder.barbersys.entity.VO.CheckArrVO;
import cn.nicecoder.barbersys.entity.VO.MenuNodeVO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.mapper.BarberMenuMapper;
import cn.nicecoder.barbersys.service.BarberMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lon't
 * @since 2021-03-05
 */
@Service
public class BarberMenuServiceImpl extends ServiceImpl<BarberMenuMapper, BarberMenu> implements BarberMenuService {
    private static final Long PARENT_ID_MENU_DEFAULT = 0L;
    private static final String PARENT_ID_MENU_STR_DEFAULT = "0";

    @Override
    public List<MenuNodeVO> createMenuTreeRoot(boolean onlyMenu) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 当前用户具有的权限菜单
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roleCodes = new ArrayList<>();
        authorities.stream().forEach(item -> {
            roleCodes.add(item.getAuthority());
        });
        List<Long> menuIdsByRoleCodes = this.baseMapper.getMenuIdsByRoleCodes(roleCodes);

        List<BarberMenu> barberMenuLsit = this.baseMapper.selectList(new LambdaQueryWrapper<BarberMenu>()
                .eq(BarberMenu::getType, CommonEnum.MENU_TYPE_1.getCode())
                .eq(BarberMenu::getStatus, CommonEnum.NORMAL.getCode())
                .eq(BarberMenu::getParentId, PARENT_ID_MENU_DEFAULT)
                .orderByAsc(BarberMenu::getSort));
        List<MenuNodeVO> menuNodeVOList = new ArrayList<>();
        for (BarberMenu item : barberMenuLsit){
            // 没有权限
            if(!menuIdsByRoleCodes.contains(item.getId())){
                continue;
            }

            MenuNodeVO nodeVO = new MenuNodeVO();
            nodeVO.setId(String.valueOf(item.getId()));
            nodeVO.setLevel("1");
            nodeVO.setTitle(item.getName());
            nodeVO.setHref(item.getHref());
            nodeVO.setCss(item.getCss());
            nodeVO.setParentId("-1");
            nodeVO.setCheckArr(new CheckArrVO("0","0"));
            // 获得子节点
            nodeVO = getChild(nodeVO, onlyMenu, 1, menuIdsByRoleCodes);
            nodeVO.setLast(false);
            if(ObjectUtils.isEmpty(nodeVO.getChildren())){
                nodeVO.setLast(true);
            }
            menuNodeVOList.add(nodeVO);
        }
        return menuNodeVOList;
    }


    /**
     * 查询子节点(flag::仅查询菜单)
     * @author: longt
     * @Param: [nodeVO, type]
     * @return: cn.nicecoder.barbersys.entity.VO.MenuNodeVO
     * @date: 2021/3/11 下午2:32
     */
    public MenuNodeVO getChild(MenuNodeVO nodeVO, boolean onlyMenu, int level, List<Long> menuIdsByRoleCodes){
        level += 1;
        LambdaQueryWrapper queryWrapper  = new LambdaQueryWrapper<BarberMenu>()
                .ne(BarberMenu::getParentId, PARENT_ID_MENU_STR_DEFAULT)
                .eq(BarberMenu::getParentId, nodeVO.getId())
                .eq(BarberMenu::getStatus, CommonEnum.NORMAL.getCode())
                .eq(onlyMenu, BarberMenu::getType, CommonEnum.MENU_TYPE_1.getCode())
                .orderByAsc(BarberMenu::getSort);
        List<BarberMenu> childNode = this.baseMapper.selectList(queryWrapper);
        if(childNode.size() > 0){
            List<MenuNodeVO> menuNodeVOList = new ArrayList<>();
            for(BarberMenu barberMenu : childNode){
                // 没有权限
                if(!menuIdsByRoleCodes.contains(barberMenu.getId())){
                    continue;
                }

                MenuNodeVO menuNodeVO = new MenuNodeVO();
                menuNodeVO.setTitle(barberMenu.getName());
                menuNodeVO.setHref(barberMenu.getHref());
                menuNodeVO.setCss(barberMenu.getCss());
                menuNodeVO.setId(String.valueOf(barberMenu.getId()));
                menuNodeVO.setParentId(String.valueOf(barberMenu.getParentId()));
                menuNodeVO.setLevel(String.valueOf(level));
                menuNodeVO.setCheckArr(new CheckArrVO("0","0"));
                // 递归查询
                menuNodeVO = getChild(menuNodeVO, onlyMenu, level, menuIdsByRoleCodes);
                menuNodeVO.setLast(false);
                if(ObjectUtils.isEmpty(menuNodeVO.getChildren())){
                    menuNodeVO.setLast(true);
                }
                menuNodeVOList.add(menuNodeVO);
            }
            nodeVO.setChildren(menuNodeVOList);
        }
        return nodeVO;
    }

    @Override
    public String getFirstHref(MenuNodeVO nodeVO){
        if(ObjectUtil.isNotEmpty(nodeVO.getChildren())){
            List<MenuNodeVO> children = nodeVO.getChildren();
            for(MenuNodeVO menuNodeVO : children){
                getFirstHref(menuNodeVO);
            }
        }
        return nodeVO.getHref();
    }
}
