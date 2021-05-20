package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.DO.BarberOrderDO;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderVO;
import cn.nicecoder.barbersys.entity.VO.EChartPieVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface BarberOrderMapper extends BaseMapper<BarberOrder> {

    /**
     * 分页查询订单
     * @author: xxxxx
     * @Param: [page, barberOrderDO]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.nicecoder.barbersys.entity.VO.BarberOrderVO>
     * @date: 2021/5/20 上午10:32
     */
    Page<BarberOrderVO> listPageBarberOrder(Page page, @Param("barberOrderDO") BarberOrderDO barberOrderDO);

    /**
     * 当天的订单分析数据
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.OverviewPO>
     * @date: 2021/5/20 上午10:32
     */
    List<OverviewPO> orderStatisDay();

    /**
     * 本年每个月的订单分析数据
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.OverviewPO>
     * @date: 2021/5/20 上午10:33
     */
    List<OverviewPO> orderStatisMonth();

    /**
     * 当月的订单分析数据
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.PO.OverviewPO>
     * @date: 2021/5/20 上午10:33
     */
    List<OverviewPO> orderStatisCurrentMonth();

    /**
     * 饼图数据
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.VO.EChartPieVO>
     * @date: 2021/5/20 上午10:33
     */
    List<EChartPieVO> echartsPieData();
}
