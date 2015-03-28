package com.jvosantos.orm.jpa.domain;

public class Chapter {
    private int number;
    private String title;

    public Chapter() {
    }
    
    public Chapter(int number, String name) {
        this.number = number;
        this.title = name;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Chapter{[" + super.toString() + "]" + ", number=" + number + ", name=" + title + '}';
    }
}
