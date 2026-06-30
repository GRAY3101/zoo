package zoo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Den gleichen Logger holen, den die Zoo-Klasse benutzt
        Logger zooLogger = Logger.getLogger(Zoo.class.getName());

        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                // Erlaube dem Konsolen-Handler, alle Level bis FINE anzuzeigen
                handler.setLevel(Level.FINE);
            }
        }

        Zoo meinZoo = new Zoo();
        Enclosure<Cat> katzenHaus = new Enclosure<>("Raubkatzen-Zuhause");
        katzenHaus.add(new Lion("Leo", true));


        System.out.println("--- LOG-LEVEL: INFO ---");
        zooLogger.setLevel(Level.INFO);

        meinZoo.addEnclosure(katzenHaus); // Loggt INFO (sichtbar) und FINE (unsichtbar)
        meinZoo.findEnclosureByName("NichtExistierend"); // Loggt INFO und WARNING (sichtbar)


        System.out.println("\n--- LOG-LEVEL AUF FINE ---");
        zooLogger.setLevel(Level.FINE);

        meinZoo.getAllAnimals(); // Jetzt sieht man im Terminal INFO und FINE!


        System.out.println("\n--- LOG-LEVEL AUF SEVERE---");
        zooLogger.setLevel(Level.SEVERE);

        meinZoo.findEnclosureByName("AuchNichtDa"); // Bleibt stumm, da WARNING < SEVERE

        try {
            meinZoo.addEnclosure(null); // Das triggert SEVERE (sichtbar)
        } catch (IllegalArgumentException e) {
            // Abgefangen für die Demo
        }
    }
}
