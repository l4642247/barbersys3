package cn.nicecoder.barbersys.controller;


import cn.nicecoder.barbersys.entity.BarberUserRole;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/role")
@Api(tags="角色相关接口Controller")
public class BarberUserRoleController {

    @Autowired
    BarberUserRoleService barberUserRoleService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有角色",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size){
        Page<BarberUserRole> page = new Page<>(current, size);
        Page<BarberUserRole> result = barberUserRoleService.page(page, new LambdaQueryWrapper<BarberUserRole>().eq(
                BarberUserRole::getStatus, CommonEnum.NORMAL.getCode()));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新角色",notes="")
    public Resp save(@RequestBody BarberUserRole barberUserRoleSave){
        barberUserRoleService.saveOrUpdate(barberUserRoleSave);
        return Resp.success(barberUserRoleSave);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除角色",notes="")
    public Resp delete(@PathVariable("id") Long id){
        BarberUserRole barberUserRoleDelete = new BarberUserRole();
        barberUserRoleDelete.setId(id);
        barberUserRoleDelete.setStatus(CommonEnum.DELETED.getCode());
        barberUserRoleService.updateById(barberUserRoleDelete);
        return Resp.success(barberUserRoleDelete);
    }
}

