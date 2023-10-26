package sg.edu.ntu.simplecrm;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sg.edu.ntu.simplecrm.entity.Customer;
import sg.edu.ntu.simplecrm.repository.CustomerRepository;

@Component
public class DataLoader {

  private CustomerRepository customerRepository;

  public DataLoader(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @PostConstruct
  public void loadData() {
    // Clear the db
    customerRepository.deleteAll();

    // load data
    // customerRepository.save(new Customer("Bruce", "Banner"));
    // customerRepository.save(new Customer("Peter", "Parker"));
    // customerRepository.save(new Customer("Stephen", "Strange"));

    // Define customers using builder pattern
    Customer bruce = Customer.builder().firstName("Bruce").lastName("Banner").email("bruce@avengers.com")
        .contactNo("12345678").jobTitle("Scientist").yearOfBirth(1975).build();

    Customer peter = Customer.builder().firstName("Peter").lastName("Parker").email("peter@avengers.com")
        .contactNo("12345679").jobTitle("Photographer").yearOfBirth(1995).build();

    Customer stephen = Customer.builder().firstName("Stephen").lastName("Strange").email("stephen@avengers.com")
        .contactNo("12345670").jobTitle("Doctor").yearOfBirth(1985).build();

    // Save customers to db
    customerRepository.save(bruce);
    customerRepository.save(peter);
    customerRepository.save(stephen);

  }

}
