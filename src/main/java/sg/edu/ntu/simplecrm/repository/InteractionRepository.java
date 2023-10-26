package sg.edu.ntu.simplecrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simplecrm.entity.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {

}
