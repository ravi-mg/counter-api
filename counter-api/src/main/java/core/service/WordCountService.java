package core.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WordCountService {
    public Map<String, Integer> wordCount(String[] var1) throws FileNotFoundException, IOException;

    public List<String> topText(int var1) throws FileNotFoundException, IOException;
}
