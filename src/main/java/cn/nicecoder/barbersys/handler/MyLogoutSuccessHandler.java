package cn.nicecoder.barbersys.handler;

import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.util.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 登出
 * @author: longt
 * @date: 2021/4/21 上午10:06
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisClient redisClient;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        redisClient.delete(CommonEnum.REDIS_KEY_MENU_PERMISSION.getDesc());
        response.sendRedirect("/login?logout");
    }
}
