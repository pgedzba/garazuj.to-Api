package to.garazuj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import to.garazuj.message.request.AddActionForm;
import to.garazuj.services.HistoryService;


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
}
