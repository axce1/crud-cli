package view;

import controller.AccountController;
import controller.DeveloperController;
import model.Account;
import model.Developer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    DeveloperController developerController = new DeveloperController();
    AccountController accountController = new AccountController();
    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void createSkill() throws IOException {
        System.out.println("Please enter new account nickname: ");
        String input = scanner.nextLine();
        developerController.createDeveloper(input);
        System.out.println("Created successfully");
    }

    public void deleteAccount() throws IOException {
        System.out.println("Please enter developer ID: ");
        String input = scanner.nextLine();
        developerController.deleteDeveloper(Long.valueOf(input));
        System.out.println("Deleted successfully");
    }

    public void findAccount() throws IOException {
        System.out.println("Please enter developer ID: ");
        String input = scanner.nextLine();
        developerController.findDeveloper(Long.valueOf(input));
        System.out.println("Created successfully");
    }

    public void updateSkill() throws IOException {
        System.out.println("Please enter developer ID: ");
        String id = scanner.nextLine();
        Developer developer = developerController.findDeveloper(Long.valueOf(id));

        System.out.println("Please enter new account ID: ");
        String accID = scanner.nextLine();
        Account account = accountController.findAccount(Long.valueOf(accID));
        developer.setAccount(account);
        developerController.updateDeveloper(developer);
        System.out.println("Updated successfully");
    }

    public void findAllAccounts() throws IOException {
        List<Developer> listDevelopers = null;
        System.out.println("Please enter findAll to find all developers: ");
        String input = scanner.nextLine();
        if(input.equals("findAll"))
            listDevelopers = developerController.findDevelopers();
        System.out.println(listDevelopers);
    }
}
