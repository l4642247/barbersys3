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

    BarberUserVO getOneByUsername(@Param("username") String username);

    Page<BarberUserVO> listPageBarberUser(Page page, @Param("barberUserDO") SysUserDO barberUserDO);
}
