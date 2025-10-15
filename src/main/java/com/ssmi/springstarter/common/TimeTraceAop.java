package com.ssmi.springstarter.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.ssmi.springstarter.common
 * fileName       : TimeTraceAop
 * author         : ssmihs
 * date           : 2025-10-15
 * description    :
 */
@Component
@Aspect
public class TimeTraceAop {
    @Around("execution(* com.ssmi.springstarter..*(..))")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + jointPoint.toString());

        try {
            return jointPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long duration = finish - start;

            System.out.println("END: " + jointPoint.toString() + " " + duration + "ms");
        }
    }
}
