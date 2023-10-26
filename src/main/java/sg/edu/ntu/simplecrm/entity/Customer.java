package sg.edu.ntu.simplecrm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "customer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "first_name")
  @NotBlank(message = "First name is mandatory")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  @Email(message = "Email should be valid")
  private String email;
  @Column(name = "contact_no")
  @Size(min = 8, max = 8, message = "Contact number should be 8 digits")
  private String contactNo;
  @Column(name = "job_title")
  private String jobTitle;
  @Column(name = "year_of_birth")
  @Max(value = 2005, message = "Year of birth should not be later than 2005")
  @Min(value = 1940, message = "Year of birth should not be earlier than 1940")
  private int yearOfBirth;

  // Bi-directional relationship
  // One customer can have many interactions
  @OneToMany(mappedBy = "customer")
  // @JsonManagedReference
  private List<Interaction> interactions;

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // Lombok Builder: https://devwithus.com/lombok-builder-annotation/
  @Builder
  public Customer(String firstName, String lastName, String email, String contactNo, String jobTitle,
      int yearOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNo = contactNo;
    this.jobTitle = jobTitle;
    this.yearOfBirth = yearOfBirth;
  }

}
