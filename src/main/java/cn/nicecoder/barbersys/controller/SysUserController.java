package cn.nicecoder.barbersys.controller;


import cn.nicecoder.barbersys.aspect.NoRepeatSubmit;
import cn.nicecoder.barbersys.entity.DO.PasswordDO;
import cn.nicecoder.barbersys.entity.DO.SysUserDO;
import cn.nicecoder.barbersys.entity.SysUser;
import cn.nicecoder.barbersys.entity.VO.SysUserVO;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  用户前端控制器
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/user")
@Api(tags="系统用户相关接口")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有用户",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size
            , @RequestParam(value = "name",required = false)String name
            , @RequestParam(value = "phone",required = false)String phone
            , @RequestParam(value = "idCard",required = false)String idCard){
        Page<SysUser> page = new Page<>(current, size);
        SysUserDO barberUserDO = new SysUserDO();
        barberUserDO.setName(name);
        barberUserDO.setPhone(phone);
        barberUserDO.setIdCard(idCard);
        Page<SysUserVO> result = sysUserService.listPageBarberUser(page, barberUserDO);
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新用户",notes="")
    public Resp save(@RequestBody SysUserDO barberUserSave){
        sysUserService.saveOne(barberUserSave);
        return Resp.success(barberUserSave);
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value="更新状态",notes="")
    @NoRepeatSubmit
    public Resp updateStatus(@RequestParam("id")Long id, @RequestParam("status")Integer status){
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setStatus(status);
        sysUserService.updateById(sysUser);
        return Resp.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除用户",notes="")
    public Resp delete(@PathVariable("id") Long id){
        SysUser sysUserDelete = new SysUser();
        sysUserDelete.setId(id);
        sysUserDelete.setStatus(CommonEnum.DELETED.getCode());
        sysUserService.updateById(sysUserDelete);
        return Resp.success(sysUserDelete);
    }

    @PostMapping("/passwordModify")
    @ApiOperation(value="修改密码",notes="")
    public Resp passwordModify(@RequestBody PasswordDO passwordDO){
        SysUser sysUser = sysUserService.passwordModify(passwordDO.getOldPassword(), passwordDO.getPassword());
        return Resp.success(sysUser);
    }

}

