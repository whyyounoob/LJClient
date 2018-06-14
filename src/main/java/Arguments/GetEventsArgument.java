package Arguments;

import java.util.HashMap;
import java.util.Map;

public class GetEventsArgument {

    private Map<String, Object> struct = new HashMap<String, Object>();

    public GetEventsArgument() {
        setUsername(Base.getInstance().getUsername());
        setPassword(Base.getInstance().getPassword());
        setAuth_method();
        setVer();
        setTruncate(0);
        setSelectType("lastn");
        setHowMany(50);
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

    public void setTruncate(int truncate){
        struct.put("truncate", truncate);
    }

    public void setSelectType(String type){
        struct.put("selecttype", type);
    }

    public void setHowMany(int number){
        struct.put("howmany", number);
    }

    public Map<String, Object> getStruct() {
        return struct;
    }
}
