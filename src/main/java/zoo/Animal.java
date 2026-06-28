package zoo;

public sealed interface Animal permits Mammal, Fish, Reptile, Bird {
    String name();
}
