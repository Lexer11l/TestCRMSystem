package smth.dao;

import org.springframework.stereotype.Component;
import smth.model.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDao {




  public void addAccount(){
    System.out.println("HELLOOOO FROM BOTTOM");
  }

  public List<Account> findAccounts() throws Exception {
    List<Account> result = new ArrayList<>();;
    result.add(new Account("John", "Silver"));
    result.add(new Account("Olge", "IKS"));
    result.add(new Account("DOKTOR", "MOM"));

    System.out.println("I AM FROM FIND METHOD");
    return result;
  }
}
