package cn.cenjp.platform.bean;


public class User {
    private Integer user_id;
    private String user_name;
    private String user_sex;
    private String user_addr;
    private String user_phone;
    private String user_password;
    private String user_email;
    private String salt;

    public User() {
    }

    public User(Integer user_id, String user_name, String user_sex, String user_addr, String user_phone, String user_password, String user_email, String salt) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_addr = user_addr;
        this.user_phone = user_phone;
        this.user_password = user_password;
        this.user_email = user_email;
        this.salt = salt;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_addr='" + user_addr + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
