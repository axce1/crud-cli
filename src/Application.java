import model.Skill;
import repository.SkillRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        Skill skill1 = new Skill("maven");
        Skill skill2 = new Skill("java");
        Skill skill3 = new Skill("spring");
        Skill skill4 = new Skill("springboot");

        SkillRepositoryImpl repo = new SkillRepositoryImpl();

        repo.save(skill1);
        Skill q = repo.findById(1L);
        System.out.println(q.toString());

        repo.save(skill2);
        repo.save(skill3);

        List<Skill> findAll = repo.findAll();
        Skill first = findAll.get(0);
        System.out.println(findAll.toString());

        Skill findOne = repo.findById(3L);
        System.out.println(findOne.toString());

        repo.delete(3L);

        repo.save(skill4);


        Skill skill5 = new Skill(2L, "jakarta");
        repo.update(skill5);
    }
}
