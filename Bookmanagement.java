import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Adding some books to the library
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        
        // Displaying available books
        library.displayAvailableBooks();
    }
}
