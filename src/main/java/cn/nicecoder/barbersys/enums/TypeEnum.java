package cn.nicecoder.barbersys.enums;

/**
 * TypeEnum
 * @author: longt
 * @date: 2020/12/22 上午8:47
 */
public enum TypeEnum {

    COMMENT_TO_NEWS("0","评论新闻"),
    COMMENT_TO_COMM("1","评论评论"),
    ;

    TypeEnum(String code, String desc) {
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
