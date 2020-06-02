package ru.naumow.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostRequestCounter {

    @Before("execution(* ru.naumow.services.*.*(..))")
    public void count() {
        System.out.println("Service method is executed");
    }

}
