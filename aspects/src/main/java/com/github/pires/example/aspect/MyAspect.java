package com.github.pires.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple aspect.
 */
@Aspect
public class MyAspect {

  private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

  /*@Before("@annotation(Trace)")
  public void logBefore(JoinPoint joinPoint) {
    final String methodFullName = String.format("%s.%s", joinPoint
        .getSignature().getDeclaringType().getName(), joinPoint.getSignature()
        .getName());
    log.info("logBefore() is RUNNING for ", methodFullName);
  }

  @After("@annotation(Trace)")
  public void logAfter(JoinPoint joinPoint) {
    final String methodFullName = String.format("%s.%s", joinPoint
        .getSignature().getDeclaringType().getName(), joinPoint.getSignature()
        .getName());
    log.info("logAfter() is RUNNING for {}", methodFullName);
  }*/

  @Around("execution(* *.*(..)) && @annotation(Trace)")
  public Object logArround(ProceedingJoinPoint joinPoint) throws Throwable {

    final String methodFullName = String.format("%s.%s", joinPoint
            .getSignature().getDeclaringType().getName(), joinPoint.getSignature()
            .getName());

    final long startTime = System.currentTimeMillis();
    try {
      log.info("logArround() is STARTING for {}", methodFullName);
      return joinPoint.proceed();
    } finally {
      final long stopTime = System.currentTimeMillis();
      log.info("logArround() is ENDING for {}", methodFullName);
    }
  }

  /*@Before("execution(* *.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("logBefore() is RUNNING for ", joinPoint.getSignature().getName());
  }

  @After("execution(* *.*(..))")
  public void logAfter(JoinPoint joinPoint) {
    log.info("logAfter() is RUNNING for {}", joinPoint.getSignature().getName());
  }*/

}
