package org.textanalyzer.silvija.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.textanalyzer.silvija.model.AnalysedText;
import org.textanalyzer.silvija.model.AnalysedTextDTO;
import org.textanalyzer.silvija.model.InitialText;
import org.textanalyzer.silvija.model.InitialTextDTO;
import org.textanalyzer.silvija.repository.AnalisedTextRepository;
import org.textanalyzer.silvija.repository.InitialTextRepository;

@Service
public class AnalyserServiceImpl implements AnalyserService {

	@Autowired
	InitialTextRepository initialTextRepo;

	@Autowired
	AnalisedTextRepository analysedTextRepo;

	@Override
	public Long analyseInitialText(InitialTextDTO initialText) {
		Map<Character, List<String>> analysedCharMap = convertToMap(initialText);
		InitialText newInitialText = new InitialText(initialText.getContent()); 
		
		for (Character ch : analysedCharMap.keySet()) {
			AnalysedText newResponse = new AnalysedText(ch, newInitialText, analysedCharMap.get(ch));
			newInitialText.addCharWithWordSet(newResponse);
			analysedTextRepo.save(newResponse);
		}
		return initialTextRepo.save(newInitialText).getId();

	}

	@Override
	public AnalysedTextDTO getAnalysisResultByInitialTextId(Long initialTextId) {
		InitialText initialTextFromDB = initialTextRepo.findById(initialTextId).get();
		return getAnalysisResultByInitialText(initialTextFromDB);
	}

	@Override
	public List<AnalysedTextDTO> analysisHistory() {
		List<InitialText> allInitialTexts = (List<InitialText>) initialTextRepo.findAll();
		List<AnalysedTextDTO> analysisHistory = new ArrayList<AnalysedTextDTO>();
		for (InitialText initialText : allInitialTexts) {
			analysisHistory.add(getAnalysisResultByInitialText(initialText));
		}
		return analysisHistory;
	}

	private AnalysedTextDTO getAnalysisResultByInitialText(InitialText initialText) {
		List<AnalysedText> allAnalysedTextOfInitText = analysedTextRepo.findAllByInitialText(initialText);
		Map<Character, List<String>> charWordMap = new TreeMap<Character, List<String>>();
		for (AnalysedText res : allAnalysedTextOfInitText) {
			charWordMap.put(res.getLastChar(), res.getWordsWithLastChar());
		}

		return new AnalysedTextDTO(initialText.getContent(), charWordMap);
	}
	
	private Map<Character, List<String>> convertToMap(InitialTextDTO initialText) {

		String[] requestWordArr = initialText.getContent().split(" ");
		return Arrays.stream(requestWordArr).parallel().filter(str->str.matches("\\p{L}+"))
				.collect(Collectors.groupingBy(str -> Character
						.toLowerCase(str.charAt(str.length() - 1))));


	}

}
