package com.eslam.mystore.models;

public class HomeCategory {

    private String name;
    private String type;
    private String imge_url;

    public HomeCategory() {
    }

    public HomeCategory(String name, String type, String imge_url) {
        this.name = name;
        this.type = type;
        this.imge_url = imge_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImge_url() {
        return imge_url;
    }

    public void setImge_url(String imge_url) {
        this.imge_url = imge_url;
    }
}
