package core.models.entities;

import java.io.Serializable;

public class WordCount
implements Serializable {
    private String searchText;
    private int count;

    public WordCount() {
    }

    public WordCount(String searchText) {
        this.searchText = searchText;
    }

    public WordCount(String searchText, int count) {
        this.searchText = searchText;
        this.count = count;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
