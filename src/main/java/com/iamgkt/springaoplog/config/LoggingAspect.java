package com.iamgkt.springaoplog.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Around("execution(* com.iamgkt.springaoplog..*(..))")
  public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().toLongString();
    String className = joinPoint.getTarget().getClass().getName();
    Object[] args = joinPoint.getArgs();

    String traceId = java.util.UUID.randomUUID().toString();
    long startTime = System.currentTimeMillis();

    logger.info(
        "{{\"event\":\"METHOD_START\",\"traceId\":\"{}\",\"class\":\"{}\",\"method\":\"{}\",\"args\":{}}}",
        traceId,
        className,
        methodName,
        java.util.Arrays.toString(args));

    try {
      Object result = joinPoint.proceed();
      long duration = System.currentTimeMillis() - startTime;

      logger.info(
          "{{\"event\":\"METHOD_END\",\"traceId\":\"{}\",\"class\":\"{}\",\"method\":\"{}\",\"result\":{},\"durationMs\":{}}}",
          traceId,
          className,
          methodName,
          result,
          duration);

      return result;
    } catch (Exception e) {
      logger.error(
          "{{\"event\":\"METHOD_EXCEPTION\",\"traceId\":\"{}\",\"class\":\"{}\",\"method\":\"{}\",\"error\":\"{}\"}}",
          traceId,
          className,
          methodName,
              e,
          e);
      throw e;
    }
  }
}
