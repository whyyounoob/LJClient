package Controller;

import Arguments.*;
import Returns.BlogEntry;
import Returns.FriendsResult;
import Returns.PostResult;
import Returns.UserData;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class XMLLJClient {

    private XmlRpcClient client;

    private static volatile XMLLJClient instance;

    private XMLLJClient() {
        client = new XmlRpcClient();
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL("http://www.livejournal.com/interface/xmlrpc"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        client.setConfig(config);
    }

    public static XMLLJClient getInstance() {
        if (instance == null)
            synchronized (XMLLJClient.class) {
                if (instance == null) instance = new XMLLJClient();
            }
        return instance;
    }

    public UserData login(LoginArgument argument) {
        UserData user = null;
        try {
            Map userData = (Map) client.execute("LJ.XMLRPC.login", new Object[]{argument.getStruct()});
            user = new UserData(userData);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return user;
    }

    public FriendsResult getFriends(GetFriendsArgument argument) {
        FriendsResult friendsData = null;
        try {
            Map map = (Map) client.execute("LJ.XMLRPC.getfriends", new Object[]{argument.getStruct()});
            friendsData = new FriendsResult(map);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return friendsData;
    }

    public PostResult postevent(PostArgument argument) {
        PostResult postResult = null;
        try {
            Map map = (Map) client.execute("LJ.XMLRPC.postevent", new Object[]{argument.getStruct()});
            postResult = new PostResult(map);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return postResult;

    }

    public BlogEntry[] getEvents(GetEventsArgument argument) {
        Map map = null;
        BlogEntry[] mas = null;
        List<BlogEntry> list = new ArrayList<BlogEntry>();
        try {
            map = (Map) client.execute("LJ.XMLRPC.getevents", new Object[]{argument.getStruct()});
            Object[] array = (Object[]) map.get("events");

            for (Object elem : array) {
                Map el = (Map) elem;
                BlogEntry blogEntry = new BlogEntry(el);
                list.add(blogEntry);
            }
            mas = list.toArray(new BlogEntry[list.size()]);
            return mas;
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mas;
    }

    public int editEvents(EditEventArgument argument) {
        Map map = null;
        int integer = 0;
        try {
            map = (Map) client.execute("LJ.XMLRPC.editevent", new Object[]{argument.getStruct()});
            integer = (int) map.get("itemid");
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return integer;
    }

    public int editFriend(EditFriendsArgument argument) {
        try {
            Map map = (Map) client.execute("LJ.XMLRPC.editfriends", new Object[]{argument.getStruct()});
            return 1;
        } catch (XmlRpcException e) {
            return 0;
        }
    }

}
