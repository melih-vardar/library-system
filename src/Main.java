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
        Guest guest = new Guest(library);
        
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
        System.out.print("TC Kimlik No: ");
        String tckno = scanner.nextLine();
        
        User user = null;
        for (User u : library.getUsers().values()) {
            if (u.getTckno().equals(tckno)) {
                user = u;
                break;
            }
        }

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
                case 1:
                    System.out.print("Kitap ID: ");
                    int id = scanner.nextInt();
                    Book book = user.findBookById(id);
                    if (book != null) {
                        printBooks(List.of(book));
                    }
                    break;
                case 2:
                    System.out.print("Kitap adı: ");
                    String name = scanner.nextLine();
                    printBooks(user.findBooksByName(name));
                    break;
                case 3:
                    System.out.print("Yazar adı: ");
                    String author = scanner.nextLine();
                    printBooks(user.findBooksByAuthor(author));
                    break;
                case 4:
                    System.out.print("Kategori adı: ");
                    String category = scanner.nextLine();
                    printBooks(user.findBooksByCategory(category));
                    break;
                case 5:
                    System.out.print("Ödünç almak istediğiniz kitabın ID'si: ");
                    int bookId = scanner.nextInt();
                    book = library.getBooks().get(bookId);
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
                    printBooks(new ArrayList<>(user.getBorrowedBooks()));
                    break;
                case 8:
                    System.out.println("Mevcut bakiyeniz: " + user.getBalance() + " TL");
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void handleAdminOperations(Scanner scanner, Library library) {
        System.out.print("TC Kimlik No: ");
        String tckno = scanner.nextLine();
        
        Admin admin = null;
        for (Admin a : library.getAdmins().values()) {
            if (a.getTckno().equals(tckno)) {
                admin = a;
                break;
            }
        }

        if (admin == null) {
            System.out.println("Admin bulunamadı!");
            return;
        }
        
        while (true) {
            System.out.println("\n=== Admin İşlemleri ===");
            System.out.println("1. Yeni kitap ekle");
            System.out.println("2. Kitap güncelle");
            System.out.println("3. Kitap sil");
            System.out.println("4. Yeni kategori ekle");
            System.out.println("5. Tüm kitapları listele");
            System.out.println("6. Tüm kategorileri listele");
            System.out.println("7. Yeni admin ekle");
            System.out.println("8. Admin sil");
            System.out.println("0. Ana menüye dön");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.print("Kitap adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Yazar: ");
                    String author = scanner.nextLine();
                    System.out.print("Kategori adı: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();
                    
                    admin.createBook(name, author, categoryName, price);
                    break;
                case 2:
                    System.out.print("Güncellenecek kitabın ID'si: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Book existingBook = library.getBooks().get(id);
                    if (existingBook != null) {
                        System.out.print("Yeni kitap adı: ");
                        name = scanner.nextLine();
                        if (!name.isEmpty()) existingBook.setName(name);
                        
                        System.out.print("Yeni yazar: ");
                        author = scanner.nextLine();
                        if (!author.isEmpty()) existingBook.setAuthor(author);
                        
                        System.out.print("Yeni fiyat: ");
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
                    admin.createCategory(categoryName);
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
                case 7:
                    System.out.print("TC Kimlik No: ");
                    String newTckno = scanner.nextLine();
                    System.out.print("Ad: ");
                    String newName = scanner.nextLine();
                    System.out.print("Soyad: ");
                    String newSurname = scanner.nextLine();
                    System.out.print("Yaş: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    
                    admin.createAdmin(newTckno, newName, newSurname, newAge);
                    System.out.println(library.getAdmins());
                    break;
                case 8:
                    System.out.println("Silinecek adminin TC Kimlik No'su: ");
                    String tcknoToDelete = scanner.nextLine();
                    Admin adminToDelete = library.getAdmins().get(tcknoToDelete);
                    if (adminToDelete != null) {
                        admin.removeAdmin(adminToDelete);
                    } else {
                        System.out.println("Admin bulunamadı!");
                    }
                    System.out.println(library.getAdmins());
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
        Admin admin = new Admin("99999999999", "Admin", "Admin", 30, library);
        library.getAdmins().put(admin.getId(), admin);
        
        admin.createBook("1984", "George Orwell", "Kurgu", 25.0);
        admin.createBook("Kozmos", "Carl Sagan", "Bilim", 35.0);
        admin.createBook("Nutuk", "Mustafa Kemal Atatürk", "Tarih", 40.0);

        admin.createUser("12345678901", "Ahmet", "Yılmaz", 25, 100.0);

    }

}