import com.fasterxml.jackson.databind.ObjectMapper;
import smth.model.Student;

import java.io.File;

public class DemoJackson {
  public static void main(String[] args) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      Student student = mapper.readValue(new File("data/sample-full.json"), Student.class);
      System.out.println(student);
    }
    catch (Exception ex){
      System.out.println(ex);
    }
  }
}
