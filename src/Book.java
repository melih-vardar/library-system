public class Book {

    private int id;
    private String name;
    private String author;
    private Category category;
    private boolean available;
    private double unitPrice;

    public Book(int id, String name, String author, Category category, double unitPrice) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.unitPrice = unitPrice;
        available = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
