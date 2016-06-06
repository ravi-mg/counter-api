package rest.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordCountResult
implements Serializable {
    List<Map.Entry<String, Integer>> counts = new ArrayList<Map.Entry<String, Integer>>();

    public List<Map.Entry<String, Integer>> getCounts() {
        return this.counts;
    }

    public void setCounts(List<Map.Entry<String, Integer>> counts) {
        this.counts = counts;
    }

    public WordCountResult() {
    }

    public WordCountResult(Map<String, Integer> map) {
        this.counts = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
    }
}
