package com.mehow.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * error处理
 * xuhui 二〇二〇年七月二十九日 19:39:58
 */
@ControllerAdvice
public class ControllerExecptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Requst URL : {}，Exception : {}", request.getRequestURL(),e);
        /**
         * 如果是404 500 则不跳往错误页面，而是到404页面 500页面
         */
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
    /*@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }*/
}
