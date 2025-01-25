import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Invoice {
    private static int lastId = 0;
    private final int id;
    private double amount;
    private List<Book> books;
    private User user;
    private LocalDateTime date;
    private boolean isReturn; // iade faturası mı yoksa ödünç alma faturası mı

    public Invoice(List<Book> books, User user, boolean isReturn, double amount) {
        this.id = ++lastId;
        this.books = new ArrayList<>(books);
        this.user = user;
        this.date = LocalDateTime.now();
        this.isReturn = isReturn;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public List<Book> getBooks() {
        return books;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isReturn() {
        return isReturn;
    }

    @Override
    public String toString() {
        String type = isReturn ? "İade" : "Kiralama";
        StringBuilder booksStr = new StringBuilder();
        for (Book book : books) {
            booksStr.append("\n- ").append(book.getName());
        }
        
        return "Fatura No: " + id + 
               "\nİşlem: " + type +
               "\nTarih: " + date +
               "\nKullanıcı: " + user.getName() +
               "\nKitaplar: " + booksStr +
               "\nToplam Tutar: " + amount + " TL";
    }
}
