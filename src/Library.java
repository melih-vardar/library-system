import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private Map<Integer, Book> books;
    private List<Category> categories;
    private Map<Integer, User> users;
    private Map<Integer, Admin> admins;
    private List<Invoice> invoices;



    public Library() {
        books = new HashMap<>();
        categories = new ArrayList<>();
        users = new HashMap<>();
        admins = new HashMap<>();
        invoices = new ArrayList<>();
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer, Book> books) {
        this.books = books;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Map<Integer, Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Map<Integer, Admin> admins) {
        this.admins = admins;
    }

    // categoryService, bookservice tarzı sınıflar oluşturularak SOLID prensiplerine uygun yapabiliriz.
}
