package view;

import controller.SkillController;
import model.Skill;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class SkillView {

    SkillController skillController = new SkillController();
    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void createSkill() throws IOException {
        System.out.println("Please enter new skill name: ");
        String input = scanner.nextLine();
        skillController.createSkill(input);
        System.out.println("Created successfully");
    }

    public void deleteSkill() throws IOException {
        System.out.println("Please enter skill ID: ");
        String input = scanner.nextLine();
        skillController.deleteSkill(Long.valueOf(input));
        System.out.println("Deleted successfully");
    }

    public void findSkill() throws IOException {
        System.out.println("Please enter skill ID: ");
        String input = scanner.nextLine();
        skillController.findSkill(Long.valueOf(input));
        System.out.println("Created successfully");
    }

    public void updateSkill() throws IOException {
        System.out.println("Please enter skill ID: ");
        String id = scanner.nextLine();
        Skill skill = skillController.findSkill(Long.valueOf(id));

        System.out.println("Please enter new skill name: ");
        String name = scanner.nextLine();
        skill.setName(name);
        skillController.updateSkill(skill);
        System.out.println("Updated successfully");
    }

    public void findAllSkills() throws IOException {
        List<Skill> listSkills = null;
        System.out.println("Please enter findAll to find all skills: ");
        String input = scanner.nextLine();
        if(input.equals("findAll"))
            listSkills = skillController.findSkills();
        System.out.println(listSkills);
    }
}
