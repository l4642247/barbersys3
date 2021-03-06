package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.BarberUser;
import cn.nicecoder.barbersys.entity.DO.BarberUserDO;
import cn.nicecoder.barbersys.entity.VO.BarberUserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lon't
 * @since 2021-02-24
 */
public interface BarberUserMapper extends BaseMapper<BarberUser> {

    BarberUserVO getOneByUsername(@Param("username") String username);

    Page<BarberUserVO> listPageBarberUser(Page page, @Param("barberUserDO") BarberUserDO barberUserDO);
}
