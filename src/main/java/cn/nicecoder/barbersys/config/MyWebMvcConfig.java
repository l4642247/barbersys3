package cn.nicecoder.barbersys.config;

import cn.nicecoder.barbersys.handler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求拦截器
 * @author: xxxxx
 * @date: 2021/5/26 下午2:43
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Value("${file.common.filePathWindow}")
    private String filePathWindow;
    @Value("${file.common.uploadLinux}")
    private String filePathLinux;

    @Autowired
    private RequestHandler requestHandler;

    private final static String resourceLocation = "/images/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler：指的是对外暴露的访问路径
        //addResourceLocations：指的是内部文件放置的目录
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler(resourceLocation).addResourceLocations("file:" + filePathWindow);
        } else {
            registry.addResourceHandler(resourceLocation).addResourceLocations("file:" + filePathLinux) ;
        }

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有delete目录(测试环境)
        registry.addInterceptor(requestHandler).addPathPatterns("/**");
    }
}
