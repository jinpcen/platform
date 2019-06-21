package cn.cenjp.platform.utils;



public class MD5Util{
    public static String md5(String src){
        return com.alibaba.druid.util.Utils.md5(src);
    }

    private static final String salt="a1b2c3d4";

    public static String inputPassToFormPass(String inputPass){
        String s = ""+salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(s);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String s = ""+salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(s);
    }

    public static  String inputPassTODBPass(String input,String saltDB){
        String inputPassToFormPass = inputPassToFormPass(input);
        String toDBPass = formPassToDBPass(inputPassToFormPass,saltDB);
        return toDBPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassTODBPass("123456","1a2b3c4d"));
    }
}
