package view;

import controller.AccountController;
import controller.SkillController;
import model.Account;
import model.Skill;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class AccountView {

    AccountController accountController = new AccountController();
    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void createSkill() throws IOException {
        System.out.println("Please enter new account name: ");
        String input = scanner.nextLine();
        accountController.createAccount(input);
        System.out.println("Created successfully");
    }

    public void deleteAccount() throws IOException {
        System.out.println("Please enter account ID: ");
        String input = scanner.nextLine();
        accountController.deleteAccount(Long.valueOf(input));
        System.out.println("Deleted successfully");
    }

    public void findAccount() throws IOException {
        System.out.println("Please enter account ID: ");
        String input = scanner.nextLine();
        accountController.findAccount(Long.valueOf(input));
        System.out.println("Created successfully");
    }

    public void updateSkill() throws IOException {
        System.out.println("Please enter account ID: ");
        String id = scanner.nextLine();
        Account account = accountController.findAccount(Long.valueOf(id));

        System.out.println("Please enter new account name: ");
        String name = scanner.nextLine();
        account.setName(name);
        accountController.updateAccount(account);
        System.out.println("Updated successfully");
    }

    public void findAllAccounts() throws IOException {
        List<Account> listAccounts = null;
        System.out.println("Please enter findAll to find all accounts: ");
        String input = scanner.nextLine();
        if(input.equals("findAll"))
            listAccounts = accountController.findAccounts();
        System.out.println(listAccounts);
    }
}
