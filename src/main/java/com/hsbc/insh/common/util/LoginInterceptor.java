//package com.hsbc.insh.common.util;
//
//
//import com.sun.istack.internal.NotNull;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
////    @Autowired
////    private HttpSession httpSession;
//
//
//    //Controller逻辑执行之前
//    @Override
//    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
//        log.info("preHandle....");
//        String uri = request.getRequestURI();
//        log.info("当前路径："+uri);
//        /**
//         * HandlerMethod=>Controller中标注@RequestMapping的方法
//         *  需要配置静态资源不拦截时，添加这块逻辑  => 前后端分离项目
//         *
//         */
//        // 是我们的conrtoller中的方法
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
////        String token = request.getHeader("authToken");
////        System.out.println("authToken==="+token);
////        if (token == null){
////            log.info("未登录1");
////            return false;
////        }
////        TokenUtils tokenUtils = new TokenUtils();
////        String verify = tokenUtils.verify(token);
//        String verify = "true";
//        if (verify == null) {
//            // 未登录跳转到登录界面
//            log.info("未登录");
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    //Controller逻辑执行完毕但是视图解析器还未进行解析之前
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle....");
//    }
//
//    //Controller逻辑和视图解析器执行完毕
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("afterCompletion....");
//    }
//}
