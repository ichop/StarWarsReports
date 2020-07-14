package com.reports.restcontroller;

import com.reports.model.Report;
import com.reports.model.RequestBodyParams;
import com.reports.service.ReportService;
import com.reports.service.servicesImplementations.ReportServiceImplementation;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportServiceImplementation reportServiceImplementation) {
        this.reportService = reportServiceImplementation;
    }

    @PutMapping(path = "/reports/{report_id}")
    public ResponseEntity<Void> putReport(@PathVariable Integer report_id,
                                          @RequestBody RequestBodyParams requestBodyParams) {
        try {
            reportService.putReport(report_id,
                    requestBodyParams.getQueryCriteriaCharacterPhrase(),
                    requestBodyParams.getQueryCriteriaPlanetName());
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/reports")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAll();
        if (reports == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping(path = "/reports/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
        Report report = reportService.getById(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @DeleteMapping(path = "/reports")
    public ResponseEntity<Void> deleteAllReports(){
        reportService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/reports/{id}")
    public ResponseEntity<Report> deleteByReportById(@PathVariable Integer id) {
        reportService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
