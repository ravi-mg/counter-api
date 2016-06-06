package rest.mvc;


import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import core.service.WordCountService;
import rest.resources.SearchTextResource;
import rest.resources.WordCountResult;

@RestController
public class WordCountController {
    @Autowired
    WordCountService wordCountService;

    @RequestMapping(value={"/search"}, method={RequestMethod.POST}, consumes={"application/json"})
    public ResponseEntity<WordCountResult> search(@RequestBody SearchTextResource searchText) throws IOException, Exception {
        Map<String, Integer> wordCount = new HashMap<>();
        wordCount = this.wordCountService.wordCount(searchText.getSearchText());
        WordCountResult wordCountResult = new WordCountResult(wordCount);
        return new ResponseEntity<WordCountResult>(wordCountResult, HttpStatus.OK);
    }

    @RequestMapping(value={"/top/{topId}"}, method={RequestMethod.GET})
    public void topResult(@PathVariable Integer topId, HttpServletResponse response) throws IOException, Exception {
        String csvFileName = "top_results.csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        List<String> topList = this.wordCountService.topText(topId.intValue());
        CsvListWriter csvWriter = new CsvListWriter((Writer)response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        for (String str : topList) {
            csvWriter.write(new String[]{str});
        }
        csvWriter.close();
    }
}
