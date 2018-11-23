package com.example.lrolan.rss;

public class Item {
    private String title;
    private String description;
    private String pubDate;
    private String enclosure;

    public Item(String title, String description, String pubDate, String enclosure) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.enclosure = enclosure;
    }
}
