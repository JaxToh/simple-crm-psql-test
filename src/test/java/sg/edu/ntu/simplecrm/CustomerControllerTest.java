package sg.edu.ntu.simplecrm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.ntu.simplecrm.entity.Customer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @DisplayName("Get customer by Id")
  @Test
  public void getCustomerByIdTest() throws Exception {
    // Step 1: Build a request
    RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");

    // Step 2: Perform the request, get the response and assert
    mockMvc.perform(request)
        // Assert that the status code is 200 OK
        .andExpect(status().isOk())
        // Assert the content is JSON
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Assert that the id returned is 1
        .andExpect(jsonPath("$.id").value(1));

  }

  @Test
  public void getAllCustomersTest() throws Exception {
    // Step 1: Build a GET request to /customers
    RequestBuilder request = MockMvcRequestBuilders.get("/customers");

    // Step 2: Perform the request, get the response and assert
    mockMvc.perform(request)
        // Assert that the status code is 200 OK
        .andExpect(status().isOk())
        // Assert that the content is JSON
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Assert that the size of the response is 3
        .andExpect(jsonPath("$.size()").value(3));
  }

  @Test
  public void validCustomerCreationTest() throws Exception {
    // Step 1: Create a Customer object
    Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

    // Step 2: Convert the Java objec to JSON using ObjectMapper
    String newCustomerAsJSON = objectMapper.writeValueAsString(customer);

    // Step 3: Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newCustomerAsJSON);

    // Step 4: Perform the request, get response and assert
    mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(4))
        .andExpect(jsonPath("$.firstName").value("Clint"))
        .andExpect(jsonPath("$.lastName").value("Barton"));
  }

  @Test
  public void invalidCustomerCreationTest() throws Exception {
    // Step 1: Create a Customer with invalid fields
    Customer invalidCustomer = Customer.builder().firstName("Clint").lastName("Barton").email("clint")
        .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

    // Step 2: Convert the Java object to JSON
    String invalidCustomerAsJSON = objectMapper.writeValueAsString(invalidCustomer);

    // Step 3: Build the request
    RequestBuilder request = MockMvcRequestBuilders.post("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidCustomerAsJSON);

    // Step 4: Perform the request and get the response and assert
    mockMvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

}
