package kirill.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
 private final static Logger LOGGER = Logger.getLogger(CRMLoggingAspect.class);

 @Pointcut("execution(* kirill.controller.*.*(..))")
 private void forControllerPackage(){}

  @Pointcut("execution(* kirill.service.*.*(..))")
  private void forServicePackage(){}

  @Pointcut("execution(* kirill.dao.*.*(..))")
  private void forDaoPackage(){}

  @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
  private void forAppFlow(){}

  @Before("forAppFlow()")
  private void before(JoinPoint joinPoint){
   String methodName = joinPoint.getSignature().toShortString();
   LOGGER.info("==> in @Before, calling method: " + methodName);

   for (Object object: joinPoint.getArgs()){
     System.out.println(object);
   }
  }


  @AfterReturning(value = "forAppFlow()", returning = "result")
  private void after(JoinPoint joinPoint, Object result){
    String methodName = joinPoint.getSignature().toShortString();
    LOGGER.info("==> in @After, calling method: " + methodName);


    LOGGER.info("Result: " + result);

  }

}
