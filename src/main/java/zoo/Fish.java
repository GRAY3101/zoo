package zoo;

public sealed interface Fish extends Animal permits Trout, Shark, Salmon {
}
