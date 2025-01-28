public class Category {

    private static int lastId = 0;
    private final int id;
    private String name;

    Category(Admin creator, String name) {
        this.id = ++lastId;
        
        if (creator == null) {
            throw new IllegalArgumentException("Kategoriler sadece Admin tarafından oluşturulabilir!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori adı boş olamaz!");
        }

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
