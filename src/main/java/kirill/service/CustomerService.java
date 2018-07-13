package kirill.service;

import kirill.entity.hibernate.Customer;

import java.util.List;

public interface CustomerService {
  Customer getCustomer(int id);
  List<Customer> getCustomers();
  void saveCustomer(Customer customer);
  void deleteCustomer(int id);
}
