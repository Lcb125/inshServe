package com.hsbc.insh;

//import com.hsbc.insh.common.util.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {




    //配置视图映射，把各个处理器中用来转页面的接口统一放在此处做映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/gh").setViewName("hello");
        registry.addViewController("/ul").setViewName("user/userlist");
//      registry.addViewController("/user/gh").setViewName("hello");
    }

    //格式转换器，把前端传递的参数，转成我们指定的格式，日期转换,对全局生效
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new MyDateConverter());
//    }

    //拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/file/**");
//    }

    //配置跨域支持
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
//                .allowedOrigins("*")
//                .allowCredentials(true)
                .allowedOriginPatterns("*");
    }

}
