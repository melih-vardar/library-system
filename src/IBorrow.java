import java.util.List;

public interface IBorrow {

    void borrowBook(Book book);
    void borrowBooks(List<Book> books);
    void returnBook(Book book);
    void returnBooks(List<Book> books);

} 