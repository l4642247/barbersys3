package cn.nicecoder.barbersys.entity.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单统计视图
 * @author: longt
 * @date: 2021/3/4 上午11:24
 */
@Data
public class OverviewPO implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer type;

    private Long countNum;

    private Long amount;

    private String createTime;
}
