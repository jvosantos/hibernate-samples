/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvosantos.hibernate.samples.annotation.helloworld;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jvosantos.hibernate.samples.annotation.helloworld.entities.Message;

/**
 *
 * @author Vasco Santos
 */
public class App {

    public static void main(String[] args) {
        Session session = DatabaseSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {

            Message message;
            long messageId;
            // Storing a message
            message = new Message("hello world");
            transaction = session.getTransaction();
            transaction.begin();
            session.save(message);
            transaction.commit();
            System.out.println("Stored message: " + message);
            messageId = message.getId();

            // breakpoint here to see message stored in database
            
            // Updating message
            message = (Message) session.get(Message.class, messageId);
            message.setText(message.getText().toUpperCase());
            transaction = session.getTransaction();
            transaction.begin();
            session.save(message);
            transaction.commit();
            System.out.println("Updated message: " + message);

            // breakpoint here to see message updated in database
            
            // Deleting message
            message = (Message) session.get(Message.class, messageId);
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(message);
            transaction.commit();
            System.out.println("Deleted message: " + message);
            
            // breakpoint here to see message deleted in database
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
