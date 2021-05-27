package cn.nicecoder.barbersys.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 下拉框视图
 * @author: xxxxx
 * @date: 2021/3/7 下午5:50
 */
@Data
public class SelectVO {
    @ApiModelProperty(value = "节点名称")
    String name;

    @ApiModelProperty(value = "选中状态")
    Long value;
}
