package cn.nicecoder.barbersys.handler;

import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.exception.ServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
  public class RequestHandler implements HandlerInterceptor {

     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getServletPath();
        if (path.indexOf("delete") > 0 || path.indexOf("batchdel") > 0) {
           throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(), "演示环境不允许删除哦");
        } else {
           return true;
        }
     }
     @Override
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) {
     }

     @Override
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
        Object handler, Exception exception) {
     }
  }