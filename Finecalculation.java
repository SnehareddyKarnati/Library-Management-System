import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    private LocalDate dueDate;

    public Book(String title, String author, String genre, LocalDate dueDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

class Patron {
    private String name;
    private String contactInfo;
    private List<Book> borrowedBooks;

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

class Library {
    private List<Book> books;
    private List<Patron> patrons;
    private double finePerDay;

    public Library(double finePerDay) {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
        this.finePerDay = finePerDay;
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

    public void calculateFines() {
        for (Patron patron : patrons) {
            for (Book book : patron.getBorrowedBooks()) {
                if (!book.isAvailable()) {
                    long daysOverdue = ChronoUnit.DAYS.between(LocalDate.now(), book.getDueDate());
                    if (daysOverdue > 0) {
                        double fine = finePerDay * daysOverdue;
                        System.out.println("Fine for " + patron.getName() + "'s book '" + book.getTitle() + "': $" + fine);
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library(0.5); // Fine rate $0.5 per day

        // Adding some books to the library
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", LocalDate.of(2024, 2, 20)));
        library.addBook(new Book("1984", "George Orwell", "Dystopian", LocalDate.of(2024, 2, 15)));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", LocalDate.of(2024, 2, 10)));

        // Adding some patrons to the library
        library.addPatron(new Patron("Alice", "alice@example.com"));
        library.addPatron(new Patron("Bob", "bob@example.com"));

        // Alice borrows a book
        Patron alice = library.getPatrons().get(0);
        Book bookToBorrow = library.getBooks().get(0);
        alice.borrowBook(bookToBorrow);

        // Displaying available books
        System.out.println("Available Books:");
        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre());
            }
        }

        // Calculating fines
        library.calculateFines();
    }
}
