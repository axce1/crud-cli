package controller;

import model.Developer;
import repository.DeveloperRepositoryTxtImpl;

import java.io.IOException;
import java.util.List;

public class DeveloperController {

    DeveloperRepositoryTxtImpl repo = new DeveloperRepositoryTxtImpl();

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
