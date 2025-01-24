import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends Person {

    private Set<Book> loanedBooks;
    private double balance;
    private final Library library;

    public User(int id, String name, String surname, int age, double balance, Library library) {
        super(id, name, surname, age, library);
        this.balance = balance;
        this.loanedBooks = new HashSet<>();
        this.library = library;
    }

    public Set<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public void setLoanedBooks(Set<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void borrowBook(Book book) {
        List<Book> books = new ArrayList<>();
        books.add(book);
        borrowBooks(books);
    }

    @Override
    public void borrowBooks(List<Book> books) {
        double totalCost = books.stream()
                .mapToDouble(book -> book.getUnitPrice())
                .sum();

        if (balance < totalCost) {
            System.out.println("You do not have enough balance to loan these books");
            return;
        }

        if (loanedBooks.size() + books.size() > 5) {
            System.out.println("You cannot loan more than 5 books in total");
            return;
        }

        for (Book book : books) {
            for (User user : library.getUsers().values()) {
                if (user.getLoanedBooks().contains(book)) {
                    System.out.println("Book '" + book.getName() + "' is already loaned by user: " + user.getName());
                    return;
                }
            }
        }

        for (Book book : books) {
            book.setAvailable(false);
            loanedBooks.add(book);
        }
        
        balance -= totalCost;

        Invoice invoice = new Invoice(
            library.getInvoices().size() + 1,
            books,
            this,
            false,
            totalCost
        );
        library.getInvoices().add(invoice);
        System.out.println("Fatura oluşturuldu:\n" + invoice);
    }

    @Override
    public void returnBook(Book book) {
        List<Book> books = new ArrayList<>();
        books.add(book);
        returnBooks(books);
    }

    @Override
    public void returnBooks(List<Book> books) {
        for (Book book : books) {
            if (!loanedBooks.contains(book)) {
                System.out.println("You have not loaned the book: " + book.getName());
                return;
            }
        }

        double totalRefund = books.stream()
                .mapToDouble(book -> book.getUnitPrice())
                .sum();

        for (Book book : books) {
            loanedBooks.remove(book);
            book.setAvailable(true);
        }
        
        balance += totalRefund;

        Invoice invoice = new Invoice(
            library.getInvoices().size() + 1,
            books,
            this,
            true,
            totalRefund
        );
        library.getInvoices().add(invoice);
        System.out.println("İade faturası oluşturuldu:\n" + invoice);
    }

    @Override
    public Book findBookById(int id) {
        return super.findBookById(id);
    }

    @Override
    public List<Book> findBooksByName(String name) {
        return super.findBooksByName(name);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return super.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findBooksByCategory(String categoryName) {
        return super.findBooksByCategory(categoryName);
    }
}
