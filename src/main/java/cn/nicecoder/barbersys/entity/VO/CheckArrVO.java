package cn.nicecoder.barbersys.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单勾选视图
 * @author: xxxxx
 * @date: 2021/3/19 下午3:55
 */
@Data
@AllArgsConstructor
public class CheckArrVO implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * type必须从0
     */
    private String type;

    /**
     * checked属性的值范围为：0-未选中，1-选中，2-半选
     */
    private String checked;

}
