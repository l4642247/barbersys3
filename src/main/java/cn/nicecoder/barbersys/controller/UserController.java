package cn.nicecoder.barbersys.controller;
import cn.nicecoder.barbersys.entity.comm.Resp;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  会员控制器
 * </p>
 *
 * @author lon't
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/user")
@Api(tags="会员相关接口Controller")
public class UserController {

    @GetMapping("/list")
    public Resp admin(){
        return Resp.ok(null);
    }

}

