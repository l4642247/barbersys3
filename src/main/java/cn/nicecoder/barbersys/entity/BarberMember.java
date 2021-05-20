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
@ApiModel(value="BarberMember对象", description="")
public class BarberMember implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cardNo;

    private String name;

    private Integer sex;

    private String idCard;

    private String phone;

    private String avatar;

    private Long balance;

    @TableField(value = "STATUS", fill = FieldFill.INSERT)
    private Integer status;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    private Long creator;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    private Long updator;


}
