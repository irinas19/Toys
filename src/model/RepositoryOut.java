package model;


import java.util.ArrayList;
import java.util.List;

public class RepositoryOut {
    private MapperOut mapperOut = new MapperOut();
    private FileOperation fileOperation;

    public RepositoryOut(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public void addToFile(Toy toy) {
        List<String> lines = fileOperation.readAllLines();
        lines.add(mapperOut.map(toy));
        fileOperation.saveAllLines(lines);
    }

    public void clearFile() {
        fileOperation.saveAllLines(new ArrayList<>());
    }
}
