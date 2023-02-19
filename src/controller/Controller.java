package controller;

import model.Repository;
import model.RepositoryOut;
import model.Toy;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    private final Repository repository;
    private final RepositoryOut repositoryOut;

    public Controller(Repository repository, RepositoryOut repositoryOut) {
        this.repository = repository;
        this.repositoryOut = repositoryOut;
    }

    public void validateToyData(Toy toy) {
        if (toy.getName().isEmpty()) {
            throw new IllegalStateException("Fields are empty!");
        }
    }

    public void saveToy(Toy toy) {
        validateToyData(toy);
        repository.createToy(toy);
    }

    public Toy readToy(String toyId) throws Exception {
        List<Toy> toys = repository.getAllToys();
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }
        throw new Exception("Toy not found!");
    }

    public List<Toy> readToys() {
        return repository.getAllToys();
    }

    public void editToy(Toy toy) {
        validateToyData(toy);
        repository.updateToy(toy);
    }

    public void deleteToy(String toyId) {
        repository.deleteToy(toyId);
    }

    public Integer raffleToy() {
        Integer result = 0;
        List<Toy> toys = repository.getAllToys();
        Integer sumProbability = repository.getSumProbability(toys);
        List<Integer> probabilityRange = new ArrayList<>();

        probabilityRange.add(1);
        Integer position = 1;

        for (Toy toy : toys) {
            position = position + toy.getProbability();
            probabilityRange.add(position);
        }

        Integer random = ThreadLocalRandom.current().nextInt(probabilityRange.stream().min(Integer::compareTo).get(),
                probabilityRange.stream().max(Integer::compareTo).get());

        for (int i = 0; i < toys.size(); i ++) {
            if (random >= probabilityRange.get(i) && random < probabilityRange.get(i + 1)) {
                result = Integer.valueOf(toys.get(i).getId());
                repositoryOut.addToFile(toys.get(i));
                return result;
            }
        }
        return result;
    }

    public void clearOutFile() {
        repositoryOut.clearFile();
    }
}
