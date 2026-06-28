package zoo;

public sealed interface Bird extends Animal permits Eagle, Owl, Penguin {
}

