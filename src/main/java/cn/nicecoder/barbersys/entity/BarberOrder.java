package cn.nicecoder.barbersys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BarberOrder对象", description="")
public class BarberOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cardNo;

    private Long memberId;

    private Long barberId;

    @ApiModelProperty(value = "1、卡充值 2、卡消费 3、现金消费 4、退款")
    private Integer type;

    private Integer amount;

    @TableField(value = "STATUS", fill = FieldFill.INSERT)
    private Integer status;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    private Long creator;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    private Long updator;


}
