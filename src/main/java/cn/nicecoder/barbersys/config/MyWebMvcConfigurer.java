package cn.nicecoder.barbersys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件上传读取拦截器（内置tomcat时使用）
 * @author: xxxxx
 * @date: 2021/5/26 下午2:43
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${file.common.filePathWindow}")
    private String filePathWindow;
    @Value("${file.common.uploadLinux}")
    private String filePathLinux;

    private final static String resourceLocation = "/images/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler：指的是对外暴露的访问路径
        //addResourceLocations：指的是内部文件放置的目录
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler(resourceLocation)
                    .addResourceLocations("file:" + filePathWindow);
        } else {
            registry.addResourceHandler(resourceLocation)
                    .addResourceLocations("file:" + filePathLinux) ;
        }
    }
}
