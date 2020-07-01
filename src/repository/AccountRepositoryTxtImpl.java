package repository;

import model.Account;
import model.AccountStatus;
import model.Skill;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static utils.IOUtils.getFile;
import static utils.IOUtils.getNextId;

public class AccountRepositoryTxtImpl implements AccountRepository{

    RandomAccessFile file;
    int index;
    private final String fname = "account.txt";

    @Override
    public Account save(Account account) throws IOException {
        boolean isExists = false;
        String numberString;
        String accName;
        long id = 0;

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('~');
            id = Long.parseLong(numberString.substring(0, index));
            accName = numberString.substring(index + 1);

            if (accName.equals(account.getName())) {
                isExists = true;
                break;
            }
        }

        if (!isExists) {
            numberString = getNextId(id) + "~" + account.getName() + "~" + account.getStatus();
            file.writeBytes(numberString);
            file.writeBytes(System.lineSeparator());
            System.out.println(" Account added.");

        } else {
            System.out.println("Already exists");
        }

        isExists = false;
        file.close();
        return account;
    }

    @Override
    public void delete(Long ID) throws IOException {

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            long position = file.getFilePointer();
            String numberString = file.readLine();
            index = numberString.indexOf('~');
            Long id = Long.parseLong(numberString.substring(0, index));

            if (id.equals(ID)) {
                Account account = findById(ID);
                byte[] remainingBytes = new byte[(int) (file.length() - file.getFilePointer())];
                file.read(remainingBytes);
                file.getChannel().truncate(position);
                numberString = id + "~" + account.getName() + "~" + AccountStatus.DELETED;
                file.writeBytes(numberString);
                file.writeBytes(System.lineSeparator());
                file.write(remainingBytes);
                System.out.println("Account deleted.");

            }
        }

        file.close();

    }

    @Override
    public Account findById(Long ID) throws IOException {
        Account account = null;

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            String numberString = file.readLine();
            index = numberString.indexOf('~');
            Long id = Long.parseLong(numberString.substring(0, index));
            String skillName = numberString.substring(index + 1);
            String status = numberString.substring(index+2);

            if (id.equals(ID)) {
                account = new Account(id, skillName, AccountStatus.valueOf(status));
                break;
            }
        }
        // TODO как обработать null ?
        return account;
    }

    @Override
    public Account update(Account account) throws IOException {

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            long position = file.getFilePointer();
            String numberString = file.readLine();
            index = numberString.indexOf('~');
            Long id = Long.parseLong(numberString.substring(0, index));

            if (id.equals(account.getId())) {
                byte[] remainingBytes = new byte[(int) (file.length() - file.getFilePointer())];
                file.read(remainingBytes);
                file.getChannel().truncate(position);
                numberString = id + "~" + account.getName() + "~" + account.getStatus();
                file.writeBytes(numberString);
                file.writeBytes(System.lineSeparator());
                file.write(remainingBytes);
                System.out.println("Account updated.");
                break;
            }
        }

        file.close();
        return account;
    }

    @Override
    public List<Account> findAll() throws IOException {
        String numberString;
        String name;
        String status;
        List<Account> listAccount = new ArrayList<>();

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('~');

            Long id = Long.parseLong(numberString.substring(0, index));
            name = numberString.substring(index + 1);
            status = numberString.substring(index + 2);
            listAccount.add(new Account(id, name, AccountStatus.valueOf(status)));
        }
        return listAccount;
    }
}
