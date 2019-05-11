package com.wall.wallpaperapp.Model;

public class Category_Model {

    private String category_image;
    private String category_name;

    public Category_Model() {
    }

    public Category_Model(String category_image, String category_name) {
        this.category_image = category_image;
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
