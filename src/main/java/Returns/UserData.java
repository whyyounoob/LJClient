package Returns;

import javafx.scene.image.Image;

import java.util.Map;

public class UserData {

    private String fullname;
    private String userId;
    private Image defaultImage;

    public UserData(Map map) {
        fullname = (String) map.get("fullname");
        defaultImage = new Image((String) map.get("defaultpicurl"));
        Integer str = (Integer) map.get("userid");
        userId = String.valueOf(str);
    }

    public String getFullname() {
        return fullname;
    }

    public Image getDefaultImage() {
        return defaultImage;
    }

    public String getUserId() {
        return userId;
    }

}
