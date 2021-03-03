package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.mapper.BarberOrderMapper;
import cn.nicecoder.barbersys.service.BarberMemberService;
import cn.nicecoder.barbersys.service.BarberOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BarberMember recharge(BarberOrder barberOrder) {
        barberOrder.setType(CommonEnum.ORDER_TYPE_1.getCode());
        this.save(barberOrder);
        //计算余额
        BarberMember barberMemberUpdate = barberMemberService.getById(barberOrder.getMemberId());
        Long balance = barberMemberUpdate.getBalance();
        barberMemberUpdate.setBalance(balance + barberOrder.getAmount());
        barberMemberService.updateById(barberMemberUpdate);
        return barberMemberUpdate;
    }

    @Override
    public BarberMember expense(BarberOrder barberOrder) {
        barberOrder.setType(CommonEnum.ORDER_TYPE_2.getCode());
        this.save(barberOrder);
        //计算余额
        BarberMember barberMemberUpdate = barberMemberService.getById(barberOrder.getMemberId());
        Long balance = barberMemberUpdate.getBalance();
        barberMemberUpdate.setBalance(balance - barberOrder.getAmount());
        barberMemberService.updateById(barberMemberUpdate);
        return barberMemberUpdate;
    }
}
