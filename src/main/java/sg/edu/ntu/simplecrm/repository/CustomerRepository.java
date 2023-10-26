package sg.edu.ntu.simplecrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simplecrm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByFirstName(String firstName);
}
