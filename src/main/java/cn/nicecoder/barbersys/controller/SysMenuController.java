package cn.nicecoder.barbersys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.SysMenu;
import cn.nicecoder.barbersys.entity.comm.MenuTreeResp;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.SysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  菜单前端控制器
 * </p>
 *
 * @author xxxxx
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有菜单",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size,
                     @RequestParam(value = "name", required = false)String name, @RequestParam(value = "type", required = false)Long type){
        Page<SysMenu> page = new Page<>(current, size);
        Page<SysMenu> result = sysMenuService.page(page, new LambdaQueryWrapper<SysMenu>()
                .like(StrUtil.isNotEmpty(name), SysMenu::getName, name)
                .eq(ObjectUtil.isNotEmpty(type), SysMenu::getType, type)
                .orderByAsc(SysMenu::getParentId, SysMenu::getSort));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新菜单",notes="")
    public Resp save(@RequestBody Map menuMap){
        SysMenu sysMenu = new SysMenu();
        BeanUtil.fillBeanWithMap(menuMap, sysMenu, true);
        if(ObjectUtil.isNotEmpty(menuMap.get("selTree1_select_nodeId"))){
            sysMenu.setParentId(Long.parseLong(menuMap.get("selTree1_select_nodeId").toString()));
        }else{
            sysMenu.setParentId(0L);
        }
        if(ObjectUtil.isNotEmpty(menuMap.get("status")) && "on".equals(menuMap.get("status"))){
            sysMenu.setStatus(1);
        }else{
            sysMenu.setStatus(0);
        }
        sysMenuService.saveOrUpdate(sysMenu);
        return Resp.success(sysMenu);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除菜单",notes="")
    public Resp delete(@PathVariable("id") Long id){
        SysMenu sysMenuDelete = new SysMenu();
        sysMenuDelete.setId(id);
        sysMenuDelete.setStatus(CommonEnum.DELETED.getCode());
        sysMenuService.updateById(sysMenuDelete);
        return Resp.success(sysMenuDelete);
    }

    @PostMapping("/parentTree")
    @ApiOperation(value="查询所有菜单树",notes="")
    public MenuTreeResp parentTree(){
        return new MenuTreeResp(sysMenuService.createMenuTreeRoot(true, false));
    }

    @PostMapping("/checkTree")
    @ApiOperation(value="查询下拉多选菜单树",notes="")
    public MenuTreeResp checkTree(){
        return new MenuTreeResp(sysMenuService.createMenuTreeRoot(false, false));
    }

}

