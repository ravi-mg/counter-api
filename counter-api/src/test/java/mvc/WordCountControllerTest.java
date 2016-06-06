package mvc;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import core.service.WordCountService;
import rest.mvc.WordCountController;



public class WordCountControllerTest {
	
	@Mock
	WordCountService service;
	
	@InjectMocks
	private WordCountController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	}
	
	@Test
	public void wordCountTest() throws Exception{
		String[] input = {"Duis","Sed","Donec","Augue","Pellentesque","123"};
		Map<String, Integer> hm = new HashMap<>();
		
		when(service.wordCount(input)).thenReturn(hm);
		
		mockMvc.perform(post("/search").content("{ \"searchText\" : [\"Duis\",\"Sed\",\"Donec\",\"Augue\",\"Pellentesque\",\"123\"]}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	@Test
	public void topText() throws Exception{
		int top =5;
		List<String> topText = new ArrayList<>();
		
		topText.add("eget|17");
		topText.add("vel|17");
		topText.add("et|14");
		topText.add("eu|13");
		topText.add("sit|12");
		
		when(service.topText(top)).thenReturn(topText);
		
		mockMvc.perform(get("/top/5"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}

}
