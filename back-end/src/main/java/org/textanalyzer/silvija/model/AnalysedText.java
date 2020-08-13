package org.textanalyzer.silvija.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class AnalysedText implements Comparable<AnalysedText>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Character lastChar;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private InitialText initialText;
	
	@ElementCollection
	private List<String> wordsWithLastChar=new ArrayList<String>();

	public AnalysedText(char lastChar, InitialText initialText, List<String> wordsWithLastChar) {
		this.lastChar = lastChar;
		this.initialText = initialText;
		this.wordsWithLastChar = wordsWithLastChar;
	}



	@Override
	public int compareTo(AnalysedText o) {
		return lastChar.compareTo(o.getLastChar());
	}
	
	

}
