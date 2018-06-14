package Returns;

import Controller.Helpers;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BlogEntry {

    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH:mm:ss");

    private int itemid;
    private Integer anum;
    private String body;
    private Date date;
    private String subject;
    private Integer reply_count;
    private String URL;

    public BlogEntry(Map map) throws UnsupportedEncodingException, ParseException {
        itemid = (Integer) map.get("itemid");
        anum = (Integer) map.get("anum");
        subject = Helpers.bytesToString(map.get("subject"));
        body = Helpers.bytesToString(map.get("event"));
        date = Helpers.parseDate((String) map.get("eventtime"), DATEFORMAT);
        reply_count = (Integer) map.get("reply_count");
        URL = (String) map.get("url");
    }

    public String getURL() {
        return URL;
    }

    public int getItemid() {
        return itemid;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getReply_count() {
        return reply_count;
    }

    public Integer getDItemId() {
        return BlogEntry.getDItemId(this.itemid, this.anum);
    }

    public static Integer getDItemId(int entryId, int anum) {
        return entryId * 256 + anum;
    }

}
