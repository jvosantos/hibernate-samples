/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvosantos.hibernate.samples.xml.helloworld.entities;

/**
 *
 * @author Vasco Santos
 */
public class Message {

    private Long id;
    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return "Message [id=" + id + ", text=\"" + text + "\"]";
    }
}
