package Arguments;

public final class Base {

    private static String username;
    private static String password;
    private static String auth_method;
    private static int ver;

    private static volatile Base instance;

    public static Base getInstance() {
        if (instance == null)
            synchronized (Base.class) {
                if (instance == null) instance = new Base();
            }
        return instance;
    }

    private Base() {
        auth_method = new String("clear");
        ver = 1;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuth_method() {
        return auth_method;
    }

    public int getVer(){
        return ver;
    }

    public void setData(String username, String password) {
        Base.username = username;
        Base.password = password;
    }
}

