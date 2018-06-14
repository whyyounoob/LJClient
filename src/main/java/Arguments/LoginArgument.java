package Arguments;

import java.util.HashMap;
import java.util.Map;

public class LoginArgument {

    private Map<String, Object> struct = new HashMap<String, Object>();

    public LoginArgument() {
        setUsername(Base.getInstance().getUsername());
        setPassword(Base.getInstance().getPassword());
        setAuth_method();
        setVer();
        setGetmenus();
        setGetpickws();
        setGetpickwurls();
        setGetmoods();
        setGetcaps();
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

    public void setGetmoods(){
        struct.put("getmoods", 0);
    }

    public void setGetmenus() {
        struct.put("getmenus", 1);
    }

    public void setGetpickws() {
        struct.put("getpickws", 1);
    }

    public void setGetpickwurls() {
        struct.put("getpickwurls", 1);
    }

    public void setGetcaps(){
        struct.put("getcaps", 1);
    }

    public Map<String, Object> getStruct() {
        return struct;
    }
}
