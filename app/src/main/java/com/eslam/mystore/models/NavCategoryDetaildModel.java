package com.eslam.mystore.models;

public class NavCategoryDetaildModel {
    String name;
    String price;
    String type;
    String img_url;


    public NavCategoryDetaildModel(String name, String price, String type, String img_url) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.img_url = img_url;
    }
    public NavCategoryDetaildModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
