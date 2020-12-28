package cn.nicecoder.newssys.entity;

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
 * @author lon't
 * @since 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsCatalog对象", description="")
public class NewsCatalog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField(value = "STATUS", fill = FieldFill.INSERT)
    private String status;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "UPDATE_TIME",fill = FieldFill.UPDATE)
    private Date updateTime;


}
