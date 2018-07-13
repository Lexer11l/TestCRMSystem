package kirill.dao;

import kirill.entity.hibernate.Customer;

import java.util.List;

public interface CustomerDAO {

  Customer getCustomer(int id);
  List<Customer> getCustomers();
  void saveCustomer(Customer customer);
  void deleteCustomer(int id);
}
