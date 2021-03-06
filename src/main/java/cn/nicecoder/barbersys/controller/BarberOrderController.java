package cn.nicecoder.barbersys.controller;


import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.DO.BarberOrderDO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderVO;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.service.BarberOrderService;
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
@RequestMapping("/order")
public class BarberOrderController {
    @Autowired
    BarberOrderService barberOrderService;

    @GetMapping("/page")
    @ApiOperation(value = "查询所有订单", notes = "")
    public Resp page(@RequestParam("page") Long current, @RequestParam("limit") Long size
            , @RequestParam(value = "memberName",required = false) String memberName, @RequestParam(value = "barberName",required = false) String barberName
            , @RequestParam(value = "dataRange",required = false) String dataRange, @RequestParam(value = "type",required = false) Integer type) {
        Page<BarberOrder> page = new Page<>(current, size);
        BarberOrderDO barberOrderDO = new BarberOrderDO();
        barberOrderDO.setMemberName(memberName);
        barberOrderDO.setBarberName(barberName);
        barberOrderDO.setType(type);
        if(StrUtil.isNotEmpty(dataRange)){
            barberOrderDO.setDateStart(dataRange.split("~")[0].trim());
            barberOrderDO.setDateEnd(dataRange.split("~")[1].trim());
        }
        Page<BarberOrderVO> result = barberOrderService.listPageBarberOrder(page, barberOrderDO);
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存/更新订单", notes = "")
    public Resp save(@RequestBody BarberOrder barberOrderSave) {
        barberOrderService.saveOrUpdate(barberOrderSave);
        return Resp.success(barberOrderSave);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除订单", notes = "")
    public Resp delete(@PathVariable("id") Long id) {
        BarberOrder barberOrderDelete = new BarberOrder();
        barberOrderDelete.setId(id);
        barberOrderDelete.setStatus(CommonEnum.DELETED.getCode());
        barberOrderService.updateById(barberOrderDelete);
        return Resp.success(barberOrderDelete);
    }
}

