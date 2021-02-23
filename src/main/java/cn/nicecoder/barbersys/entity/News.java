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
import lombok.ToString;

import javax.servlet.annotation.HandlesTypes;

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
@ApiModel(value="News对象", description="")
@ToString
public class News implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String cover;

    private String title;

    private String content;

    private Integer catalog;

    private String author;

    @TableField(value = "CLICK", fill = FieldFill.INSERT)
    private Integer click;

    @TableField(value = "STATUS", fill = FieldFill.INSERT)
    private String status;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

}
