import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends Person {

    private Set<Book> borrowedBooks;
    private double balance;

    User(String tckno, String username, String password, String name, String surname, int age, double balance, Library library) {
        super(tckno, username, password, name, surname, age, library);
        
        if (balance < 0) {
            throw new IllegalArgumentException("Bakiye negatif olamaz!");
        }

        this.borrowedBooks = new HashSet<>();
        this.balance = balance;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
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
        if (books == null || books.isEmpty()) {
            throw new IllegalArgumentException("Kitap listesi boş olamaz!");
        }

        double totalCost = 0.0;
        for(Book book : books) {
            if (book == null) {
                throw new IllegalArgumentException("Kitap nesnesi null olamaz!");
            }
            totalCost += book.getUnitPrice();
        }

        if (balance < totalCost) {
            throw new IllegalStateException("Bu kitapları almak için yeterli bakiyeniz yok.");
        }

        if (borrowedBooks.size() + books.size() > 5) {
            throw new IllegalStateException("En fazla 5 kitap ödünç alabilirsiniz.");
        }

        for(Book book : books) {
            if (!book.isAvailable()) {
                throw new IllegalStateException("Kitap şu anda mevcut değil: " + book.getName());
            }
        }

        for (Book book : books) {
            book.setAvailable(false);
            borrowedBooks.add(book);
        }
        
        balance -= totalCost;

        Invoice invoice = new Invoice(
                books,
                this,
                false,
                totalCost
        );
        getLibrary().getInvoices().add(invoice);
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
            if (!borrowedBooks.contains(book)) {
                System.out.println("Bu kitaba sahip değilsiniz: " + book.getName());
                return;
            }
        }

        double totalRefund = 0.0;
        for (Book book : books) {
            totalRefund += book.getUnitPrice();
        }

        for (Book book : books) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
        }
        
        balance += totalRefund;

        Invoice invoice = new Invoice(
            books,
            this,
            true,
            totalRefund
        );
        getLibrary().getInvoices().add(invoice);
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
