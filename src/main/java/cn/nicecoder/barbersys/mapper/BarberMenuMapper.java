package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.BarberMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lon't
 * @since 2021-03-05
 */
public interface BarberMenuMapper extends BaseMapper<BarberMenu> {

    /**
     * @Description: 根据角色查询对应菜单id
     * @author: longt
     * @Param: [roleCodes]
     * @return: java.util.List<java.lang.Long>
     * @date: 2021/4/22 下午4:30
     */
    List<Long> getMenuIdsByRoleCodes(@Param("roleCodes")List roleCodes);
}
