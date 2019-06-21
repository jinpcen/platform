package cn.cenjp.platform.Prefix;

public interface KeyPrefix {
    /**
     * 定义过期时间
     * @return
     */
    public int expireSeconds();

    /**
     * 定义前缀
     * @return
     */
    public String getPrefix();
}
