package cn.nicecoder.barbersys.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: TODO
 * @author: longt
 * @date: 2021/3/19 下午3:55
 */
@Data
@AllArgsConstructor
public class CheckArrVO {
    /**
     * type必须从0
     */
    private String type;

    /**
     * checked属性的值范围为：0-未选中，1-选中，2-半选
     */
    private String checked;

}
