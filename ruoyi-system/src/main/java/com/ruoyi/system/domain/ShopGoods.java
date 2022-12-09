package com.ruoyi.system.domain;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2022年12月09日 11:16
 */
public class ShopGoods {
    private String id;
    private String image ;
    private String store_name ;
    private String store_info ;
    private String cate_ ;
    private String price ;
    private String ot_price ;
    private String sales ;
    private String unit_name ;
    private Long sort;
    private String[] activity;
    private Long stock;
    private Long vip_price;
    private Long is_vip;
    private String[] coupon;
    private String[] star;
    private boolean checkCoupon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_info() {
        return store_info;
    }

    public void setStore_info(String store_info) {
        this.store_info = store_info;
    }

    public String getCate_() {
        return cate_;
    }

    public void setCate_(String cate_) {
        this.cate_ = cate_;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOt_price() {
        return ot_price;
    }

    public void setOt_price(String ot_price) {
        this.ot_price = ot_price;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String[] getActivity() {
        return activity;
    }

    public void setActivity(String[] activity) {
        this.activity = activity;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getVip_price() {
        return vip_price;
    }

    public void setVip_price(Long vip_price) {
        this.vip_price = vip_price;
    }

    public Long getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(Long is_vip) {
        this.is_vip = is_vip;
    }

    public String[] getCoupon() {
        return coupon;
    }

    public void setCoupon(String[] coupon) {
        this.coupon = coupon;
    }

    public String[] getStar() {
        return star;
    }

    public void setStar(String[] star) {
        this.star = star;
    }

    public boolean isCheckCoupon() {
        return checkCoupon;
    }

    public void setCheckCoupon(boolean checkCoupon) {
        this.checkCoupon = checkCoupon;
    }
}
