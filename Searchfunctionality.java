import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Book {
    private int id;
    private String title;
    private String author;
    private String genre;

    // Constructor, getters, setters
}

class Patron {
    private int id;
    private String name;
    private String contactInfo;

    // Constructor, getters, setters
}

public class LibraryManager {
    private Connection connection;

    public LibraryManager() {
        try {
            // Establish connection to MySQL database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE title LIKE ?");
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Patron> searchPatrons(String keyword) {
        List<Patron> patrons = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM patrons WHERE name LIKE ?");
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Patron patron = new Patron();
                patron.setId(resultSet.getInt("id"));
                patron.setName(resultSet.getString("name"));
                patron.setContactInfo(resultSet.getString("contact_info"));
                patrons.add(patron);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patrons;
    }

    // Other methods for inserting, updating, and deleting data from the database
}
