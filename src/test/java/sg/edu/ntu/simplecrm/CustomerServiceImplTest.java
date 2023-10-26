package sg.edu.ntu.simplecrm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.ntu.simplecrm.entity.Customer;
import sg.edu.ntu.simplecrm.exception.CustomerNotFoundException;
import sg.edu.ntu.simplecrm.repository.CustomerRepository;
import sg.edu.ntu.simplecrm.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceImplTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  @Test
  public void createCustomerTest() {

    // 1. SETUP / ARRANGE
    // Customer customer = new Customer();
    Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345679").jobTitle("Special Agent").yearOfBirth(1975).build();

    // Mock the save method of customer repository
    when(customerRepository.save(customer)).thenReturn(customer);

    // 2. EXECUTE
    // Call the method that we want to test
    Customer savedCustomer = customerService.createCustomer(customer);

    // 3. ASSERT
    assertEquals(customer, savedCustomer, "The saved customer should be the same as the input customer");

    // Also verify that the save method was called
    verify(customerRepository, times(1)).save(customer);
  }

  @Test
  public void getCustomerTest() {

    // 1. SETUP
    Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345679").jobTitle("Special Agent").yearOfBirth(1975).build();

    Long customerId = 1L;

    when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

    // 2. EXECUTE
    Customer retrievedCustomer = customerService.getCustomer(customerId);

    // 3. ASSERT
    assertEquals(customer, retrievedCustomer, "The retrieved customer should be the same as the input customer");

  }

  @Test
  void getCustomerNotFoundTest() {
    Long customerId = 1L;
    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    // Assert
    assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));

  }

}
