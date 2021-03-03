package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberMemberService extends IService<BarberMember> {
    /**
     * @Description: 创建会员用户卡号
     * @author: longt
     * @Param: [barberMember]
     * @return: cn.nicecoder.barbersys.entity.BarberMember
     * @date: 2021/2/27 下午3:05
     */
    public BarberMember createMember(BarberMember barberMember);
}
