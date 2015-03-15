package com.jvosantos.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import com.jvosantos.orm.jpa.translator.BookStoreDatabaseHandler;
import com.jvosantos.orm.jpa.domain.Book;
import com.jvosantos.orm.jpa.domain.Chapter;
import com.jvosantos.orm.jpa.domain.Publisher;
import java.util.Calendar;

public class App {

    public static void main(String[] args) {
        BookStoreDatabaseHandler bookStoreHandler = new BookStoreDatabaseHandler();

        // Creating an object graph and persist it
        Publisher publisher = new Publisher("Addison Reilly");
        
        Book bookDraft = new Book("1234567890123", "Java Persistence with JPA", publisher);
        
        List<Chapter> chapters = new ArrayList<>();
        Chapter chapter1 = new Chapter(1, "Introducing JPA and Hibernate");
        chapters.add(chapter1);
        Chapter chapter2 = new Chapter(2, "Domain Models and Metadata");
        chapters.add(chapter2);
        
        bookDraft.setChapters(chapters);
        bookDraft.setPublicationDate(Calendar.getInstance().getTime());
        
        bookStoreHandler.saveBook(bookDraft);
        System.out.println(bookDraft);

        // Retrieving object graph
        Book bookReleased = bookStoreHandler.getBook("1234567890123");
        System.out.println(bookReleased);
        /*
         Book book = bookStoreService.retrieveObjectGraph("9781617290459");
         System.out.println(book);
        */
    }
}
