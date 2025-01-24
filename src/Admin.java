import java.util.List;

public class Admin extends Person {
    private final Library library;

    public Admin(int id, String name, String surname, int age, Library library) {
        super(id, name, surname, age, library);
        this.library = library;
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

    // Admin için özel arama metodları - Person'dan miras alınanları kullanacak
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
