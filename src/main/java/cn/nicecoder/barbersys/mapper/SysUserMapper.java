package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.SysUser;
import cn.nicecoder.barbersys.entity.DO.SysUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据username查询单个系统用户
     * @author: xxxxx
     * @Param: [username]
     * @return: cn.nicecoder.barbersys.entity.VO.BarberUserVO
     * @date: 2021/5/20 上午10:34
     */
    BarberUserVO getOneByUsername(@Param("username") String username);

    /**
     * 分页查询系统用户信息
     * @author: xxxxx
     * @Param: [page, barberUserDO]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.nicecoder.barbersys.entity.VO.BarberUserVO>
     * @date: 2021/5/20 上午10:34
     */
    Page<BarberUserVO> listPageBarberUser(Page page, @Param("barberUserDO") SysUserDO barberUserDO);
}
