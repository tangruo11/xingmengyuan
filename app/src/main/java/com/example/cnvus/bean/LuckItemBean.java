package com.example.cnvus.bean;

public class LuckItemBean {
  private   String title;
   private String content;
   private int color;

    public LuckItemBean(String title, String content,int color) {
        this.title = title;
        this.content = content;
        this.color=color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
