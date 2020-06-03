package com.yourBouquet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logger {
    @Pointcut("execution(* com.yourBouquet.сontroller.MainController.addClientOrder(..))")
    public void logging() { }

    @Before("logging()")
    public void logCreating(JoinPoint jp) {
        System.out.println("METHOD " + jp.getSignature() + " WAS CALLED!");
    }
}

