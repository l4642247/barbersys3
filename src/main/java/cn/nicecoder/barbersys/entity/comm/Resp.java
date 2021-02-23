package cn.nicecoder.barbersys.entity.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

/**
 * Rest接口通用返回
 * @author: longt
 * @Param:
 * @return:
 * @date: 2020/12/24 上午11:13
 */
@Data
@AllArgsConstructor
@ToString
public class Resp {
    private final Integer code;
    private final String msg;
    private final Object data;
    private final LocalDateTime timestamp = LocalDateTime.now();
	public static final Resp OK = new Resp(HttpStatus.OK);
    public static final Resp FAIL = new Resp(HttpStatus.INTERNAL_SERVER_ERROR);

    public static Resp ok(Object data) {
        return new Resp(HttpStatus.OK, data);
    }
    public static Resp fail(String msg) {
        return new Resp(500, msg);
    }

    public Resp(Integer code, String msg) {
        this(code, msg, null);
    }

    public Resp(HttpStatus httpStatus) {
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public Resp(HttpStatus httpStatus, Object data) {
        this(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }
}

