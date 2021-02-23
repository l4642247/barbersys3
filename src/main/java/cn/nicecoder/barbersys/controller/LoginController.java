package cn.nicecoder.barbersys.controller;

import cn.nicecoder.barbersys.entity.comm.Resp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
    * 登录失败返回 401 以及提示信息.
    *
    * @return the rest
    */
    @PostMapping("/failure")
    public Resp loginFailure() {
        return Resp.fail( "登录失败了");
    }

    /**
    * 登录成功后拿到个人信息.
    *
    * @return the rest
    */
    @PostMapping("/success")
    public Resp loginSuccess() {
         return Resp.ok("登录成功");
    }
}