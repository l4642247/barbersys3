package cn.nicecoder.barbersys.service.impl;

import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.DO.BarberOrderDO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderVO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.mapper.BarberOrderMapper;
import cn.nicecoder.barbersys.service.BarberMemberService;
import cn.nicecoder.barbersys.service.BarberOrderService;
import cn.nicecoder.barbersys.service.BarberUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    BarberUserService barberUserService;

    @Override
    public BarberMember recharge(BarberOrder barberOrder) {
        Long userId = barberUserService.getCurrentUser().getId();
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
        Long userId = barberUserService.getCurrentUser().getId();
        barberOrder.setType(CommonEnum.ORDER_TYPE_2.getCode());
        barberOrder.setCreator(userId);
        this.save(barberOrder);
        //计算余额
        BarberMember barberMemberUpdate = barberMemberService.getById(barberOrder.getMemberId());
        Long balance = barberMemberUpdate.getBalance();
        barberMemberUpdate.setBalance(balance - barberOrder.getAmount());
        barberMemberUpdate.setUpdator(userId);
        barberMemberService.updateById(barberMemberUpdate);
        return barberMemberUpdate;
    }

    @Override
    public Page<BarberOrderVO> listPageBarberOrder(Page<BarberOrder> page, BarberOrderDO barberOrderDO) {
        return this.baseMapper.listPageBarberOrder(page, barberOrderDO);
    }
}
