package com.jvosantos.orm.jpa.translator;

import com.jvosantos.orm.jpa.domain.Book;
import com.jvosantos.orm.jpa.domain.Chapter;
import com.jvosantos.orm.jpa.domain.Publisher;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vasco Santos
 */
public class BookStoreDatabaseHandler {

    private Connection connection = null;

    public void saveBook(Book book) {
        try {
            // insert publisher
            Class c = Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BOOKSTORE", "root", "password");
            
            // insert publisher
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO PUBLISHER (NAME) VALUES(?)");
            stmt.setString(1, book.getPublisher().getName());
            stmt.executeUpdate();
            // publisher inserted
            stmt.close();

            // insert book
            stmt = connection.prepareStatement("INSERT INTO BOOK (ISBN, NAME, PUBLICATION_DATE, PUBLISHER_NAME) VALUES(?, ?, ?, ?)");
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getName());
            stmt.setDate(3, new Date(book.getPublicationDate().getTime()));
            stmt.setString(4, book.getPublisher().getName());
            stmt.executeUpdate();
            // book inserted
            stmt.close();
            
            
            // insert chapters
            stmt = connection.prepareStatement("INSERT INTO CHAPTER (NUMBER, TITLE, BOOK_ISBN) VALUES(?, ?, ?)");
            for (Chapter chapter : book.getChapters()) {
                // inserting chapter
                stmt.setInt(1, chapter.getNumber());
                stmt.setString(2, chapter.getTitle());
                stmt.setString(3, book.getIsbn());
                stmt.executeUpdate();
                // chapter inserted
            }
            // chapters inserted
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Book getBook(String isbn) {
        Book book = null;
        try {
            Class c = Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BOOKSTORE", "root", "password");
            
            // Fetching book and publisher
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM BOOK WHERE BOOK.ISBN = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            book = new Book();
            if (rs.next()) {
                book.setIsbn(rs.getString("ISBN"));
                book.setName(rs.getString("NAME"));
                book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
                Publisher publisher = new Publisher();
                publisher.setName(rs.getString("PUBLISHER_NAME"));
                book.setPublisher(publisher);
            }
            rs.close();
            stmt.close();
            List<Chapter> chapters = new ArrayList<>();
            stmt = connection.prepareStatement("SELECT * FROM CHAPTER WHERE BOOK_ISBN = ?");
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Chapter chapter = new Chapter();
                chapter.setTitle(rs.getString("TITLE"));
                chapter.setNumber(rs.getInt("NUMBER"));
                chapters.add(chapter);
            }
            book.setChapters(chapters);
            rs.close();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }
}
