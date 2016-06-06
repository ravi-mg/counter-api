package core.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import core.service.WordCountService;
import core.service.util.WordCounterUtil;

@Service
public class WordCountServiceImpl
implements WordCountService {
    public Map<String, Integer> wordCount(String[] searchText) throws FileNotFoundException, IOException {
        Map<String, Integer> totalWordCount = WordCounterUtil.wordCount();
        HashMap<String, Integer> finalMap = new HashMap<String, Integer>();
        
        for (int i=0; i< searchText.length;i++) {
            if (totalWordCount.get(searchText[i]) != null) {
                finalMap.put(searchText[i], (Integer)totalWordCount.get(searchText[i]));
            } else {
                finalMap.put(searchText[i], 0);
            }
            
        }
        return finalMap;
    }

    public List<String> topText(int top) throws FileNotFoundException, IOException {
        return WordCounterUtil.topTexts((int)top);
    }
}
