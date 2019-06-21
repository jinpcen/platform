package cn.cenjp.platform.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class SpikeGoods {

    private Integer spike_id;
    private Integer user_id;
    private Integer good_id;
    private Double spike_price;
    private LocalDateTime spike_startTime;
    private LocalDateTime spike_endTime;
    private Integer spike_count;
    private String good_image;
    private Integer orinal_count;

    public SpikeGoods() {
    }

    public SpikeGoods(Integer spike_id, Integer user_id, Integer good_id,
                      Double spike_price, LocalDateTime spike_startTime, LocalDateTime spike_endTime, Integer spike_count, String good_image, Integer orinal_count) {
        this.spike_id = spike_id;
        this.user_id = user_id;
        this.good_id = good_id;
        this.spike_price = spike_price;
        this.spike_startTime = spike_startTime;
        this.spike_endTime = spike_endTime;
        this.spike_count = spike_count;
        this.good_image = good_image;
        this.orinal_count = orinal_count;
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

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Double getSpike_price() {
        return spike_price;
    }

    public void setSpike_price(Double spike_price) {
        this.spike_price = spike_price;
    }

    public LocalDateTime getSpike_startTime() {
        return spike_startTime;
    }

    public void setSpike_startTime(LocalDateTime spike_startTime) {
        this.spike_startTime = spike_startTime;
    }

    public LocalDateTime getSpike_endTime() {
        return spike_endTime;
    }

    public void setSpike_endTime(LocalDateTime spike_endTime) {
        this.spike_endTime = spike_endTime;
    }

    public Integer getSpike_count() {
        return spike_count;
    }

    public void setSpike_count(Integer spike_count) {
        this.spike_count = spike_count;
    }

    public String getGood_image() {
        return good_image;
    }

    public void setGood_image(String good_image) {
        this.good_image = good_image;
    }

    public Integer getOrinal_count() {
        return orinal_count;
    }

    public void setOrinal_count(Integer orinal_count) {
        this.orinal_count = orinal_count;
    }
}
