package ru.naumow.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advices {

    //@Before("within(ru.naumow.repository.MyClassRepository+) && execution(* *(..))")
    public void ss(JoinPoint joinPoint) {
        System.out.println("---");
        System.out.println(joinPoint.getTarget().getClass().getName() + " invokes " + joinPoint.getSignature().getName());
        System.out.println(joinPoint.getThis().getClass().getName() + " invokes " + joinPoint.getSignature().getName());
        System.out.println("---");
    }

}
