package cn.cenjp.platform.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class MySpike {
    private Integer spike_id;
    private Integer user_id;
    private Integer myspike_id;
    private  String ifpay;
    private String address;
    private LocalDateTime myspike_date;

    public MySpike() {
    }

    public MySpike(Integer spike_id, Integer user_id, Integer myspike_id, String ifpay, String address, LocalDateTime myspike_date) {
        this.spike_id = spike_id;
        this.user_id = user_id;
        this.myspike_id = myspike_id;
        this.ifpay = ifpay;
        this.address = address;
        this.myspike_date = myspike_date;
    }

    public Integer getSpike_id() {
        return spike_id;
    }

    public void setSpike_id(Integer spike_id) {
        this.spike_id = spike_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMyspike_id() {
        return myspike_id;
    }

    public void setMyspike_id(Integer myspike_id) {
        this.myspike_id = myspike_id;
    }

    public String getIfpay() {
        return ifpay;
    }

    public void setIfpay(String ifpay) {
        this.ifpay = ifpay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getMyspike_date() {
        return myspike_date;
    }

    public void setMyspike_date(LocalDateTime myspike_date) {
        this.myspike_date = myspike_date;
    }
}
