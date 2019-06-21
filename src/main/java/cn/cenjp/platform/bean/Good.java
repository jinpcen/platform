package cn.cenjp.platform.bean;

public class Good {
    private Integer good_id;
    private String good_name;
    private String good_introduction;
    private Long good_originalPrice;
    private Integer good_kind;
    private String good_image;
    public Good() {
    }

    public Good(Integer good_id, String good_name, String good_introduction, Long good_originalPrice, Integer good_kind, String good_image) {
        this.good_id = good_id;
        this.good_name = good_name;
        this.good_introduction = good_introduction;
        this.good_originalPrice = good_originalPrice;
        this.good_kind = good_kind;
        this.good_image = good_image;
    }

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getGood_introduction() {
        return good_introduction;
    }

    public void setGood_introduction(String good_introduction) {
        this.good_introduction = good_introduction;
    }

    public Long getGood_originalPrice() {
        return good_originalPrice;
    }

    public void setGood_originalPrice(Long good_originalPrice) {
        this.good_originalPrice = good_originalPrice;
    }

    public Integer getGood_kind() {
        return good_kind;
    }

    public void setGood_kind(Integer good_kind) {
        this.good_kind = good_kind;
    }

    public String getGood_image() {
        return good_image;
    }

    public void setGood_image(String good_image) {
        this.good_image = good_image;
    }
}
