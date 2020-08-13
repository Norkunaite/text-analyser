package org.textanalyzer.silvija.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CharWordList implements Comparable <CharWordList>{
	
	private Character lastChar;
	private int wordCount;
	private List<String> wordList=new ArrayList<String>();
	
	public CharWordList(Character lastChar, List<String> wordList) {

		this.lastChar = lastChar;
		this.wordList = wordList;
		this.wordCount=wordList.size();
	}

	@Override
	public int compareTo(CharWordList o) {
		return lastChar.compareTo(o.getLastChar());
	}
		

}
