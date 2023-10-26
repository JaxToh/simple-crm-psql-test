package sg.edu.ntu.simplecrm.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interaction")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Interaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "remarks")
  @Size(min = 3, max = 30, message = "Remarks should be between 3 and 30 characters")
  private String remarks;
  @Column(name = "interaction_date")
  @PastOrPresent(message = "Interaction date should not be in the future")
  private LocalDate interactionDate;

  // @JsonBackReference
  @ManyToOne(optional = false)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

}
