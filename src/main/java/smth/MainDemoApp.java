package smth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import smth.service.TrafficFortuneService;

public class MainDemoApp {

  public static void main(String[] args)  {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
    TrafficFortuneService accountDAO = context.getBean("trafficFortuneService", TrafficFortuneService.class);
    System.out.println(accountDAO.getFortune());

      context.close();
    }


}
