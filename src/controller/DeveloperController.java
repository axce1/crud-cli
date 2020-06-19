package controller;

import model.Developer;
import model.AccountStatus;
import repository.DeveloperRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class DeveloperController {

    DeveloperRepositoryImpl repo = new DeveloperRepositoryImpl();

    public Developer createDeveloper(String nickName) throws IOException {
        Developer developer = new Developer(nickName);
        return repo.save(developer);
    }

    public void deleteDeveloper(Long id) throws IOException {
        repo.delete(id);
    }

    public void updateDeveloper(Developer developer) throws IOException {
        repo.update(developer);
    }

    public Developer findDeveloper(Long id) throws IOException {
        return repo.findById(id);
    }

    public List<Developer> findDevelopers() throws IOException {
        return repo.findAll();
    }
}
