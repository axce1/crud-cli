package repository;

import controller.AccountController;
import controller.SkillController;
import model.Account;
import model.AccountStatus;
import model.Developer;
import model.Skill;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.IOUtils.getFile;
import static utils.IOUtils.getNextId;

public class DeveloperRepositoryTxtImpl implements DeveloperRepository {

    RandomAccessFile file;
    int index;
    private final String fname = "developer.txt";
    AccountController accountController = new AccountController();
    SkillController skillController = new SkillController();


    @Override
    public Developer save(Developer developer) throws IOException {
        boolean isExists = false;
        String numberString;
        String accName;
        long id = 0;

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('~');
            id = Long.parseLong(numberString.substring(0, index));
            String skills = numberString.substring(index + 1);
            accName = numberString.substring(index + 2);

            if (accName.equals(developer.getAccount().getName())) {
                isExists = true;
                break;
            }
        }

        if (!isExists) {
            numberString = getNextId(id) + "~";
            file.writeBytes(numberString);
            file.writeBytes(System.lineSeparator());
            System.out.println(" Account added.");

        } else {
            System.out.println("Already exists");
        }

        isExists = false;
        file.close();
        return developer;
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
                byte[] remainingBytes = new byte[(int) (file.length() - file.getFilePointer())];
                file.read(remainingBytes);
                file.getChannel().truncate(position);
                file.write(remainingBytes);
                System.out.println("Developer deleted.");
                break;
            }
        }

        file.close();
    }

    @Override
    public Developer findById(Long ID) throws IOException {
        Developer developer = null;
        Set<Skill> skills = null;

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            String numberString = file.readLine();
            index = numberString.indexOf('~');
            Long id = Long.parseLong(numberString.substring(0, index));
            String nickname = numberString.substring(index + 1);

            String skillStr = numberString.substring(index+2);
            String[] lstSkill = skillStr.split(",");
            for (String skillId :
                    lstSkill) {
                Skill skill = skillController.findSkill(Long.valueOf(skillId));
                skills.add(skill);
            }

            String accID = numberString.substring(index+3);
            Account account = accountController.findAccount(Long.valueOf(accID));

            if (id.equals(ID)) {
                developer = new Developer(id, nickname, skills, account);
                break;
            }
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) throws IOException {

        List<Long> lstSkills = null;

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            long position = file.getFilePointer();
            String numberString = file.readLine();
            index = numberString.indexOf('~');
            Long id = Long.parseLong(numberString.substring(0, index));

            if (id.equals(developer.getId())) {

                Set<Skill> ds = developer.getSkills();
                for (Skill skill :
                        ds) {
                    Long skillId = skill.getId();
                    lstSkills.add(skillId);
                }

                String skillsStr = lstSkills
                        .stream()
                        .map(String::valueOf)
                        .collect(
                                Collectors.joining(String.valueOf(','))
                        );

                byte[] remainingBytes = new byte[(int) (file.length() - file.getFilePointer())];
                file.read(remainingBytes);
                file.getChannel().truncate(position);
                numberString = id + "~" + developer.getNickname() + "~" + skillsStr + developer.getAccount().getId();
                file.writeBytes(numberString);
                file.writeBytes(System.lineSeparator());
                file.write(remainingBytes);
                System.out.println("Developer updated.");
                break;
            }
        }

        file.close();
        return developer;
    }

    @Override
    public List<Developer> findAll() throws IOException {
        Set<Skill> skills = null;
        List<Developer> listDeveloper = new ArrayList<>();

        file = getFile(fname);

        while (file.getFilePointer() < file.length()) {
            String numberString = file.readLine();
            index = numberString.indexOf('~');

            Long id = Long.parseLong(numberString.substring(0, index));
            String nickName = numberString.substring(index + 1);
            String skillStr = numberString.substring(index+2);
            String[] lstSkill = skillStr.split(",");
            for (String skillId :
                    lstSkill) {
                Skill skill = skillController.findSkill(Long.valueOf(skillId));
                skills.add(skill);
            }

            String accID = numberString.substring(index+3);
            Account account = accountController.findAccount(Long.valueOf(accID));
            listDeveloper.add(new Developer(id, nickName, skills, account));
        }
        return listDeveloper;
    }
}
