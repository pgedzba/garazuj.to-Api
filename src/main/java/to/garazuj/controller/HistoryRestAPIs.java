package to.garazuj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import to.garazuj.message.request.AddActionForm;
import to.garazuj.message.response.ResponseMessage;
import to.garazuj.services.HistoryService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
public class HistoryRestAPIs {

	@Autowired
	HistoryService historyService;
	
	@PutMapping(value="/car/{id}")
	public ResponseEntity<?> addAction(@PathVariable Long id, @RequestBody AddActionForm addActionForm){

		return new ResponseEntity<>(historyService.addAction(id, addActionForm),HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value="{id}")
	public ResponseEntity<?> deleteAction(HttpServletRequest request, @PathVariable Long id){
		if(request.isUserInRole("ROLE_ADMIN"))
			historyService.deleteActionAdmin(id);
		else
			historyService.deleteActionUser(id);
		return new ResponseEntity<>(new ResponseMessage(""),HttpStatus.OK);
	}

	@GetMapping(value="/car/{id}")
	public ResponseEntity<?> addAction(@PathVariable Long id){

		return new ResponseEntity<>(historyService.getCarHistory(id),HttpStatus.OK);
	}
}
