package zoo;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Zoo {

    private static final Logger logger = Logger.getLogger(Zoo.class.getName());

    private final List<Enclosure<?>> enclosures = new ArrayList<>();

    public void addEnclosure(Enclosure<?> enclosure) {
        // INFO: Start der Methode mit Parametern
        logger.info("Methode addEnclosure aufgerufen. Parameter enclosure name: " +
                (enclosure != null ? enclosure.getName() : "null"));

        // SEVERE: Schwerwiegende Inkonsistenz prüfen
        if (enclosure == null) {
            logger.severe("Versuch, ein null-Gehege zum Zoo hinzuzufügen!");
            throw new IllegalArgumentException("Enclosure cannot be null");
        }

        enclosures.add(enclosure);

        // FINE: Zustandszusammenfassung nach Erfolg
        logger.fine("Gehege erfolgreich hinzugefügt. Aktuelle Gehegeanzahl: " + enclosures.size());
    }

    public List<Enclosure<?>> getEnclosures() {
        logger.info("Methode getEnclosures aufgerufen.");
        List<Enclosure<?>> list = new ArrayList<>(enclosures);
        logger.fine("getEnclosures erfolgreich ausgeführt. Rückgabegröße: " + list.size());
        return list;
    }

    public Enclosure<?> findEnclosureByName(String name) {
        logger.info("Methode findEnclosureByName aufgerufen. Parameter name: " + name);

        for (Enclosure<?> enclosure : enclosures) {
            if (enclosure.getName().equals(name)) {
                logger.fine("Gehege '" + name + "' erfolgreich gefunden.");
                return enclosure;
            }
        }

        // WARNING: Angefordertes Gehege wurde nicht gefunden
        logger.warning("Gehege mit dem Namen '" + name + "' wurde im Zoo nicht gefunden!");
        return null;
    }

    public List<Animal> getAllAnimals() {
        logger.info("Methode getAllAnimals aufgerufen.");
        List<Animal> animals = enclosures.stream()
                .flatMap(enclosure -> enclosure.getInhabitants().stream())
                .collect(Collectors.toList());

        logger.fine("getAllAnimals erfolgreich ausgeführt. Gesamtzahl Tiere im Zoo: " + animals.size());
        return animals;
    }

    public List<Mammal> getAllMammals() {
        logger.info("Methode getAllMammals aufgerufen.");
        List<Mammal> mammals = getAllAnimals().stream()
                .filter(animal -> animal instanceof Mammal)
                .map(animal -> (Mammal) animal)
                .collect(Collectors.toList());

        logger.fine("getAllMammals erfolgreich ausgeführt. Anzahl Säugetiere: " + mammals.size());
        return mammals;
    }

    public List<Animal> getAnimalsByPredicate(Predicate<Animal> predicate) {
        logger.info("Methode getAnimalsByPredicate aufgerufen.");
        List<Animal> filtered = getAllAnimals().stream()
                .filter(predicate)
                .collect(Collectors.toList());

        logger.fine("getAnimalsByPredicate erfolgreich ausgeführt. Gefundene Treffer: " + filtered.size());
        return filtered;
    }

    public Map<Class<? extends Animal>, Long> countAnimalsByType() {
        logger.info("Methode countAnimalsByType aufgerufen.");
        Map<Class<? extends Animal>, Long> counts = getAllAnimals().stream()
                .collect(Collectors.groupingBy(Animal::getClass, Collectors.counting()));

        logger.fine("countAnimalsByType erfolgreich ausgeführt. Anzahl unterschiedlicher Tiertypen: " + counts.size());
        return counts;
    }

    public List<Enclosure<?>> getOvercrowdedEnclosures(int maxInhabitants) {
        logger.info("Methode getOvercrowdedEnclosures aufgerufen. Parameter maxInhabitants: " + maxInhabitants);
        List<Enclosure<?>> overcrowded = enclosures.stream()
                .filter(enclosure -> enclosure.getInhabitants().size() > maxInhabitants)
                .collect(Collectors.toList());

        logger.fine("getOvercrowdedEnclosures erfolgreich ausgeführt. Überfüllte Gehege gefunden: " + overcrowded.size());
        return overcrowded;
    }

    public String summary() {
        logger.info("Methode summary aufgerufen.");
        List<Animal> allAnimals = getAllAnimals();

        long mammalsCount = allAnimals.stream().filter(a -> a instanceof Mammal).count();
        long birdsCount = allAnimals.stream().filter(a -> a instanceof Bird).count();
        long fishCount = allAnimals.stream().filter(a -> a instanceof Fish).count();
        long reptilesCount = allAnimals.stream().filter(a -> a instanceof Reptile).count();

        String result = String.format("Zoo mit %d Gehegen und %d Tieren: %d Mammals, %d Birds, %d Fish, %d Reptiles",
                enclosures.size(), allAnimals.size(), mammalsCount, birdsCount, fishCount, reptilesCount);

        logger.fine("summary erfolgreich generiert.");
        return result;
    }

    public Optional<Animal> findAnimalByName(String animalName) {
        Animal correctAnimal = enclosures.stream()
                .map(enclosure -> enclosure.findAnimalByName(animalName))
                .flatMap(Optional::stream)
                .map(animal -> (Animal) animal)
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(correctAnimal);
    }



}
