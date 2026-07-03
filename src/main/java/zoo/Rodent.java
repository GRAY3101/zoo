package zoo;

public sealed interface Rodent extends Mammal permits Beaver, Squirrel{
}

