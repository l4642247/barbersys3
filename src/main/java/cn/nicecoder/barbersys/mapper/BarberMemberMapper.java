package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface BarberMemberMapper extends BaseMapper<BarberMember> {
    List<OverviewPO> memberStatisMonth();

    List<OverviewPO> memberStatisCurrentMonth();
}
