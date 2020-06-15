package controller;

import model.Account;
import model.AccountStatus;
import model.Skill;
import repository.AccountRepositoryImpl;
import repository.SkillRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class AccountController {

    AccountRepositoryImpl repo = new AccountRepositoryImpl();

    public Account createAccount(String name) throws IOException {
        Account account = new Account(name, AccountStatus.ACTIVE);
        return repo.save(account);
    }

    public void deleteAccount(Long id) throws IOException {
        repo.delete(id);
    }

    public void updateAccount(Account account) throws IOException {
        repo.update(account);
    }

    public Account findAccount(Long id) throws IOException {
        return repo.findById(id);
    }

    public List<Account> findAccounts() throws IOException {
        return repo.findAll();
    }
}
