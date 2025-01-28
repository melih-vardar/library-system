public class Guest extends Person {
    public Guest(Library library) {
        super("00000000000", "guest", "guest123", "Misafir", "misafir", 25, library);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}