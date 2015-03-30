/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvosantos.hibernate.samples.xml.component.entities;

/**
 *
 * @author Vasco Santos
 */
public class Person {

    private Long id;
    private String name;
    private Address homeAddress;
    private Address billingAddress;

    public Person() {
    }
    
    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Address address) {
        this(name, address, address);
    }
    
    public Person(String name, Address homeAddress, Address billingAddress) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.billingAddress = billingAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", homeAddress=" + homeAddress + ", billingAddress=" + billingAddress + '}';
    }
}
