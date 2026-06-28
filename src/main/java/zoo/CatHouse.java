package zoo;

public class CatHouse<T extends Cat> extends Enclosure<T>  {
    public CatHouse(String name) {
        super(name);
    }
}
