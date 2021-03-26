package cn.nicecoder.barbersys.service;

import cn.nicecoder.barbersys.entity.BarberMenu;
import cn.nicecoder.barbersys.entity.VO.MenuNodeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2021-03-05
 */
public interface BarberMenuService extends IService<BarberMenu> {

    /**
     * 查询根菜单
     * @author: longt
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.VO.MenuNodeVO>
     * @date: 2021/3/11 上午10:23
     */
    List<MenuNodeVO> createMenuTreeRoot(boolean flag);
}
