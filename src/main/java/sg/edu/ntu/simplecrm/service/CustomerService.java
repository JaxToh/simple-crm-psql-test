package sg.edu.ntu.simplecrm.service;

import java.util.List;

import sg.edu.ntu.simplecrm.entity.Customer;
import sg.edu.ntu.simplecrm.entity.Interaction;

public interface CustomerService {
  Customer createCustomer(Customer customer);

  Customer getCustomer(Long id);

  List<Customer> getAllCustomers();

  Customer updateCustomer(Long id, Customer customer);

  void deleteCustomer(Long id);

  Interaction addInteractionToCustomer(Long id, Interaction interaction);

  List<Customer> searchCustomers(String firstName);

}
