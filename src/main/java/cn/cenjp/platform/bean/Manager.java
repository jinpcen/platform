package cn.cenjp.platform.bean;

public class Manager {
    private Integer manager_id;
    private String password;

    public Manager() {
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + manager_id +
                ", password='" + password + '\'' +
                '}';
    }
}
