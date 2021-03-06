package cn.nicecoder.barbersys.controller;


import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.DO.PasswordDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/user")
public class BarberUserController {

    @Autowired
    BarberUserService barberUserService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有用户",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size
            , @RequestParam(value = "name",required = false)String name
            , @RequestParam(value = "phone",required = false)String phone
            , @RequestParam(value = "idCard",required = false)String idCard){
        Page<BarberUser> page = new Page<>(current, size);
        BarberUserDO barberUserDO = new BarberUserDO();
        barberUserDO.setName(name);
        barberUserDO.setPhone(phone);
        barberUserDO.setIdCard(idCard);
        Page<BarberUserVO> result = barberUserService.listPageBarberUser(page, barberUserDO);
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新用户",notes="")
    public Resp save(@RequestBody BarberUser barberUserSave){
        barberUserService.saveOrUpdate(barberUserService.createBarberUser(barberUserSave));
        return Resp.success(barberUserSave);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除用户",notes="")
    public Resp delete(@PathVariable("id") Long id){
        BarberUser barberUserDelete = new BarberUser();
        barberUserDelete.setId(id);
        barberUserDelete.setStatus(CommonEnum.DELETED.getCode());
        barberUserService.updateById(barberUserDelete);
        return Resp.success(barberUserDelete);
    }

    @PostMapping("/passwordModify")
    @ApiOperation(value="修改密码",notes="")
    public Resp passwordModify(@RequestBody PasswordDO passwordDO){
        BarberUser barberUser = barberUserService.passwordModify(passwordDO.getOldPassword(), passwordDO.getPassword());
        return Resp.success(barberUser);
    }

}

