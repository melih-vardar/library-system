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
            throw new IllegalArgumentException("Kitaplar sadece Admin tarafından oluşturulabilir!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Kitap adı boş olamaz!");
        }
        if (category == null) {
            throw new IllegalArgumentException("Kategori boş olamaz!");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Fiyat negatif olamaz!");
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


// hashcode ezmek gerekiyor kitap eşitliği id üzerinden yapılacak
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            return ((Book) obj).id == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Kitap Adı: " + name +
               "\nYazar: " + author +
               "\nKategori: " + category.getName() +
               "\nFiyat: " + unitPrice + " TL";
    }
}
