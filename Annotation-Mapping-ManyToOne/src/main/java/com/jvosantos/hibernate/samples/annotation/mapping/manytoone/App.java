package com.jvosantos.hibernate.samples.annotation.mapping.manytoone;

import com.jvosantos.hibernate.samples.annotation.mapping.manytoone.entities.Department;
import com.jvosantos.hibernate.samples.annotation.mapping.manytoone.entities.University;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.hibernate.Session;

/**
 *
 * @author Vasco Santos
 */
public class App {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Session session = DatabaseSessionFactory.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            
            University universityOfAveiro = new University("University of Aveiro", 
                    df.parse("15/12/1973"));
            
            Department computerEngineering = new Department("Department of Electronics and "
                    + "Telecommunications", df.parse("15/09/1974"));
            Department physics = new Department("Department of Physics", df.parse("10/09/1992"));
            
            universityOfAveiro.addDepartment(computerEngineering);
            universityOfAveiro.addDepartment(physics);
            
            // Should generate 3 insert statements
            session.persist(universityOfAveiro);
            
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
