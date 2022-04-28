package com.eslam.mystore.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String name;
    String description;
    String rating;
    String imge_url;
    String type;

    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, String rating, String imge_url, String type, int price) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imge_url = imge_url;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImge_url() {
        return imge_url;
    }

    public void setImge_url(String imge_url) {
        this.imge_url = imge_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
