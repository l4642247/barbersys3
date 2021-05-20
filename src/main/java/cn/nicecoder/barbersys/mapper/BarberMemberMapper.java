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
    /**
     * 查询本年每月的分析数据
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.OverviewPO>
     * @date: 2021/5/20 上午10:30
     */
    List<OverviewPO> memberStatisMonth();

    /**
     * 查询当月的分析数据
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.OverviewPO>
     * @date: 2021/5/20 上午10:31
     */
    List<OverviewPO> memberStatisCurrentMonth();
}
