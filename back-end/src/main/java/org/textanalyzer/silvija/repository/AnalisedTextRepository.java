package org.textanalyzer.silvija.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.textanalyzer.silvija.model.InitialText;
import org.textanalyzer.silvija.model.AnalysedText;

public interface AnalisedTextRepository extends CrudRepository<AnalysedText, Long> {
	List<AnalysedText> findAllByInitialText(InitialText initialText);

}
