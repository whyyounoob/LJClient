package Arguments;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class PostArgument {

    protected Map<String, Object> struct = new HashMap<String, Object>();

    public PostArgument(String subject, String event) {
        setUsername(Base.getInstance().getUsername());
        setPassword(Base.getInstance().getPassword());
        setAuthMethod(Base.getInstance().getAuth_method());
        setLineendings("pc");
        setSubject(subject);
        setEvent(event);
        struct.put("ver", 1);
        setDate();
    }

    public void setUsername(String username) {
        struct.put("username", username);
    }

    public void setAuthMethod(String method) {
        struct.put("auth_method", method);
    }

    public void setPassword(String password) {
        struct.put("password", password);
    }

    public void setSubject(String subject) {
        struct.put("subject", subject);
    }

    public void setEvent(String event) {
        struct.put("event", event);
    }

    public void setLineendings(String lineendings) {
        struct.put("lineendings", lineendings);
    }

    public void setDate() {
        Calendar calendar = new GregorianCalendar();

        struct.put("year", calendar.get(Calendar.YEAR));
        struct.put("mon", calendar.get(Calendar.MONTH)+1);
        struct.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        struct.put("hour", calendar.get(Calendar.HOUR_OF_DAY));
        struct.put("min", calendar.get(Calendar.MINUTE));
    }

    public Map<String, Object> getStruct() {
        return struct;
    }
}
