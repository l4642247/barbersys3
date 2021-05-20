package cn.nicecoder.barbersys.entity.DO;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 菜单数据传输对象
 * @author: xxxxx
 * @date: 2021/2/24 下午11:27
 */
@Data
public class BarberMenuDO implements Serializable {
    private static final long serialVersionUID=1L;
    private String css;
    private String href;
    private Long id;
    private Long selTree1_select_nodeId;
    private String name;
    private int sort;
    private int type;
}
