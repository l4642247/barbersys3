package cn.nicecoder.barbersys.mapper;

import cn.nicecoder.barbersys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxxxx
 * @since 2021-03-05
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色查询对应菜单id
     * @author: xxxxx
     * @Param: [roleCodes]
     * @return: java.util.List<java.lang.Long>
     * @date: 2021/4/22 下午4:30
     */
    List<Long> getMenuIdsByRoleCodes(@Param("roleCodes")List roleCodes);
}
