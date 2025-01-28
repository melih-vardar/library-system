import java.util.List;

public class Admin extends Person {
    public Admin(String tckno, String username, String password, String name, String surname, int age, Library library) {
        super(tckno, username, password, name, surname, age, library);
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

    public Admin createAdmin(String tckno, String username, String password, String name, String surname, int age) {
        try {
            for (Admin existingAdmin : getLibrary().getAdmins().values()) {
                if (existingAdmin.getUsername().equals(username)) {
                    throw new IllegalStateException("Bu kullanıcı adı zaten kullanılıyor!");
                }
            }

            Admin newAdmin = new Admin(tckno, username, password, name, surname, age, getLibrary());
            addAdmin(newAdmin);
            return newAdmin;
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return null;
        }
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
