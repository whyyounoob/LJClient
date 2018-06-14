package Returns;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

public class FriendsResult {

    private Friend[] friends;

    public FriendsResult(Map map) throws UnsupportedEncodingException {
        friends = Friend.createFriends((Object[]) map.get("friends"));
    }

    public Friend[] getFriends() {
        return friends;
    }
}
