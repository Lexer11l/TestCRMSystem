package smth.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import smth.model.Account;

import java.util.List;

@Aspect
@Component
public class DemoAspect {

  @Before("execution(public void addAccount()))")
  public void beforeAddAccount(JoinPoint joinPoint){
    System.out.println(joinPoint.getSignature());
    System.out.println(joinPoint.getKind());
    System.out.println(joinPoint.getSourceLocation());
    System.out.println(joinPoint.getStaticPart());
    System.out.println(joinPoint.getTarget());
    System.out.println(joinPoint.getThis());
  }

  @AfterReturning(value = "execution(* findAccounts())", returning = "result")
  public void afterFindAccount(JoinPoint joinPoint, List<Account> result){
    System.out.println(result);
    result.add(new Account("SAM", "FISHER"));
    result.get(0).setName("NEW NAME");
  }


  @AfterThrowing(value = "execution(* findAccounts())", throwing = "result")
  public void afterFindAccount(JoinPoint joinPoint, Throwable result){
    System.out.println(result.toString());
  }

}


