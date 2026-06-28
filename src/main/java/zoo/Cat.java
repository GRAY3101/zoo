package zoo;

public sealed interface Cat extends Mammal permits Lion, Tiger {
}

