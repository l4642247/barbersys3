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
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberOrderMapper extends BaseMapper<BarberOrder> {

    Page<BarberOrderVO> listPageBarberOrder(Page page, @Param("barberOrderDO") BarberOrderDO barberOrderDO);

    List<OverviewPO> orderStatisDay();

    List<OverviewPO> orderStatisMonth();

    List<OverviewPO> orderStatisCurrentMonth();

    List<EChartPieVO> echartsPieData();
}
