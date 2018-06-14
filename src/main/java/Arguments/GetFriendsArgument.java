package Arguments;

import java.util.HashMap;
import java.util.Map;

public class GetFriendsArgument {

    private Map<String, Object> struct = new HashMap<String, Object>();

    public GetFriendsArgument() {
        setUsername(Base.getInstance().getUsername());
        setPassword(Base.getInstance().getPassword());
        setAuth_method();
        setVer();
        setIncludeFriendOf();
        setIncludeGroups();
        setIncludeBDays();
    }

    public void setUsername(String username) {
        struct.put("username", username);
    }

    public void setPassword(String password) {
        struct.put("password", password);
    }

    public void setAuth_method() {
        struct.put("auth_method", "clear");
    }

    public void setVer() {
        struct.put("ver", 1);
    }

    public void setIncludeFriendOf() {
        struct.put("includefriendof", 0);
    }

    public void setIncludeGroups() {
        struct.put("includegroups", 0);
    }

    public void setIncludeBDays() {
        struct.put("includebdays", 1);
    }

    public Map<String, Object> getStruct() {
        return struct;
    }
}
