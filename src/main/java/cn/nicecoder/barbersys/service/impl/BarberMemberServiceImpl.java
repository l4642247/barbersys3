package cn.nicecoder.barbersys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.exception.ServiceException;
import cn.nicecoder.barbersys.mapper.BarberMemberMapper;
import cn.nicecoder.barbersys.service.BarberMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxxxx
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

    @Override
    public void deleteMember(Long id) {
        BarberMember barberMemberDelete = barberMemberService.getById(id);
        if(barberMemberDelete.getBalance() >= 0){
            throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(), "余额大于0，无法删除！");
        }
        barberMemberDelete.setStatus(CommonEnum.DELETED.getCode());
        barberMemberService.updateById(barberMemberDelete);
    }

    @Override
    public void deleteBatchMember(String ids) {
        List<BarberMember> barberMemberList = new ArrayList<>();
        for(String id : ids.split(",")){
            if(StrUtil.isNotEmpty(id)){
                BarberMember barberMemberDelete = barberMemberService.getById(id);
                if(barberMemberDelete.getBalance() >= 0){
                    throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(), "余额大于0，无法删除！");
                }
                barberMemberDelete.setStatus(CommonEnum.DELETED.getCode());
                barberMemberList.add(barberMemberDelete);
            }
        }
        barberMemberService.updateBatchById(barberMemberList);
    }
}
