package org.textanalyzer.silvija.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.textanalyzer.silvija.model.AnalysedTextDTO;
import org.textanalyzer.silvija.model.InitialTextDTO;
import org.textanalyzer.silvija.service.AnalyserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/texts")
public class AnalyserController {

	@Autowired
	AnalyserService analyserService;

	@PostMapping
	public Long readTheRequest(@RequestBody InitialTextDTO initialText) {
		return analyserService.analyseInitialText(initialText);
	}

	@GetMapping("/{initialTextId}")
	public AnalysedTextDTO getResponse(@PathVariable Long initialTextId) {
		return analyserService.getAnalysisResultByInitialTextId(initialTextId);
	}

	@GetMapping
	public List<AnalysedTextDTO> getResponse() {
		return analyserService.analysisHistory();
	}

}
