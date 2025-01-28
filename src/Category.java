public class Category {

    private static int lastId = 0;
    private final int id;
    private String name;

    // Package-private friend constructor
    Category(Admin creator, String name) {
        this.id = ++lastId;
        
        if (creator == null) {
            System.out.println("Kategoriler sadece Admin tarafından oluşturulabilir!");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Kategori adı boş olamaz!");
            return;
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
