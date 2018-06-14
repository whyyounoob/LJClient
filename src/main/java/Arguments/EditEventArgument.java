package Arguments;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class EditEventArgument {

    private Map<String, Object> struct = new HashMap<String, Object>();

    public EditEventArgument(int itemID, String subject, String event) {
        setUsername(Base.getInstance().getUsername());
        setPassword(Base.getInstance().getPassword());
        setAuth_method();
        setVer();
        setItemID(itemID);
        setSubject(subject);
        setEvent(event);
        setLineendings("pc");
        if (subject.equals("") && event.equals("")) {
        } else {
            setDate();
        }

    }

    public void setUsername(String username) {
        struct.put("username", username);
    }

    private void setPassword(String password) {
        struct.put("password", password);
    }

    private void setAuth_method() {
        struct.put("auth_method", "clear");
    }

    private void setVer() {
        struct.put("ver", 1);
    }

    private void setItemID(int ID) {
        struct.put("itemid", ID);
    }

    private void setSubject(String subject) {
        struct.put("subject", subject);
    }

    private void setEvent(String event) {
        struct.put("event", event);
    }

    private void setLineendings(String lineendings) {
        struct.put("lineendings", lineendings);
    }

    private void setDate() {
        Calendar calendar = new GregorianCalendar();

        struct.put("year", calendar.get(Calendar.YEAR));
        struct.put("mon", calendar.get(Calendar.MONTH + 1));
        struct.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        struct.put("hour", calendar.get(Calendar.HOUR_OF_DAY));
        struct.put("min", calendar.get(Calendar.MINUTE));
    }

    public Map<String, Object> getStruct() {
        return struct;
    }
}
