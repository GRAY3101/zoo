package zoo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Enclosure<T extends Animal> {
    private final String name;
    public HashSet<T> inhabitants = new HashSet<>();

    public Enclosure(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean add(T animal){
        return inhabitants.add(animal);
    }

    public boolean remove(T animal){
        return inhabitants.remove(animal);
    }

    public List<T> getInhabitants(){
        return new ArrayList<>(inhabitants);
    }

}
