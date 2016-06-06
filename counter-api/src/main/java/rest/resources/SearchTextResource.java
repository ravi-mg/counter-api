package rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchTextResource
extends ResourceSupport {
    String[] searchText;

    public String[] getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String[] searchText) {
        this.searchText = searchText;
    }
}
