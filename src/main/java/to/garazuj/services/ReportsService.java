package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import to.garazuj.exception.CarException;
import to.garazuj.exception.HistoryException;
import to.garazuj.exception.UserException;
import to.garazuj.message.request.AddActionForm;
import to.garazuj.model.Car;
import to.garazuj.model.History;
import to.garazuj.model.Report;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.repository.HistoryRepository;
import to.garazuj.repository.ReportsRepository;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.SecurityUtils;

import java.util.List;

@Service
public class ReportsService {

	@Autowired
	ReportsRepository reportsRepository;

	
	public Report addReport(Long articleID){
		Report report = new Report(SecurityUtils.getCurrentUser(),articleID);
		reportsRepository.save(report);

		return report;
	}

	public List<Report> getAll(){
		return  reportsRepository.findAll();
	}

}
