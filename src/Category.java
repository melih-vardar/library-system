public class Category {

    private static int lastId = 0;
    private final int id;
    private String name;

    // Package-private friend constructor
    Category(Admin creator, String name) {
        if (creator == null) {
            throw new IllegalArgumentException("Categories can only be created by Admin!");
        }
        this.id = ++lastId;
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
