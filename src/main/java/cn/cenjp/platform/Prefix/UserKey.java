package cn.cenjp.platform.Prefix;

public class UserKey extends BasePrefix{

    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey getById = new UserKey(60*60*24*3,"id");
    public static UserKey getByName = new UserKey(60*60*24*3,"name");
    public static UserKey getByPhone = new UserKey(60*60*3,"phone");
    public static UserKey token = new UserKey(60*60*3*24,"token");

}
