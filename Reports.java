import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

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

    public List<Book> generateBookAvailabilityReport() {
        List<Book> availableBooks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE is_available = true");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setAvailable(true);
                availableBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableBooks;
    }

    public List<Book> generateBorrowingHistoryReport() {
        List<Book> borrowedBooks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE is_available = false");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setAvailable(false);
                borrowedBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }

    public double calculateTotalFines() {
        double totalFines = 0.0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(fine_amount) AS total_fines FROM fines");
            if (resultSet.next()) {
                totalFines = resultSet.getDouble("total_fines");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalFines;
    }
}
