package com.news.newsapp;

import java.util.ArrayList;

public class NewsModel {
    private int totalResult;

    public NewsModel(int totalResult, ArrayList<Articles> articles, String status) {
        this.totalResult = totalResult;
        this.articles = articles;
        this.status = status;
    }

    private ArrayList<Articles> articles;
    private String status;

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
