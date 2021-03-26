package cn.nicecoder.barbersys.entity.VO;

import lombok.Data;

import java.util.List;

/**
 * 菜单树节点
 * @author: longt
 * @date: 2021/3/10 下午3:56
 */
@Data
public class MenuNodeVO {
    private String id;
    private String title;
    private String parentId;
    private CheckArrVO checkArr;
    private String level;
    private Boolean last;
    List<MenuNodeVO> children;
}
