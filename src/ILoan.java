import java.util.List;

public interface ILoan {

    void loanBook(Book book);
    void loanBooks(List<Book> books);
    void returnBook(Book book);
    void returnBooks(List<Book> books);
}
