package cn.nicecoder.barbersys.entity.PO;

import lombok.Data;

/**
 * 概览结果集
 * @author: xxxxx
 * @date: 2021/3/4 上午11:24
 */
@Data
public class OverviewPO {
    private Integer type;
    private Long countNum;
    private Long amount;
    private String createTime;
}
