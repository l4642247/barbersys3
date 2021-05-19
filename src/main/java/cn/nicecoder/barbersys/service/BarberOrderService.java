package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.DO.BarberOrderDO;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderStatisVO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderVO;
import cn.nicecoder.barbersys.entity.VO.EChartLineVO;
import cn.nicecoder.barbersys.entity.VO.EChartPieVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberOrderService extends IService<BarberOrder> {
    /**
     * @Description: 充值
     * @author: longt
     * @Param: [barberOrder]
     * @return: cn.nicecoder.barbersys.entity.BarberMember
     * @date: 2021/2/27 上午11:54
     */
    @Transactional
    BarberMember recharge(BarberOrder barberOrder);


    /**
     * @Description: 消费
     * @author: longt
     * @Param: [barberOrder]
     * @return: cn.nicecoder.barbersys.entity.BarberMember
     * @date: 2021/2/27 上午11:54
     */
    @Transactional
    BarberMember expense(BarberOrder barberOrder);

    /**
     * 分页查询
     * @param page
     * @param barberOrderDO
     * @return
     */
    Page<BarberOrderVO> listPageBarberOrder(Page<BarberOrder> page, BarberOrderDO barberOrderDO);

    /**
     * 保存订单
     * @author: longt
     * @Param: [barberOrderSave]
     * @return: void
     * @date: 2021/5/7 下午2:34
     */
    void saveOne(BarberOrder barberOrderSave);


    /**
     * 数据概览
     * @author: longt
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.VO.BarberOrderStatisVO>
     * @date: 2021/5/8 上午10:09
     */
    BarberOrderStatisVO getOverviewData();

    /**
     * 获取折线图数据
     * @author: longt
     * @Param: []
     * @return: cn.nicecoder.barbersys.entity.VO.EChartLineVO
     * @date: 2021/5/8 下午4:16
     */
    EChartLineVO getEchartsLineData();

    /**
     * 获取饼图数据
     * @author: longt
     * @Param: []
     * @return: cn.nicecoder.barbersys.entity.VO.EChartPieVO
     * @date: 2021/5/8 下午4:17
     */
    List<EChartPieVO> getEChartPieData();

}
