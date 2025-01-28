import java.util.List;

public class Admin extends Person {
    public Admin(String tckno, String name, String surname, int age, Library library) {
        super(tckno, name, surname, age, library);
    }

    public void addCategory(Category category) {
        if (getLibrary().getCategories().contains(category)) {
            System.out.println("Bu kategori zaten mevcut");
        } else {
            getLibrary().getCategories().add(category);
            System.out.println("Kategori kütüphaneye başarıyla eklendi. Kategori adı: " + category.getName());
        }
    }

    public void removeCategory(Category category) {
        if(getLibrary().getCategories().contains(category)) {
            getLibrary().getCategories().remove(category);
        } else {
            System.out.println("Böyle bir kategori bulunamadı");
        }
    }

    public void addBook(Book book) {
        if(getLibrary().getBooks().containsKey(book.getId())) {
            System.out.println("Kitap zaten mevcut");
        } else {
            getLibrary().getBooks().put(book.getId(), book);
            System.out.println("Kitap kütüphaneye başarıyla eklendi. Kitap adı: " + book.getName());
        }
    }

    public void removeBook(Book book) {
        if(getLibrary().getBooks().containsKey(book.getId())) {
            getLibrary().getBooks().remove(book.getId());
        } else {
            System.out.println("Kitap bulunamadı");
        }
    }

    public void updateBook(Book book) {
        if(getLibrary().getBooks().containsKey(book.getId())) {
            getLibrary().getBooks().put(book.getId(), book);
            System.out.println("Kitap başarıyla güncellendi");
        } else {
            System.out.println("Kitap bulunamadı");
        }
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

    public User createUser(String tckno, String name, String surname, int age, double initialBalance) {
        for (User existingUser : getLibrary().getUsers().values()) {
            if (existingUser.getTckno().equals(tckno)) {
                System.out.println("Bu TC Kimlik No'ya sahip bir kullanıcı zaten mevcut!");
                return null;
            }
        }

        User user = new User(this, tckno, name, surname, age, initialBalance, getLibrary());
        addUser(user);
        return user;
    }

    public void addUser(User user) {

        if (getLibrary().getUsers().containsKey(user.getId())) {
            System.out.println("Kullanıcı zaten mevcut");
        } else {
            getLibrary().getUsers().put(user.getId(), user);
            System.out.println("Kullanıcı kütüphaneye başarıyla eklendi. Kullanıcı adı: " + user.getName());
        }
    }

    public void removeUser(User user) {
        if (getLibrary().getUsers().containsKey(user.getId())) {
            getLibrary().getUsers().remove(user.getId());
            System.out.println("Kullanıcı başarıyla silindi");
        } else {
            System.out.println("Kullanıcı bulunamadı");
        }
    }

    public void updateUser(User user) {
        if (getLibrary().getUsers().containsKey(user.getId())) {
            getLibrary().getUsers().put(user.getId(), user);
            System.out.println("Kullanıcı başarıyla güncellendi");
        } else {
            System.out.println("Kullanıcı bulunamadı");
        }
    }

    public Book createBook(String name, String author, String categoryName, double unitPrice) {
        Category category = null;
        for (Category existingCategory : getLibrary().getCategories()) {
            if (existingCategory.getName().equalsIgnoreCase(categoryName)) {
                category = existingCategory;
                break;
            }
        }
        
        if (category == null) {
            category = createCategory(categoryName);
        }

        Book book = new Book(this, name, author, category, unitPrice);
        addBook(book);
        return book;
    }

    public Category createCategory(String name) {
        Category category = new Category(this, name);
        addCategory(category);
        return category;
    }

    public Admin createAdmin(String tckno, String name, String surname, int age) {
        for (Admin existingAdmin : getLibrary().getAdmins().values()) {
            if (existingAdmin.getTckno().equals(tckno)) {
                System.out.println("Bu TC Kimlik No'ya sahip bir admin zaten mevcut!");
                return null;
            }
        }

        Admin newAdmin = new Admin(tckno, name, surname, age, getLibrary());
        addAdmin(newAdmin);
        return newAdmin;
    }

    private void addAdmin(Admin admin) {
        if (getLibrary().getAdmins().containsKey(admin.getId())) {
            System.out.println("Admin already exists");
        } else {
            getLibrary().getAdmins().put(admin.getId(), admin);
            System.out.println("Admin başarıyla eklendi. Admin adı: " + admin.getName());
        }
    }

    public void removeAdmin(Admin admin) {
        if (getLibrary().getAdmins().size() <= 1) {
            System.out.println("Son admin silinemez!");
            return;
        }
        
        if (getLibrary().getAdmins().containsKey(admin.getId())) {
            getLibrary().getAdmins().remove(admin.getId());
            System.out.println("Admin başarıyla silindi");
        } else {
            System.out.println("Böyle bir admin bulunamadı");
        }
    }
}
