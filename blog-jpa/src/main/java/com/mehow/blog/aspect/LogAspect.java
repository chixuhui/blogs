package com.mehow.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.RequestHandledEvent;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * log日志处理
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.mehow.blog.controller.*.*(..))")
    public void log() {
    }
    /**
     * 之前拦截
     */
    @Before("log()")
    public void doBefor(JoinPoint joinPoint) {
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();

        String classMehod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMehod,args);
        /**
         * 将日志输出到txt文件，并显示到控制台
         */
        logger.info("Request:{}"+requestLog);
        logger.info("====-------------------doBefor-------------");
    }

    /**
     * 前面之后执行
     */
    @After("log()")
    public void doAfter() {
        logger.info("====-------------------doBefor-------------");
    }

    /**
     * 最后执行，并返回方法的返回值
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterRuturn(Object result) {


        logger.info("Result:{}---->>" + result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMehod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMehod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMehod = classMehod;
            this.args = args;
        }
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getClassMehod() {
            return classMehod;
        }

        public void setClassMehod(String classMehod) {
            this.classMehod = classMehod;
        }

        public Object getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
        @Override
        public String toString() {
            return "RequestLog{" +
                    "url=" + url +
                    ", ip='" + ip + '\'' +
                    ", classMehod='" + classMehod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
