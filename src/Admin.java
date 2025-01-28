import java.util.List;

public class Admin extends Person {
    public Admin(String tckno, String name, String surname, int age, Library library) {
        super(tckno, name, surname, age, library);
    }

    public void addCategory(Category category) {
        if (library.getCategories().contains(category)) {
            System.out.println("Category already exists");
        } else {
            library.getCategories().add(category);
            System.out.println("Category added to library successfully. Category name: " + category.getName());
        }
    }

    public void removeCategory(Category category) {
        if(library.getCategories().contains(category)) {
            library.getCategories().remove(category);
        } else {
            System.out.println("Category does not exist");
        }
    }

    public void addBook(Book book) {
        if(library.getBooks().containsKey(book.getId())) {
            System.out.println("Book already exists");
        } else {
            library.getBooks().put(book.getId(), book);
            System.out.println("Book added to library successfully. Book name: " + book.getName());
        }
    }

    public void removeBook(Book book) {
        if(library.getBooks().containsKey(book.getId())) {
            library.getBooks().remove(book.getId());
        } else {
            System.out.println("Book does not exist");
        }
    }

    public void updateBook(Book book) {
        if(library.getBooks().containsKey(book.getId())) {
            library.getBooks().put(book.getId(), book);
            System.out.println("Book updated successfully");
        } else {
            System.out.println("Book not found");
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
        // TC no kontrolü
        for (User existingUser : library.getUsers().values()) {
            if (existingUser.getTckno().equals(tckno)) {
                System.out.println("Bu TC Kimlik No'ya sahip bir kullanıcı zaten mevcut!");
                return null;
            }
        }

        User user = new User(this, tckno, name, surname, age, initialBalance, library);
        addUser(user);
        return user;
    }

    public void addUser(User user) {
        for (User existingUser : library.getUsers().values()) {
            if (existingUser.getTckno().equals(user.getTckno())) {
                System.out.println("Bu TC Kimlik No'ya sahip bir kullanıcı zaten mevcut!");
                return;
            }
        }

        if (library.getUsers().containsKey(user.getId())) {
            System.out.println("User already exists");
        } else {
            library.getUsers().put(user.getId(), user);
            System.out.println("User added to library successfully. User name: " + user.getName());
        }
    }

    public void removeUser(User user) {
        if (library.getUsers().containsKey(user.getId())) {
            library.getUsers().remove(user.getId());
            System.out.println("User removed successfully");
        } else {
            System.out.println("User does not exist");
        }
    }

    public void updateUser(User user) {
        if (library.getUsers().containsKey(user.getId())) {
            library.getUsers().put(user.getId(), user);
            System.out.println("User updated successfully");
        } else {
            System.out.println("User not found");
        }
    }

    public Book createBook(String name, String author, String categoryName, double unitPrice) {
        Category category = null;
        for (Category existingCategory : library.getCategories()) {
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
        // TC no kontrolü
        for (Admin existingAdmin : library.getAdmins().values()) {
            if (existingAdmin.getTckno().equals(tckno)) {
                System.out.println("Bu TC Kimlik No'ya sahip bir admin zaten mevcut!");
                return null;
            }
        }

        Admin newAdmin = new Admin(tckno, name, surname, age, library);
        addAdmin(newAdmin);
        return newAdmin;
    }

    private void addAdmin(Admin admin) {
        if (library.getAdmins().containsKey(admin.getId())) {
            System.out.println("Admin already exists");
        } else {
            library.getAdmins().put(admin.getId(), admin);
            System.out.println("Admin added successfully. Admin name: " + admin.getName());
        }
    }

    public void removeAdmin(Admin admin) {
        if (library.getAdmins().size() <= 1) {
            System.out.println("Son admin silinemez!");
            return;
        }
        
        if (library.getAdmins().containsKey(admin.getId())) {
            library.getAdmins().remove(admin.getId());
            System.out.println("Admin removed successfully");
        } else {
            System.out.println("Admin does not exist");
        }
    }
}
