public class Book {

    private static int lastId = 0;
    private final int id;
    private String name;
    private String author;
    private Category category;
    private boolean available;
    private double unitPrice;

    Book(Admin creator, String name, String author, Category category, double unitPrice) {
        this.id = ++lastId;
        
        if (creator == null) {
            System.out.println("Kitaplar sadece Admin tarafından oluşturulabilir!");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Kitap adı boş olamaz!");
            return;
        }
        if (category == null) {
            System.out.println("Kategori boş olamaz!");
            return;
        }
        if (unitPrice < 0) {
            System.out.println("Fiyat negatif olamaz!");
            return;
        }

        this.name = name;
        this.author = author;
        this.category = category;
        this.unitPrice = unitPrice;
        this.available = true;
    }


    // public getters/setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
