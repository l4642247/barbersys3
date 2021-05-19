package cn.nicecoder.barbersys.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.DO.BarberOrderDO;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderStatisVO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderVO;
import cn.nicecoder.barbersys.entity.VO.EChartLineVO;
import cn.nicecoder.barbersys.entity.VO.EChartPieVO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.exception.ServiceException;
import cn.nicecoder.barbersys.mapper.BarberOrderMapper;
import cn.nicecoder.barbersys.service.BarberMemberService;
import cn.nicecoder.barbersys.service.BarberOrderService;
import cn.nicecoder.barbersys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class BarberOrderServiceImpl extends ServiceImpl<BarberOrderMapper, BarberOrder> implements BarberOrderService {

    @Autowired
    BarberMemberService barberMemberService;

    @Autowired
    SysUserService sysUserService;

    @Override
    public BarberMember recharge(BarberOrder barberOrder) {
        Long userId = sysUserService.getCurrentUser().getId();
        barberOrder.setType(CommonEnum.ORDER_TYPE_1.getCode());
        barberOrder.setCreator(userId);
        this.save(barberOrder);
        //计算余额
        BarberMember barberMemberUpdate = barberMemberService.getById(barberOrder.getMemberId());
        Long balance = barberMemberUpdate.getBalance();
        barberMemberUpdate.setBalance(balance + barberOrder.getAmount());
        barberMemberUpdate.setUpdator(userId);
        barberMemberService.updateById(barberMemberUpdate);
        return barberMemberUpdate;
    }

    @Override
    public BarberMember expense(BarberOrder barberOrder) {
        Long userId = sysUserService.getCurrentUser().getId();
        barberOrder.setType(CommonEnum.ORDER_TYPE_2.getCode());
        barberOrder.setCreator(userId);
        this.save(barberOrder);
        //计算余额
        BarberMember barberMemberUpdate = barberMemberService.getById(barberOrder.getMemberId());
        Long balance = barberMemberUpdate.getBalance() - barberOrder.getAmount();
        if(balance < 0){
            throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(), "余额不足！");
        }
        barberMemberUpdate.setBalance(balance);
        barberMemberUpdate.setUpdator(userId);
        barberMemberService.updateById(barberMemberUpdate);
        return barberMemberUpdate;
    }

    @Override
    public Page<BarberOrderVO> listPageBarberOrder(Page<BarberOrder> page, BarberOrderDO barberOrderDO) {
        return this.baseMapper.listPageBarberOrder(page, barberOrderDO);
    }

    @Override
    public void saveOne(BarberOrder barberOrderSave) {
        Long userId = sysUserService.getCurrentUser().getId();
        barberOrderSave.setType(CommonEnum.ORDER_TYPE_3.getCode());
        barberOrderSave.setCreator(userId);
        this.saveOrUpdate(barberOrderSave);
    }

    @Override
    public BarberOrderStatisVO getOverviewData() {
        List<OverviewPO> dayList = this.baseMapper.orderStatisDay();
        BarberOrderStatisVO result = new BarberOrderStatisVO();
        for (OverviewPO item: dayList) {
            Long amountDay = result.getAmountDay();
            Long orderNumDay = result.getOrderNumDay();
            if(CommonEnum.ORDER_TYPE_2.getCode().equals(item.getType())
                    || CommonEnum.ORDER_TYPE_3.getCode().equals(item.getType())){
                result.setAmountDay(amountDay + item.getAmount());
                result.setOrderNumDay(orderNumDay + item.getCountNum());
            }
        }
        List<OverviewPO> monthList = this.baseMapper.orderStatisCurrentMonth();
        for (OverviewPO item: monthList) {
            Long orderNumMonth = result.getOrderNumMonth();
            Long amountMemberMonth = result.getAmountMemberMonth();
            Long amountCashMonth = result.getAmountCashMonth();
            Long amountTotal = result.getAmountTotal();
            if(CommonEnum.ORDER_TYPE_2.getCode().equals(item.getType())
                    || CommonEnum.ORDER_TYPE_3.getCode().equals(item.getType())){
                result.setOrderNumMonth(orderNumMonth + item.getCountNum());
                result.setAmountTotal(amountTotal + item.getAmount());
                if(CommonEnum.ORDER_TYPE_2.getCode().equals(item.getType())){
                    result.setAmountMemberMonth(amountMemberMonth + item.getAmount());
                }else{
                    result.setAmountCashMonth(amountCashMonth + item.getAmount());
                }
            }
        }
        List<OverviewPO> memberNewMonth = barberMemberService.memberStatisCurrentMonth();
        for (OverviewPO item : memberNewMonth){
            if(Integer.parseInt(item.getCreateTime()) == DateUtil.month(new Date())+1){
                result.setMemberNumMonth(item.getCountNum());
            }
        }
        return result;
    }

    @Override
    public EChartLineVO getEchartsLineData() {
        EChartLineVO eChartLineVO = new EChartLineVO();
        List<OverviewPO> overviewPOS = barberMemberService.memberStatisMonth();
        for(OverviewPO item : overviewPOS){
            int index = Integer.parseInt(item.getCreateTime()) - 1;
            eChartLineVO.getMemberArr()[index] = item.getCountNum();
        }
        List<OverviewPO> overviewPOS1 = this.baseMapper.orderStatisMonth();
        for(OverviewPO item : overviewPOS1){
            int index = Integer.parseInt(item.getCreateTime()) - 1;
            eChartLineVO.getAmountArr()[index] = item.getAmount();
            eChartLineVO.getOrderNumArr()[index] = item.getCountNum();
        }
        return eChartLineVO;
    }

    @Override
    public List<EChartPieVO> getEChartPieData() {
        return this.baseMapper.echartsPieData();
    }

}
