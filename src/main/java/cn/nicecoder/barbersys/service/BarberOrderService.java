package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.BarberOrder;
import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.DO.BarberOrderDO;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberOrderVO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

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
    public BarberMember recharge(BarberOrder barberOrder);


    /**
     * @Description: 消费
     * @author: longt
     * @Param: [barberOrder]
     * @return: cn.nicecoder.barbersys.entity.BarberMember
     * @date: 2021/2/27 上午11:54
     */
    public BarberMember expense(BarberOrder barberOrder);

    /**
     * 分页查询
     * @param page
     * @param barberOrderDO
     * @return
     */
    Page<BarberOrderVO> listPageBarberOrder(Page<BarberOrder> page, BarberOrderDO barberOrderDO);
}
