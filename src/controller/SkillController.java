package controller;

import model.Skill;
import repository.SkillRepositoryTxtImpl;

import java.io.IOException;
import java.util.List;

public class SkillController {

    SkillRepositoryTxtImpl repo = new SkillRepositoryTxtImpl();

    public Skill createSkill(String name) throws IOException {
        Skill skill = new Skill(name);
        return repo.save(skill);
    }

    public void deleteSkill(Long id) throws IOException {
        repo.delete(id);
    }

    public void updateSkill(Skill skill) throws IOException {
        repo.update(skill);
    }

    public Skill findSkill(Long id) throws IOException {
        return repo.findById(id);
    }

    public List<Skill> findSkills() throws IOException {
        return repo.findAll();
    }
}
