package kirill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kirill.entity.errors.CustomerException;
import kirill.entity.errors.NoCustomerDataException;
import kirill.entity.hibernate.Customer;
import kirill.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smth.model.Student;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

  @Autowired
  private CustomerService service;


  @GetMapping("/customers")
  public List<Customer> getCustomers() {
    List<Customer> customers= service.getCustomers();
    if (isNull(customers) || customers.isEmpty()){
      throw new NoCustomerDataException();
    }
    else return customers;
  }

  @GetMapping("/customers/{custId}")
  public Customer getCustomer(@PathVariable("custId") int id)  {
    Customer customer = service.getCustomer(id);
    if (isNull(customer)){
      throw new NoCustomerDataException();
    }
    else return customer;
  }

  @PostMapping("/customers")
  public Customer addCustomer(@RequestBody Customer customer) {
    customer.setId(0);
    service.saveCustomer(customer);
    return customer;
  }

  @PutMapping("/customers")
  public Customer updateCustomer(@RequestBody Customer customer) {
    service.saveCustomer(customer);
    return customer;
  }

  @DeleteMapping("/customers/{custId}")
  public String deleteCustomer(@PathVariable("custId") int id)  {
    Customer tempCustomer = service.getCustomer(id);

    // throw exception if null

    if (tempCustomer == null) {
      throw new NoCustomerDataException("Customer id not found - " + id);
    }

    service.deleteCustomer(id);

    return "Deleted customer id - " + id;
  }

}
