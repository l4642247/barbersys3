package cn.nicecoder.barbersys.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单统计视图
 * @author: xxxxx
 * @date: 2021/3/4 上午11:24
 */
@Data
public class BarberOrderStatisVO implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "当天消费总计")
    private Long amountDay;

    @ApiModelProperty(value = "日订单量")
    private Long orderNumDay;

    @ApiModelProperty(value = "月会员量")
    private Long memberNumMonth;

    @ApiModelProperty(value = "月订单量")
    private Long orderNumMonth;

    @ApiModelProperty(value = "月会员消费")
    private Long amountMemberMonth;

    @ApiModelProperty(value = "消费总计")
    private Long amountTotal;

    @ApiModelProperty(value = "月现金消费")
    private Long amountCashMonth;

    public BarberOrderStatisVO(){
        amountDay = 0L;
        orderNumDay = 0L;
        memberNumMonth = 0L;
        orderNumMonth = 0L;
        amountMemberMonth = 0L;
        amountTotal = 0L;
        amountCashMonth = 0L;
    }
}
