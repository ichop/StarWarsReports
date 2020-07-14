package com.reports.service.servicesImplementations;


import com.reports.repository.ReportRepository;
import com.reports.model.Report;
import com.reports.service.ReportService;
import com.reports.service.ResultsGenerationService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ReportServiceImplementation implements ReportService {

    private final ResultsGenerationService resultsGenerationService;
    private final ReportRepository reportRepository;


    public ReportServiceImplementation(ResultsGenerationService resultsGenerationService, ReportRepository reportRepository) {
        this.resultsGenerationService = resultsGenerationService;
        this.reportRepository = reportRepository;
    }

    public void putReport(Integer reportId, String characterPhrase, String searchingPlanetName) throws NotFoundException {
        Report report = new Report(reportId, characterPhrase, searchingPlanetName);
        report.setResults(resultsGenerationService.generateResults(characterPhrase, searchingPlanetName));
//        if(getById(reportId) != null) {
//            deleteById(reportId);
//        }
        reportRepository.save(report);
    }

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public Report getById(Integer id) {
        return reportRepository.findById(id).orElse(null);
    }

    public void deleteAll() {
        reportRepository.deleteAll();
    }

    public void deleteById(Integer id) {
        reportRepository.delete(getById(id));
    }
}
