package Returns;

import java.util.Map;

public class PostResult {

    private String url;

    public PostResult(Map map) {
        url = (String) map.get("url");
    }

    public String getUrl() {
        return url;
    }
}
