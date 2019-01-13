package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import to.garazuj.exception.CarException;
import to.garazuj.message.request.AddMechanicForm;
import to.garazuj.model.Mechanic;
import to.garazuj.model.Post;
import to.garazuj.repository.MechanicsRepository;
import to.garazuj.security.SecurityUtils;

import java.util.List;

@Service
public class MechanicsService {

	@Autowired
	MechanicsRepository mechanicsRepository;
	
	public ResponseEntity<?> addMechanic(AddMechanicForm addMechanicForm){
		Mechanic mechanic = new Mechanic();
		mechanic.setShortDescription(addMechanicForm.getShortDescription());
		mechanic.setTitle(addMechanicForm.getTitle());
		mechanicsRepository.save(mechanic);

		return new ResponseEntity<>(mechanic,HttpStatus.OK);
	}

	public List<Mechanic> getMechanics(){
		return mechanicsRepository.findAll();
	}

	public void deleteMechanic(Long id){
		mechanicsRepository.deleteById(id);
	}

	public List<Mechanic> searchMechanics(String search){
		return mechanicsRepository.findWithSearch(search);
	}
}
