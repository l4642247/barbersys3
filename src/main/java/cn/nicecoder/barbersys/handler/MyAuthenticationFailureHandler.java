package cn.nicecoder.barbersys.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: 登录异常
 * @author: xxxxx
 * @date: 2021/4/23 下午5:05
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        session.setAttribute("errorMsg", exception.getMessage());
        if("Bad credentials".equals(exception.getMessage())){
            session.setAttribute("errorMsg", "账号或密码错误");
        }
        response.sendRedirect("/login?error=true");
    }
}