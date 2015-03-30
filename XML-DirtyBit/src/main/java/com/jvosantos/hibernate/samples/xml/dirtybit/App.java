/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvosantos.hibernate.samples.xml.dirtybit;

import com.jvosantos.hibernate.samples.xml.dirtybit.entities.Message;
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
            Message message;
            long messageId;
            // Storing a message
            message = new Message("dirty bit example message");
            transaction = session.getTransaction();
            transaction.begin();
            session.save(message);
            transaction.commit();
            System.out.println("Stored message: " + message);
            messageId = message.getId();
            
            // Updating message
            transaction = session.getTransaction();
            transaction.begin();
            message = (Message) session.get(Message.class, messageId);
            System.out.println("Fetched message: " + message);
            message.setText(message.getText().toUpperCase());
            // We could do a session.save(message), but hibernate will check 
            // for modifications within this transaction, i.e., if the state 
            // of the Message object got dirty, and will automatically 
            // persist the modifications to the database.
            transaction.commit();
            System.out.println("Updated message: " + message);

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
