package model;

public class MapperOut {
    public String map(Toy toy) {
        return String.format("Toy ID: %s. Toy name: %s", toy.getId(), toy.getName());
    }
}
