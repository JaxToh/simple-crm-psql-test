package sg.edu.ntu.simplecrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sg.edu.ntu.simplecrm.entity.Customer;
import sg.edu.ntu.simplecrm.entity.Interaction;
import sg.edu.ntu.simplecrm.exception.CustomerNotFoundException;
import sg.edu.ntu.simplecrm.repository.CustomerRepository;
import sg.edu.ntu.simplecrm.repository.InteractionRepository;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;
  private InteractionRepository interactionRepository;

  // Create
  @Override
  public Customer createCustomer(Customer customer) {
    // return customer;
    return customerRepository.save(customer);
  }

  // Get One
  @Override
  public Customer getCustomer(Long id) {

    return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }

  // Get All
  @Override
  public List<Customer> getAllCustomers() {
    List<Customer> allCustomers = customerRepository.findAll();
    return allCustomers;
  }

  // Update
  @Override
  public Customer updateCustomer(Long id, Customer customer) {

    // Retrieve customer from DB
    Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

    // Update the fields
    customerToUpdate.setFirstName(customer.getFirstName());
    customerToUpdate.setLastName(customer.getLastName());
    customerToUpdate.setEmail(customer.getEmail());
    customerToUpdate.setContactNo(customer.getContactNo());
    customerToUpdate.setJobTitle(customer.getJobTitle());
    customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
    return customerRepository.save(customerToUpdate);
  }

  // Delete
  @Override
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  @Override
  public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
    // Retrieve customer
    Customer selectedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

    // add the customer to the interaction
    interaction.setCustomer(selectedCustomer);
    return interactionRepository.save(interaction);

  }

  @Override
  public List<Customer> searchCustomers(String firstName) {
    List<Customer> foundCustomers = customerRepository.findByFirstName(firstName);
    return foundCustomers;

  }

}