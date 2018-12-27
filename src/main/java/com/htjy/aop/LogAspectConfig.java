package com.htjy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspectConfig {
    private Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);


    //被注解CustomAopAnnotation表示的方法
    //@Pointcut("@annotation(com.only.mate.springboot.annotation.CustomAopAnnotation")
    @Pointcut("execution(public * com.htjy.controller.*.*(..))")
    public void pointCut(){

    }


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        logger.info("【注解：Before】------------------切面  before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("【注解：Before】浏览器输入的网址=URL : " + request.getRequestURL().toString());
        logger.info("【注解：Before】HTTP_METHOD : " + request.getMethod());
        logger.info("【注解：Before】IP : " + request.getRemoteAddr());
        logger.info("【注解：Before】执行的业务方法名=CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("【注解：Before】业务方法获得的参数=ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }


    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void afterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("【注解：AfterReturning】这个会在切面最后的最后打印，方法的返回值 : " + ret);
    }


    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint jp){
        logger.info("【注解：AfterThrowing】方法异常时执行.....");
    }


    @After("pointCut()")
    public void after(JoinPoint jp){
        logger.info("【注解：After】方法最后执行.....");
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        logger.info("【注解：Around . 环绕前】方法环绕start.....");
        try {
            //如果不执行这句，会不执行切面的Before方法及controller的业务方法
            Object o =  pjp.proceed();
            logger.info("【注解：Around. 环绕后】方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
