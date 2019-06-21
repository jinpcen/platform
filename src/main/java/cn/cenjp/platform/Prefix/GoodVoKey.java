package cn.cenjp.platform.Prefix;

public class GoodVoKey extends BasePrefix {
    public GoodVoKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static  GoodVoKey goodVoKey=new GoodVoKey(0,"GoodVoKey");
    public static  GoodVoKey goodVoCount=new GoodVoKey(0,"GoodVoCount");

    public static void main(String[] args) {
        System.out.println(goodVoCount.getPrefix());
    }
}
