package cn.nicecoder.barbersys.enums;

/**
 * StatusEnum
 * @author: longt
 * @date: 2020/12/22 上午8:47
 */
public enum CommonEnum {

    NORMAL(1,"正常"),
    DELETED(0,"已删除"),

    // layui返回值
    RESP_LAYUI_OK(0,"成功"),
    RESP_LAYUI_EMPTY(201,"无数据"),

    ORDER_TYPE_1(1,"充值"),
    ORDER_TYPE_2(2,"消费"),
    ;

    CommonEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}