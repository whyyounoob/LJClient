package Arguments;

import java.util.HashMap;
import java.util.Map;

public class EditFriendsArgument {

    private Map<String, Object> struct = new HashMap<String, Object>();

    public EditFriendsArgument(String what, String username) {
        setUsername(Base.getInstance().getUsername());
        setPassword(Base.getInstance().getPassword());
        setAuth_method();
        setVer();
        setEdit(what, username);

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

    public void setEdit(String name, String username) {
        switch (name) {
            case "delete":
                String arr[] = new String[]{username};
                struct.put("delete", arr);
                break;
            case "add":
                String[] map = new String[]{username};
                //map.put("username", username);
                struct.put("add", map);
                break;
        }
    }

    public Map<String, Object> getStruct() {
        return struct;
    }
}
