import java.util.ArrayList;
import java.util.List;

public abstract class Person implements IBorrow {

    private static int lastId = 0;
    private final int id;
    private String tckno;
    private String username;
    private String password;
    private String name;
    private String surname;
    private int age;
    private Library library;

    public Person(String tckno, String username, String password, String name, String surname, int age, Library library) {
        this.id = ++lastId;
        
        if (tckno == null || tckno.length() != 11 || !tckno.matches("\\d+")) {
            throw new IllegalArgumentException("TC Kimlik No 11 haneli rakamlardan oluşmalıdır!");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Kullanıcı adı boş olamaz!");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Şifre en az 6 karakter olmalıdır!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("İsim boş olamaz!");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Soyisim boş olamaz!");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Yaş 0'dan büyük olmalıdır!");
        }
        if (library == null) {
            throw new IllegalArgumentException("Library parametresi null olamaz!");
        }
        
        this.tckno = tckno;
        this.username = username;
        this.password = password;
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

    public Library getLibrary() {
        return library;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    // equals&hashcode
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return ((Person) obj).id == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
               "TC Kimlik No: " + tckno + "\n" +
               "Kullanıcı Adı: " + username + "\n" +
               "Şifre: " + password + "\n" +
               "İsim: " + name + "\n" +
               "Soyisim: " + surname + "\n" +
               "Yaş: " + age + "\n";
    }

}
