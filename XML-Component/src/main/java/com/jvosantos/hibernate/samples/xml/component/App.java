/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvosantos.hibernate.samples.xml.component;

import com.jvosantos.hibernate.samples.xml.component.entities.Address;
import com.jvosantos.hibernate.samples.xml.component.entities.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Vasco Santos
 */
public class App {
    public static void main(String[] args) {
        Session session = DatabaseSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            
            Address billAddress = new Address("Dummy Street 1 E", "Dummy City", "12345");
            Address homeAddress = new Address("Dummy Boulevard 2 R", "Stuff City", "54321");
            
            Person person = new Person("John Doe", billAddress, homeAddress);
            
            session.save(person);
            
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
