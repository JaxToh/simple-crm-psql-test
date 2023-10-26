package sg.edu.ntu.simplecrm.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(Long id) {
    super("Could not find customer " + id);
  }

}
