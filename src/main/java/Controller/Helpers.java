package Controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {

    public static String bytesToString(Object text) throws UnsupportedEncodingException {
        if (text == null) {
            return null;
        } else if (text instanceof byte[]) {
            return new String((byte[]) text, "UTF-8");
        } else {
            return String.valueOf(text);
        }
    }

    public static Date parseDate(String dateString, SimpleDateFormat simpleDateFormat) throws ParseException {
        if (dateString != null && dateString.length() > 0) {
            return simpleDateFormat.parse(dateString);
        } else {
            return null;
        }
    }
}
