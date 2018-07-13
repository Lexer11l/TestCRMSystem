package kirill.controller;

import kirill.entity.hibernate.Customer;
import kirill.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService service;

  @RequestMapping("/list")
  public String listCustomers(Model theModel) {
    List<Customer> customers = service.getCustomers();
    theModel.addAttribute("customers", customers);
    return "list-customers";
  }

  @GetMapping("/showFormForAdd")
  public String addCustomer(Model theModel) {
    Customer customer = new Customer();
    theModel.addAttribute("customer", customer);
    return "add-customer";
  }

  @PostMapping("/saveCustomer")
  public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    service.saveCustomer(customer);
    return "redirect:/customer/list";
  }

  @GetMapping("/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("customerId") int theId,
                                  Model theModel) {
    Customer theCustomer = service.getCustomer(theId);
    theModel.addAttribute("customer", theCustomer);
    return "add-customer";
  }

  @GetMapping("/delete")
  public String deleteCustomer(@RequestParam("customerId") int id, Model model) {
    service.deleteCustomer(id);
    return "redirect:/customer/list";
  }

}
