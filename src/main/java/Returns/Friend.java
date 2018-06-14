package Returns;

import Controller.Helpers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class Friend {
    private String username;
    private String fullname;
    private String type;
    private String bday;

    public Friend(Map map) throws UnsupportedEncodingException {
        username = (String) map.get("username");
        fullname = Helpers.bytesToString(map.get("fullname"));
        type = (String) map.get("type");
        bday = (String) map.get("birthday");
    }

    public String getBday() {
        return bday;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getType() {
        return type;
    }

    public static Friend[] createFriends(Object[] objects) throws UnsupportedEncodingException {
        if (objects == null) {
            return null;
        }
        Friend[] result = new Friend[objects.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Friend((Map) objects[i]);
        }
        return result;

    }
}
