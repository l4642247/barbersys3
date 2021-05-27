package cn.nicecoder.barbersys.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单树节点
 * @author: xxxxx
 * @date: 2021/3/10 下午3:56
 */
@Data
public class MenuNodeVO {
    @ApiModelProperty(value = "节点id")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "链接")
    private String href;

    @ApiModelProperty(value = "图标")
    private String css;

    @ApiModelProperty(value = "级别")
    private String level;

    @ApiModelProperty(value = "是否是叶子节点")
    private Boolean last;

    @ApiModelProperty(value = "父节点id")
    private String parentId;

    @ApiModelProperty(value = "选中")
    private CheckArrVO checkArr;

    @ApiModelProperty(value = "孩子节点")
    List<MenuNodeVO> children;
}
