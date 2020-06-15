package view;

import controller.SkillController;
import model.Skill;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class SkillView {

    SkillController skillController = new SkillController();

    public void createSkill() throws IOException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Please enter new skill name: ");
        String input = scanner.nextLine();
        skillController.createSkill(input);
        System.out.println("Created successfully");
    }

    public void findAllSkills() throws IOException {
        List<Skill> listSkills = null;

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Please enter findAll to find all skills: ");
        String input = scanner.nextLine();
        if(input.equals("findAll"))
            listSkills = skillController.findSkills();
        
        System.out.println(listSkills);
    }
}
