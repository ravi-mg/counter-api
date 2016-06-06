package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import core.service.util.WordCounterUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")
public class WordCountUtilTest {
	
	private Map<String, Integer> hm=null;
	List<String> topText = null;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException{
		hm=WordCounterUtil.wordCount();
		topText = WordCounterUtil.topTexts(5);
	}
	
	@Test
	public void testWordCount(){
		assertNotNull(hm);
		assertEquals(187, hm.size());
		assertEquals(true, hm.containsKey("Ut"));
		assertEquals(true, hm.containsKey("Maecenas"));
		assertEquals(true, hm.containsKey("habitant"));
		assertEquals(true, hm.containsKey("mattis"));
		assertEquals(true, hm.containsKey("neque"));
		assertEquals(new Integer(5), hm.get("Ut"));
		assertEquals(new Integer(3), hm.get("Maecenas"));
		assertEquals(new Integer(2), hm.get("habitant"));
		assertEquals(new Integer(6), hm.get("mattis"));
		assertEquals(new Integer(3), hm.get("neque"));
		
		
	}
	
	@Test
	public void testTopText(){
		assertNotNull(topText);
		assertEquals(5, topText.size());
		assertEquals("eget|17", topText.get(0));
		assertEquals("vel|17", topText.get(1));
		assertEquals("et|14", topText.get(2));
		assertEquals("eu|13", topText.get(3));
		assertEquals("sit|12", topText.get(4));
	}
}
