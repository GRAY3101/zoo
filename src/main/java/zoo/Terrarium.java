package zoo;

public class Terrarium<T extends Reptile> extends Enclosure<T> {
    public Terrarium(String name) {
        super(name);
    }
}
