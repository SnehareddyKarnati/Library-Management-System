import java.util.ArrayList;
import java.util.List;

class Patron {
    private String name;
    private String contactInfo;
    private List<Book> borrowingHistory;

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void borrowBook(Book book) {
        borrowingHistory.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowingHistory.remove(book);
        book.setAvailable(true);
    }
}

class Library {
    private List<Book> books;
    private List<Patron> patrons;

    public Library() {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
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

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void removePatron(Patron patron) {
        patrons.remove(patron);
    }

    public List<Patron> getPatrons() {
        return patrons;
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding some books to the library
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));

        // Adding some patrons to the library
        library.addPatron(new Patron("Alice", "alice@example.com"));
        library.addPatron(new Patron("Bob", "bob@example.com"));

        // Displaying available books
        library.displayAvailableBooks();

        // Example of a patron borrowing a book
        Patron alice = library.getPatrons().get(0);
        Book bookToBorrow = library.getBooks().get(0);
        alice.borrowBook(bookToBorrow);

        // Displaying Alice's borrowing history
        System.out.println("Alice's borrowing history:");
        for (Book book : alice.getBorrowingHistory()) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre());
        }

        // Example of a patron returning a book
        alice.returnBook(bookToBorrow);
        System.out.println("Book returned by Alice");
        library.displayAvailableBooks();
    }
}
