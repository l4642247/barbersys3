package cn.nicecoder.barbersys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import cn.nicecoder.barbersys.mapper.BarberMemberMapper;
import cn.nicecoder.barbersys.service.BarberMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BarberMemberServiceImpl extends ServiceImpl<BarberMemberMapper, BarberMember> implements BarberMemberService {

    @Autowired
    BarberMemberService barberMemberService;

    @Override
    public BarberMember createMember(BarberMember barberMember) {
        int count = barberMemberService.count();
        String cardNo = StrUtil.fillBefore(String.valueOf(count+1), '0',6);
        barberMember.setCardNo(cardNo);
        return barberMember;
    }

    @Override
    public List<OverviewPO> memberStatisMonth() {
        return this.baseMapper.memberStatisMonth();
    }

    @Override
    public List<OverviewPO> memberStatisCurrentMonth() {
        return this.baseMapper.memberStatisCurrentMonth();
    }
}
