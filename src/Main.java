import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        initializeLibrary(library);

        while (true) {
            System.out.println("\n=== Kütüphane Sistemine Hoş Geldiniz ===");
            System.out.println("1. Misafir olarak devam et");
            System.out.println("2. Üye olarak devam et");
            System.out.println("3. Admin olarak devam et");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    handleGuestOperations(scanner, library);
                    break;
                case 2:
                    handleUserOperations(scanner, library);
                    break;
                case 3:
                    handleAdminOperations(scanner, library);
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
        scanner.close();
    }

    private static void handleGuestOperations(Scanner scanner, Library library) {
        Person guest = new Person(0, "Misafir", "", 0, library) {};
        while (true) {
            System.out.println("\n=== Misafir İşlemleri ===");
            System.out.println("1. ID ile kitap ara");
            System.out.println("2. İsim ile kitap ara");
            System.out.println("3. Yazar ile kitap ara");
            System.out.println("4. Kategori ile kitap ara");
            System.out.println("0. Ana menüye dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.print("Kitap ID: ");
                    int id = scanner.nextInt();
                    Book book = guest.findBookById(id);
                    if (book != null) {
                        printBooks(List.of(book));
                    }
                    break;
                case 2:
                    System.out.print("Kitap adı: ");
                    String name = scanner.nextLine();
                    printBooks(guest.findBooksByName(name));
                    break;
                case 3:
                    System.out.print("Yazar adı: ");
                    String author = scanner.nextLine();
                    printBooks(guest.findBooksByAuthor(author));
                    break;
                case 4:
                    System.out.print("Kategori adı: ");
                    String category = scanner.nextLine();
                    printBooks(guest.findBooksByCategory(category));
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void handleUserOperations(Scanner scanner, Library library) {
        System.out.print("Kullanıcı ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = library.getUsers().get(userId);
        if (user == null) {
            System.out.println("Kullanıcı bulunamadı!");
            return;
        }

        while (true) {
            System.out.println("\n=== Kullanıcı İşlemleri ===");
            System.out.println("1. ID ile kitap ara");
            System.out.println("2. İsim ile kitap ara");
            System.out.println("3. Yazar ile kitap ara");
            System.out.println("4. Kategori ile kitap ara");
            System.out.println("5. Kitap ödünç al");
            System.out.println("6. Kitap iade et");
            System.out.println("7. Ödünç aldığım kitapları listele");
            System.out.println("8. Bakiyemi göster");
            System.out.println("0. Ana menüye dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 5:
                    System.out.print("Ödünç almak istediğiniz kitabın ID'si: ");
                    int bookId = scanner.nextInt();
                    Book book = library.getBooks().get(bookId);
                    if (book != null) {
                        user.borrowBook(book);
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 6:
                    System.out.print("İade etmek istediğiniz kitabın ID'si: ");
                    bookId = scanner.nextInt();
                    book = library.getBooks().get(bookId);
                    if (book != null) {
                        user.returnBook(book);
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 7:
                    System.out.println("\nÖdünç Aldığınız Kitaplar:");
                    printBooks(new ArrayList<>(user.getLoanedBooks()));
                    break;
                case 8:
                    System.out.println("Mevcut bakiyeniz: " + user.getBalance() + " TL");
                    break;
                default:
                    handleGuestOperations(scanner, library);
            }
        }
    }

    private static void handleAdminOperations(Scanner scanner, Library library) {
        Admin admin = new Admin(0, "Admin", "", 0, library);
        
        while (true) {
            System.out.println("\n=== Admin İşlemleri ===");
            System.out.println("1. Yeni kitap ekle");
            System.out.println("2. Kitap güncelle");
            System.out.println("3. Kitap sil");
            System.out.println("4. Yeni kategori ekle");
            System.out.println("5. Tüm kitapları listele");
            System.out.println("6. Tüm kategorileri listele");
            System.out.println("0. Ana menüye dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.print("Kitap ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Kitap adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Yazar: ");
                    String author = scanner.nextLine();
                    System.out.print("Kategori adı: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();
                    
                    Category category = new Category(categoryName);
                    Book newBook = new Book(id, name, author, category, price);
                    admin.addBook(newBook);
                    break;
                case 2:
                    System.out.print("Güncellenecek kitabın ID'si: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Book existingBook = library.getBooks().get(id);
                    if (existingBook != null) {
                        System.out.print("Yeni kitap adı (Enter ile geç): ");
                        name = scanner.nextLine();
                        if (!name.isEmpty()) existingBook.setName(name);
                        
                        System.out.print("Yeni yazar (Enter ile geç): ");
                        author = scanner.nextLine();
                        if (!author.isEmpty()) existingBook.setAuthor(author);
                        
                        System.out.print("Yeni fiyat (0 ile geç): ");
                        price = scanner.nextDouble();
                        if (price > 0) existingBook.setUnitPrice(price);
                        
                        admin.updateBook(existingBook);
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 3:
                    System.out.print("Silinecek kitabın ID'si: ");
                    id = scanner.nextInt();
                    Book bookToDelete = library.getBooks().get(id);
                    if (bookToDelete != null) {
                        admin.removeBook(bookToDelete);
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 4:
                    System.out.print("Kategori adı: ");
                    categoryName = scanner.nextLine();
                    admin.addCategory(new Category(categoryName));
                    break;
                case 5:
                    System.out.println("\nTüm Kitaplar:");
                    printBooks(new ArrayList<>(library.getBooks().values()));
                    break;
                case 6:
                    System.out.println("\nTüm Kategoriler:");
                    for (Category cat : library.getCategories()) {
                        System.out.println("- " + cat.getName());
                    }
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Kitap bulunamadı!");
            return;
        }
        System.out.println("\nBulunan Kitaplar:");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + 
                             ", Ad: " + book.getName() + 
                             ", Yazar: " + book.getAuthor() + 
                             ", Kategori: " + book.getCategory().getName() + 
                             ", Fiyat: " + book.getUnitPrice() + " TL" +
                             ", Durum: " + (book.isAvailable() ? "Mevcut" : "Ödünç alındı"));
        }
    }

    private static void initializeLibrary(Library library) {
        Category fiction = new Category("Kurgu");
        Category science = new Category("Bilim");
        Category history = new Category("Tarih");
        
        library.getCategories().add(fiction);
        library.getCategories().add(science);
        library.getCategories().add(history);

        Book book1 = new Book(1, "1984", "George Orwell", fiction, 25.0);
        Book book2 = new Book(2, "Kozmos", "Carl Sagan", science, 35.0);
        Book book3 = new Book(3, "Nutuk", "Mustafa Kemal Atatürk", history, 40.0);
        
        library.getBooks().put(book1.getId(), book1);
        library.getBooks().put(book2.getId(), book2);
        library.getBooks().put(book3.getId(), book3);

        // Örnek kullanıcı
        User user1 = new User(1, "Ahmet", "Yılmaz", 25, 100.0, library);
        library.getUsers().put(user1.getId(), user1);
    }
}