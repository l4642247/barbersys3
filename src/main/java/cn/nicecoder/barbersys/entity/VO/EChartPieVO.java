package cn.nicecoder.barbersys.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * echarts饼图
 * @author: longt
 * @date: 2021/3/4 上午11:24
 */
@Data
public class EChartPieVO implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "金额")
    private Long value;

    @ApiModelProperty(value = "姓名")
    private String name;

}
