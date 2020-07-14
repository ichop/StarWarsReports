package com.reports.service;

import com.reports.model.Report;
import javassist.NotFoundException;

import java.util.List;

public interface ReportService {
    void putReport(Integer reportId, String characterPhrase, String searchingPlanetName) throws NotFoundException;

    List<Report> getAll();

    Report getById(Integer id);

    public void deleteAll();

    public void deleteById(Integer id);
}
