package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import to.garazuj.message.request.AddReportForm;
import to.garazuj.model.Report;
import to.garazuj.services.ReportsService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports")
public class ReportsRestAPIs {

    @Autowired
    ReportsService reportsService;

    @PostMapping
    public ResponseEntity<?> reportArticle(@RequestBody AddReportForm addReportForm){
        Report report = reportsService.addReport(addReportForm.getArticleId());

        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity(reportsService.getAll(),HttpStatus.OK);
    }
}
