package org.textanalyzer.silvija;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.textanalyzer.silvija.model.AnalysedText;
import org.textanalyzer.silvija.model.InitialText;
import org.textanalyzer.silvija.model.InitialTextDTO;
import org.textanalyzer.silvija.repository.AnalisedTextRepository;
import org.textanalyzer.silvija.repository.InitialTextRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest (properties = {"spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop"}) 
@AutoConfigureMockMvc
public class IntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	InitialTextRepository initialTextRepo;

	@Autowired
	AnalisedTextRepository analisedTextRepo;

	@PersistenceContext
	private EntityManager entityManager; 
	private Long id;

	@BeforeEach
	public void setUp() throws JsonProcessingException, Exception {

		InitialTextDTO req = new InitialTextDTO("Ejo kiskis per balas ir uzlipo dun2kas vabalaS");
		mockMvc.perform(
				post("/api/texts").contentType("application/json").content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isOk());
		List<InitialText> listFromDB = (List<InitialText>) initialTextRepo.findAll();
		id=listFromDB.get(0).getId();
	}

	@AfterEach
	public void cleanupAfterEach() {
		entityManager.flush();
	}

	@Test
	public void initialTextQuantitySavesAsExpected() {

		List<InitialText> listFromDB = (List<InitialText>) initialTextRepo.findAll();
		assertThat(listFromDB.size()).isEqualTo(1);

	}

	@Test
	public void analisedTextCharQuantitySavesAsExpected() {
		List<AnalysedText> charListFromDB = (List<AnalysedText>) analisedTextRepo.findAll();
		assertThat(charListFromDB.size()).isEqualTo(3);

	}


	@Test
	public void firstCharsListSavesCorrectWords() {
		List<AnalysedText> charListFromDB = (List<AnalysedText>)analisedTextRepo.findAll();
		List<String>words=charListFromDB.stream().filter(ch->ch.getLastChar().equals(Character.valueOf('o'))).findAny().orElse(null)
				.getWordsWithLastChar();
		assertThat(words.size()).isEqualTo(2);
		assertThat(words.contains("Ejo")).isTrue();
		assertThat(words.contains("uzlipo")).isTrue();
		
	}
	
	@Test
	public void secondCharsListSavesCorrectWords() {
		List<AnalysedText> charListFromDB = (List<AnalysedText>) analisedTextRepo.findAll();
		List<String>words=charListFromDB.stream().filter(ch->ch.getLastChar().equals(Character.valueOf('r'))).findAny().orElse(null)
				.getWordsWithLastChar();
		assertThat(words.size()).isEqualTo(2);
		assertThat(words.contains("per")).isTrue();
		assertThat(words.contains("ir")).isTrue();
		
	}
	
	@Test
	public void thirdCharsListSavesCorrectWords() {
		List<AnalysedText> charListFromDB = (List<AnalysedText>) analisedTextRepo.findAll();
		List<String>words=charListFromDB.stream().filter(ch->ch.getLastChar().equals(Character.valueOf('s'))).findAny().orElse(null)
				.getWordsWithLastChar();
		assertThat(words.size()).isEqualTo(3);
		assertThat(words.contains("kiskis")).isTrue();
		assertThat(words.contains("balas")).isTrue();
		assertThat(words.contains("vabalaS")).isTrue();
		
	}
	
	@Test
	public void returnsCorrectAnalysisById() throws Exception {
		mockMvc.perform(
				get("/api/texts/"+id).accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.initialString").value("Ejo kiskis per balas ir uzlipo dun2kas vabalaS"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.charWordSet[0].wordList[0]").value("Ejo"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.charWordSet[2].wordList[2]").value("vabalaS"))
	      ;
	}

}
