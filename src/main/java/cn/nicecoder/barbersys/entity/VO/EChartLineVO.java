package cn.nicecoder.barbersys.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * echarts折线图
 * @author: longt
 * @date: 2021/3/4 上午11:24
 */
@Data
public class EChartLineVO implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "会员")
    private long[] memberArr;

    @ApiModelProperty(value = "订单量")
    private long[] orderNumArr;

    @ApiModelProperty(value = "消费金额")
    private long[] AmountArr;

    public EChartLineVO(){
        memberArr = new long[12];
        orderNumArr = new long[12];
        AmountArr = new long[12];
    }
}
