package cn.cenjp.platform.vo;


import java.sql.Timestamp;
import java.util.Date;

public class GoodVo{
    private Integer good_id;
    private String good_introduction;
    private Integer good_kind;
    private String good_name;
    private Double good_originalPrice;
    private String good_image;
    private Integer spike_count;
    private Timestamp spike_endTime;
    private Integer spike_id;
    private Double spike_price;
    private Timestamp spike_startTime;
    private Integer orinal_count;

    public GoodVo() {
    }


    public GoodVo(Integer good_id, String good_introduction, Integer good_kind, String good_name, Double good_originalPrice, String good_image,
                  Integer spike_count, Timestamp spike_endTime, Integer spike_id, Double spike_price, Timestamp spike_startTime, Integer orinal_count) {
        this.good_id = good_id;
        this.good_introduction = good_introduction;
        this.good_kind = good_kind;
        this.good_name = good_name;
        this.good_originalPrice = good_originalPrice;
        this.good_image = good_image;
        this.spike_count = spike_count;
        this.spike_endTime = spike_endTime;
        this.spike_id = spike_id;
        this.spike_price = spike_price;
        this.spike_startTime = spike_startTime;
        this.orinal_count = orinal_count;
    }

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public String getGood_introduction() {
        return good_introduction;
    }

    public void setGood_introduction(String good_introduction) {
        this.good_introduction = good_introduction;
    }

    public Integer getGood_kind() {
        return good_kind;
    }

    public void setGood_kind(Integer good_kind) {
        this.good_kind = good_kind;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public Double getGood_originalPrice() {
        return good_originalPrice;
    }

    public void setGood_originalPrice(Double good_originalPrice) {
        this.good_originalPrice = good_originalPrice;
    }

    public String getGood_image() {
        return good_image;
    }

    public void setGood_image(String good_image) {
        this.good_image = good_image;
    }

    public Integer getSpike_count() {
        return spike_count;
    }

    public void setSpike_count(Integer spike_count) {
        this.spike_count = spike_count;
    }

    public Timestamp getSpike_endTime() {
        return spike_endTime;
    }

    public void setSpike_endTime(Timestamp spike_endTime) {
        this.spike_endTime = spike_endTime;
    }

    public Integer getSpike_id() {
        return spike_id;
    }

    public void setSpike_id(Integer spike_id) {
        this.spike_id = spike_id;
    }

    public Double getSpike_price() {
        return spike_price;
    }

    public void setSpike_price(Double spike_price) {
        this.spike_price = spike_price;
    }

    public Timestamp getSpike_startTime() {
        return spike_startTime;
    }

    public void setSpike_startTime(Timestamp spike_startTime) {
        this.spike_startTime = spike_startTime;
    }

    public Integer getOrinal_count() {
        return orinal_count;
    }

    public void setOrinal_count(Integer orinal_count) {
        this.orinal_count = orinal_count;
    }

    @Override
    public String toString() {
        return "GoodVo{" +
                "good_id=" + good_id +
                ", good_introduction='" + good_introduction + '\'' +
                ", good_kind=" + good_kind +
                ", good_name='" + good_name + '\'' +
                ", good_originalPrice=" + good_originalPrice +
                ", good_image='" + good_image + '\'' +
                ", spike_count=" + spike_count +
                ", spike_endTime=" + spike_endTime +
                ", spike_id=" + spike_id +
                ", spike_price=" + spike_price +
                ", spike_startTime=" + spike_startTime +
                ", orinal_count=" + orinal_count +
                '}';
    }
}