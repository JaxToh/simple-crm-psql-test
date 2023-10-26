package sg.edu.ntu.simplecrm.service;

import java.util.List;

import sg.edu.ntu.simplecrm.entity.Interaction;

public interface InteractionService {

  Interaction saveInteraction(Interaction interaction);

  Interaction getInteraction(Long id);

  List<Interaction> getAllInteractions();

  Interaction updateInteraction(Long id, Interaction interaction);

  void deleteInteraction(Long id);

}
