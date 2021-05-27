package cn.nicecoder.barbersys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
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
@ApiModel(value="SysUser对象", description="")
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("邮件")
    private String email;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("登录用户名")
    private String username;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("描述")
    private String description;

    @TableField(value = "STATUS", fill = FieldFill.INSERT)
    @ApiModelProperty("状态")
    private Integer status;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
