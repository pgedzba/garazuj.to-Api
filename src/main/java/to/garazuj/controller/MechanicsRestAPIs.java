package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import to.garazuj.message.request.AddMechanicForm;
import to.garazuj.model.Mechanic;
import to.garazuj.services.MechanicsService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/mechanics")
public class MechanicsRestAPIs {

    @Autowired
    MechanicsService mechanicsService;

    @GetMapping
    public ResponseEntity<List<Mechanic>> getMechanics() {
        return new ResponseEntity<>(mechanicsService.getMechanics(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addMechanic(@RequestBody AddMechanicForm addMechanicForm) {
        mechanicsService.addMechanic(addMechanicForm);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMechanic(@RequestParam Long id) {
        mechanicsService.deleteMechanic(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
