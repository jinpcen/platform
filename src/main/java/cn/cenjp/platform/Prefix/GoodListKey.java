package cn.cenjp.platform.Prefix;

public class GoodListKey extends BasePrefix{

    public GoodListKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodListKey goodListKey=new GoodListKey(60*30,"GoodListKey");
}
