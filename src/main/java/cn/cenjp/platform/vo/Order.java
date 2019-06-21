package cn.cenjp.platform.vo;

import cn.cenjp.platform.bean.Good;
import cn.cenjp.platform.bean.MySpike;

import java.time.LocalDateTime;

public class Order {
    private String goodName;
    private String goodOiginalPrice;
    private String spikePrice;
    private LocalDateTime spikeDate;

    public Order() {
    }

    public Order(String goodName, String goodOiginalPrice, String spikePrice, LocalDateTime spikeDate) {
        this.goodName = goodName;
        this.goodOiginalPrice = goodOiginalPrice;
        this.spikePrice = spikePrice;
        this.spikeDate = spikeDate;
    }


    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodOiginalPrice() {
        return goodOiginalPrice;
    }

    public void setGoodOiginalPrice(String goodOiginalPrice) {
        this.goodOiginalPrice = goodOiginalPrice;
    }

    public String getSpikePrice() {
        return spikePrice;
    }

    public void setSpikePrice(String spikePrice) {
        this.spikePrice = spikePrice;
    }

    public LocalDateTime getSpikeDate() {
        return spikeDate;
    }

    public void setSpikeDate(LocalDateTime spikeDate) {
        this.spikeDate = spikeDate;
    }


    @Override
    public String toString() {
        return "Order{" +
                "goodName='" + goodName + '\'' +
                ", goodOiginalPrice='" + goodOiginalPrice + '\'' +
                ", spikePrice='" + spikePrice + '\'' +
                ", spikeDate=" + spikeDate +
                '}';
    }
}
