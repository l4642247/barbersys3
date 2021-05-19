package cn.nicecoder.barbersys.controller;


import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.aspect.NoRepeatSubmit;
import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberMemberService;
import cn.nicecoder.barbersys.service.BarberOrderService;
import cn.nicecoder.barbersys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/member")
public class BarberMemberController {
    @Autowired
    BarberMemberService barberMemberService;

    @Autowired
    BarberOrderService barberOrderService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有会员",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size
            , @RequestParam(value = "name",required = false)String name
            , @RequestParam(value = "phone",required = false)String phone
            , @RequestParam(value = "idCard",required = false)String idCard){
        Page<BarberMember> page = new Page<>(current, size);
        Page<BarberMember> result = barberMemberService.page(page, new LambdaQueryWrapper<BarberMember>()
                .eq(BarberMember::getStatus, CommonEnum.NORMAL.getCode())
                .like(StrUtil.isNotBlank(name), BarberMember:: getName, name)
                .eq(StrUtil.isNotBlank(phone), BarberMember:: getPhone, phone)
                .like(StrUtil.isNotBlank(idCard), BarberMember:: getIdCard, idCard));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新会员",notes="")
    public Resp save(@RequestBody BarberMember barberMemberSave){
        barberMemberService.saveOrUpdate(barberMemberService.createMember(barberMemberSave));
        return Resp.success(barberMemberSave);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除会员",notes="")
    public Resp delete(@PathVariable("id") Long id){
        BarberMember barberMemberDelete = new BarberMember();
        barberMemberDelete.setId(id);
        barberMemberDelete.setStatus(CommonEnum.DELETED.getCode());
        barberMemberService.updateById(barberMemberDelete);
        return Resp.success(barberMemberDelete);
    }

    @PostMapping("/recharge")
    @ApiOperation(value="会员充值",notes="")
    public Resp recharge(@RequestBody BarberOrder barberOrderSave){
        BarberMember barberMember = barberOrderService.recharge(barberOrderSave);
        return Resp.success(barberMember);
    }

    @PostMapping("/expense")
    @ApiOperation(value="会员消费",notes="")
    @NoRepeatSubmit(location = "expense")
    public Resp expense(@RequestBody BarberOrder barberOrderSave){
        BarberMember barberMember = barberOrderService.expense(barberOrderSave);
        return Resp.success(barberMember);
    }
}

