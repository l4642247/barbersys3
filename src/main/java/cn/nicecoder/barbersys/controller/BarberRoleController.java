package cn.nicecoder.barbersys.controller;


import cn.nicecoder.barbersys.entity.BarberRole;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberRoleService;
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
public class BarberRoleController {

    @Autowired
    BarberRoleService BarberRoleService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有角色",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size){
        Page<BarberRole> page = new Page<>(current, size);
        Page<BarberRole> result = BarberRoleService.page(page, new LambdaQueryWrapper<BarberRole>().eq(
                BarberRole::getStatus, CommonEnum.NORMAL.getCode()));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新角色",notes="")
    public Resp save(@RequestBody BarberRole BarberRoleSave){
        BarberRoleService.saveOrUpdate(BarberRoleSave);
        return Resp.success(BarberRoleSave);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除角色",notes="")
    public Resp delete(@PathVariable("id") Long id){
        BarberRole BarberRoleDelete = new BarberRole();
        BarberRoleDelete.setId(id);
        BarberRoleDelete.setStatus(CommonEnum.DELETED.getCode());
        BarberRoleService.updateById(BarberRoleDelete);
        return Resp.success(BarberRoleDelete);
    }
}

