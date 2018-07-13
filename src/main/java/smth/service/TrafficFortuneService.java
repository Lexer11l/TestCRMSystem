package smth.service;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

  public String getFortune() {

    try {
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "HEAVY TRAFFIC";
  }
}
