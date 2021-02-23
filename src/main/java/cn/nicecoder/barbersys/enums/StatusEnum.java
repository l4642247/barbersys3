package cn.nicecoder.barbersys.enums;

/**
 * StatusEnum
 * @author: longt
 * @date: 2020/12/22 上午8:47
 */
public enum StatusEnum {

    NORMAL("1","正常"),
    DELETED("0","已删除"),
    ;

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
