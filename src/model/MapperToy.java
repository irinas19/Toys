package model;

public class MapperToy {
    public String map(Toy toy) {
        return String.format("%s;%s;%d", toy.getId(), toy.getName(), toy.getProbability());
    }

    public Toy map(String line) {
        String[] lines = line.split(";");
        try {
            Toy toy =  new Toy(lines[0], lines[1], Byte.valueOf(lines[2]));
            return toy;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of data in toys.scv file!");
        }
        return null;
    }
}
