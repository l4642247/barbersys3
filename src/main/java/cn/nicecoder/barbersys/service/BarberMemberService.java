package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberMember;
import cn.nicecoder.barbersys.entity.PO.OverviewPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface BarberMemberService extends IService<BarberMember> {
    /**
     * 创建会员用户卡号
     * @author: xxxxx
     * @Param: [barberMember]
     * @return: cn.nicecoder.barbersys.entity.BarberMember
     * @date: 2021/2/27 下午3:05
     */
    public BarberMember createMember(BarberMember barberMember);


    /**
     * 获取会员人数
     * @author: xxxxx
     * @Param: []
     * @return: cn.nicecoder.barbersys.entity.PO.OverviewPO
     * @date: 2021/5/8 下午3:25
     */
    public List<OverviewPO> memberStatisMonth();

    /**
     * 获取当会员新增
     * @author: xxxxx
     * @Param: []
     * @return: cn.nicecoder.barbersys.entity.PO.OverviewPO
     * @date: 2021/5/8 下午3:25
     */
    public List<OverviewPO> memberStatisCurrentMonth();


}
