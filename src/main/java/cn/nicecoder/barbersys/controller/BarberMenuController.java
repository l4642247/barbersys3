package cn.nicecoder.barbersys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.nicecoder.barbersys.entity.BarberMenu;
import cn.nicecoder.barbersys.entity.comm.MenuTreeResp;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lon't
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/menu")
public class BarberMenuController {

    @Autowired
    BarberMenuService barberMenuService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有菜单",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size){
        Page<BarberMenu> page = new Page<>(current, size);
        Page<BarberMenu> result = barberMenuService.page(page, new LambdaQueryWrapper<BarberMenu>()
                .eq(BarberMenu::getStatus, CommonEnum.NORMAL.getCode())
                .orderByAsc(BarberMenu::getParentId,BarberMenu::getSort));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新菜单",notes="")
    public Resp save(@RequestBody Map menuMap){
        BarberMenu barberMenu = new BarberMenu();
        BeanUtil.fillBeanWithMap(menuMap, barberMenu, false);
        if(ObjectUtil.isNotEmpty(menuMap.get("selTree1_select_nodeId"))){
            barberMenu.setParentId(Long.parseLong(menuMap.get("selTree1_select_nodeId").toString()));
        }else{
            barberMenu.setParentId(0L);
        }
        barberMenuService.saveOrUpdate(barberMenu);
        return Resp.success(barberMenu);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除菜单",notes="")
    public Resp delete(@PathVariable("id") Long id){
        BarberMenu barberMenuDelete = new BarberMenu();
        barberMenuDelete.setId(id);
        barberMenuDelete.setStatus(CommonEnum.DELETED.getCode());
        barberMenuService.updateById(barberMenuDelete);
        return Resp.success(barberMenuDelete);
    }

    @PostMapping("/parentTree")
    @ApiOperation(value="查询所有菜单树",notes="")
    public MenuTreeResp parentTree(){
        return new MenuTreeResp(barberMenuService.createMenuTreeRoot(true));
    }

    @PostMapping("/checkTree")
    @ApiOperation(value="查询下拉多选菜单树",notes="")
    public MenuTreeResp checkTree(){
        return new MenuTreeResp(barberMenuService.createMenuTreeRoot(false));
    }

}

