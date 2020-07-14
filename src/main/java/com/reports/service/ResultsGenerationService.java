package com.reports.service;

import com.reports.model.Result;
import javassist.NotFoundException;

import java.util.Set;

public interface ResultsGenerationService {
    Set<Result> generateResults(String characterPhrase, String searchingPlanetPhrase) throws NotFoundException;
}
