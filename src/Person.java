import java.util.ArrayList;
import java.util.List;

public abstract class Person implements IBorrow {

    private static int lastId = 0;
    private final int id;
    private final String tckno;
    private String name;
    private String surname;
    private int age;
    protected Library library;  // library'i protected yapıyoruz ki alt sınıflar erişebilsin

    public Person(String tckno, String name, String surname, int age, Library library) {
        if (tckno == null || tckno.length() != 11 || !tckno.matches("\\d+")) {
            throw new IllegalArgumentException("TC Kimlik No 11 haneli rakamlardan oluşmalıdır!");
        }
        this.id = ++lastId;
        this.tckno = tckno;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public String getTckno() {
        return tckno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    // Arama metodları
    public Book findBookById(int id) {
        if(library.getBooks().containsKey(id)) {
            return library.getBooks().get(id);
        }
        System.out.println("Kitap bulunamadı!");
        return null;
    }

    public List<Book> findBooksByName(String name) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : library.getBooks().values()) {
            if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : library.getBooks().values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findBooksByCategory(String categoryName) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : library.getBooks().values()) {
            if (book.getCategory().getName().toLowerCase().contains(categoryName.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // IBorrow interface'inin default implementasyonları
    @Override
    public void borrowBook(Book book) {
        System.out.println("Bu işlemi gerçekleştirmek için kütüphaneye üye olmalısınız.");
    }

    @Override
    public void borrowBooks(List<Book> books) {
        System.out.println("Bu işlemi gerçekleştirmek için kütüphaneye üye olmalısınız.");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Bu işlemi gerçekleştirmek için kütüphaneye üye olmalısınız.");
    }

    @Override
    public void returnBooks(List<Book> books) {
        System.out.println("Bu işlemi gerçekleştirmek için kütüphaneye üye olmalısınız.");
    }
}
