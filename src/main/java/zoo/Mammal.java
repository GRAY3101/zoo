package zoo;

public sealed interface Mammal extends Animal permits Primate, Rodent, Cat{
}
