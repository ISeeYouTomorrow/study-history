package com.study.lxl.annotation;

import com.study.lxl.cache.CacheClear;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xile.lv
 * @since 2020/6/28 13:29
 **/
@Aspect
@Component
public class CacheAspect {

    @After("@annotation(com.study.lxl.cache.CacheClear)")
    public void clearCache(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        CacheClear cacheClear = method.getAnnotation(CacheClear.class);
//        Object[] args = joinPoint.getArgs();
//        Object target = joinPoint.getTarget();
//        try {
//            Object rs = method.invoke(target, args);
//            System.out.println("object ====> " + rs.toString());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        String name = cacheClear.name();
        System.out.printf("清除缓存key[{%s}]",name);
    }


    @Before("@annotation(com.study.lxl.cache.CacheClear)")
    public void checkCache(JoinPoint joinPoint) {
        System.out.println("判断缓存是否存在");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("method = "+method.getName()+" "+method.getReturnType());
        System.out.println("在注解方法执行之前");
    }

    /**
     * 目标方法执行前后都执行
     */
//    @Around("@annotation(com.study.lxl.cache.CacheClear)")
//    public void ab(JoinPoint point) {
//        System.out.println(Thread.currentThread().getName());
//    }

}
