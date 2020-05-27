package ru.naumow.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecommendationFormingTimeLogger {


    @Around("execution(* ru.naumow.services.RecommendationService+.recommendationsFor(..))")
    private Object log(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Recommendation forming time is " + (end - start));
        return proceed;
    }

}
