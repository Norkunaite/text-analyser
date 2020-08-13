package org.textanalyzer.silvija.service;

import java.util.List;

import org.textanalyzer.silvija.model.AnalysedTextDTO;
import org.textanalyzer.silvija.model.InitialTextDTO;

public interface AnalyserService {
	
	Long analyseInitialText(InitialTextDTO initialText);
	
	AnalysedTextDTO getAnalysisResultByInitialTextId(Long initialTextId);
	
	List<AnalysedTextDTO> analysisHistory();


}
