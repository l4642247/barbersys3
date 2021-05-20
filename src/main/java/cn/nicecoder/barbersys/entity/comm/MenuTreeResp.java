package cn.nicecoder.barbersys.entity.comm;

import cn.nicecoder.barbersys.entity.VO.MenuNodeVO;
import cn.nicecoder.barbersys.enums.CommonEnum;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 树接口返回
 * @author: xxxxx
 * @Param:
 * @return:
 * @date: 2020/12/24 上午11:13
 */
@Data
public class MenuTreeResp {

    private Status status;
    private List<MenuNodeVO> data;

    public MenuTreeResp(List<MenuNodeVO> data){
        this.data = data;
        this.status = new Status(200, "操作成功");
    }
}

