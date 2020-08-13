package org.textanalyzer.silvija.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class InitialText {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String content;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonIgnoreProperties//("request")
	private Set<AnalysedText> analysedDataSet=new TreeSet<AnalysedText>();


	public InitialText(String content) {
		super();
		this.content = content;
	}
	
	
	public void addCharWithWordSet (AnalysedText analysedText) {
		analysedDataSet.add(analysedText);
	}

}
