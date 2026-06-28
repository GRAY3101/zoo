package zoo;

public sealed interface Reptile extends Animal permits Snake, Turtle, Lizard {
}

