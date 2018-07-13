package smth.aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TrafficAspect {


  @Around("execution(String getFortune())")
  public Object afterFindAccount(ProceedingJoinPoint joinPoint){
    long begin = System.currentTimeMillis();
    Object result = null;
    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    System.out.println(System.currentTimeMillis()- begin);
    return result;
  }


}
