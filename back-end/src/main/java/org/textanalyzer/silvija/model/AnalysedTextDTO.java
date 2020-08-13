package org.textanalyzer.silvija.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AnalysedTextDTO {
	
	private String initialString;
//	private Map<Character, List<String>> charWordMap=new TreeMap<Character, List<String>>();
	private Set<CharWordList> charWordSet =new TreeSet<CharWordList>();
	
	public AnalysedTextDTO(String initialString, Map<Character, List<String>> charWordMap) {

		this.initialString = initialString;
		
		for (Character ch : charWordMap.keySet()) {
			List<String> words=charWordMap.get(ch);
			charWordSet.add(new CharWordList(ch, words));
			
		}
		
		
	}
	

	

}
