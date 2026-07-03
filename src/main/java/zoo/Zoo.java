package zoo;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Zoo {

    private final List<Enclosure<?>> enclosures = new ArrayList<>();

    public void addEnclosure(Enclosure<?> enclosure) {
        enclosures.add(enclosure);
    }

    public List<Enclosure<?>> getEnclosures() {
        return new ArrayList<>(enclosures);
    }

    public Enclosure<?> findEnclosureByName(String name) {
        for (Enclosure<?> enclosure : enclosures) {
            if (enclosure.getName().equals(name)) {
                return enclosure;
            }
        }
        return null;
    }

    public List<Animal> getAllAnimals() {
        return enclosures.stream()
                .flatMap(enclosure -> enclosure.getInhabitants().stream())
                .collect(Collectors.toList());
    }

    public List<Mammal> getAllMammals() {
        return getAllAnimals().stream()
                .filter(animal -> animal instanceof Mammal)
                .map(animal -> (Mammal) animal)
                .collect(Collectors.toList());
    }

    public List<Animal> getAnimalsByPredicate(Predicate<Animal> predicate) {
        return getAllAnimals().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Map<Class<? extends Animal>, Long> countAnimalsByType() {
        return getAllAnimals().stream()
                .collect(Collectors.groupingBy(Animal::getClass, Collectors.counting()));
    }

    public List<Enclosure<?>> getOvercrowdedEnclosures(int maxInhabitants) {
        return enclosures.stream()
                .filter(enclosure -> enclosure.getInhabitants().size() > maxInhabitants)
                .collect(Collectors.toList());
    }

    public String summary() {
        List<Animal> allAnimals = getAllAnimals();

        long mammalsCount = allAnimals.stream().filter(a -> a instanceof Mammal).count();
        long birdsCount = allAnimals.stream().filter(a -> a instanceof Bird).count();
        long fishCount = allAnimals.stream().filter(a -> a instanceof Fish).count();
        long reptilesCount = allAnimals.stream().filter(a -> a instanceof Reptile).count();

        return String.format("Zoo mit %d Gehegen und %d Tieren: %d Mammals, %d Birds, %d Fish, %d Reptiles",
                enclosures.size(), allAnimals.size(), mammalsCount, birdsCount, fishCount, reptilesCount);
    }

    public Optional<Animal> findAnimalByName(String animalName) {
        return enclosures.stream()
                .map(enclosure -> enclosure.findAnimalByName(animalName))
                .flatMap(Optional::stream)
                .map(animal -> (Animal) animal)
                .findFirst();
    }


}
