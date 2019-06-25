package com.hao.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.hao.security.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println("aspect start ..");
        long start = System.currentTimeMillis();
        Object[] args = point.getArgs();
        for (Object arg:args){
            System.out.println("arg is :"+arg);
        }

        Object object = point.proceed();
        System.out.println("aspect 耗时：" + (System.currentTimeMillis() - start));
        System.out.println("aspect finish ..");
        return object;
    }
}
