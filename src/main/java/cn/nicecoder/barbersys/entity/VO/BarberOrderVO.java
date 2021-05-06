package cn.nicecoder.barbersys.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息视图
 * @author: longt
 * @date: 2021/3/4 上午11:24
 */
@Data
public class BarberOrderVO implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "会员名")
    private String memberName;

    @ApiModelProperty(value = "员工名")
    private String barberName;

    @ApiModelProperty(value = "消费类型")
    private Integer type;

    @ApiModelProperty(value = "消费金额")
    private Integer amount;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人id")
    private Long creator;

    @ApiModelProperty(value = "创建人")
    private String creatorName;
}
