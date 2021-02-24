package cn.nicecoder.barbersys.controller;
import cn.nicecoder.barbersys.entity.comm.Resp;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  账户控制器
 * </p>
 *
 * @author lon't
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/account")
@Api(tags="账户关接口Controller")
public class AccountController {

    @GetMapping("/recharge/list")
    public Resp rechargeList(){
        return Resp.ok(null);
    }

}

