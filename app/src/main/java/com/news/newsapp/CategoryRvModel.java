package com.news.newsapp;

public class CategoryRvModel {
    private String category;
    private String categoryimageurl;

    public CategoryRvModel(String category, String categoryimageurl) {
        this.category = category;
        this.categoryimageurl = categoryimageurl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryimageurl() {
        return categoryimageurl;
    }

    public void setCategoryimageurl(String categoryimageurl) {
        this.categoryimageurl = categoryimageurl;
    }
}
