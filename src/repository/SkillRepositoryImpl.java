package repository;

import model.Skill;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static utils.FileHelper.getFile;

public class SkillRepositoryImpl implements SkillRepository{

    RandomAccessFile file = null;
    boolean isExists = false;
    int index;

    @Override
    public Skill save(Skill skill) throws IOException {

        String numberString;
        String skillName;
        long id = 0;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('!');
            id = Long.parseLong(numberString.substring(0, index));
            skillName = numberString.substring(index + 1);

            if (skillName.equals(skill.getSkill())) {
                isExists = true;
                break;
            }
        }

        if (!isExists) {
            numberString = ++id + "!" + skill.getSkill();
            file.writeBytes(numberString);
            file.writeBytes(System.lineSeparator());
            System.out.println(" Skill added.");
        } else {
            System.out.println("Already exists");
            isExists = false;
        }
        file.close();
        return skill;
    }

    @Override
    public void delete(Skill skill) throws IOException {

        String numberString;
        String skillName;
        long id;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('!');
            skillName = numberString.substring(index + 1);

            if (skillName.equals(skill.getSkill())) {
                isExists = true;
                break;
            }
        }

        if(isExists) {
            File tmpFile = new File(UUID.randomUUID().toString() + "temp.txt");
            RandomAccessFile tmpraf
                    = new RandomAccessFile(tmpFile, "rw");
            file.seek(0);

            while (file.getFilePointer() < file.length()) {
                numberString = file.readLine();
                index = numberString.indexOf('!');
                id = Long.parseLong(numberString.substring(0, index));
                skillName = numberString.substring(index + 1);

                if (skillName.equals(skill.getSkill()) || id == skill.getId()) {
                    continue;
                }
                tmpraf.writeBytes(numberString);
                tmpraf.writeBytes(System.lineSeparator());
            }

            file.seek(0);
            tmpraf.seek(0);

            while (tmpraf.getFilePointer() < tmpraf.length()) {
                file.writeBytes(tmpraf.readLine());
                file.writeBytes(System.lineSeparator());
            }

            file.setLength(tmpraf.length());
            file.close();
            tmpraf.close();
            tmpFile.delete();

            System.out.println(" Skill deleted. ");
        } else {
            file.close();
            System.out.println(" Input skill does not exists. ");
        }
    }

    @Override
    public Skill findById(Long ID) throws IOException {
        String numberString;
        String skillName;
        Skill skill = null;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('!');
            Long id = Long.parseLong(numberString.substring(0, index));
            skillName = numberString.substring(index + 1);

            if (id.equals(ID)) {
                skill = new Skill(id, skillName);
                break;
            }
        }
        // TODO как обработать null ?
        return skill;
    }

    @Override
    public Skill update(Skill skill) throws IOException {
        String numberString;
        long id;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('!');
            id = Long.parseLong(numberString.substring(0, index));

            if (id == skill.getId()) {
                isExists = true;
                break;
            }
        }

        if(isExists) {
            File tmpFile = new File(UUID.randomUUID().toString() + "temp.txt");
            RandomAccessFile tmpraf
                    = new RandomAccessFile(tmpFile, "rw");
            file.seek(0);

            while (file.getFilePointer() < file.length()) {
                numberString = file.readLine();
                index = numberString.indexOf('!');
                id = Long.parseLong(numberString.substring(0, index));

                if (id == skill.getId()) {
                    numberString = id + "!" + skill.getSkill();
                }
                tmpraf.writeBytes(numberString);
                tmpraf.writeBytes(System.lineSeparator());
            }

            file.seek(0);
            tmpraf.seek(0);

            while (tmpraf.getFilePointer() < tmpraf.length()) {
                file.writeBytes(tmpraf.readLine());
                file.writeBytes(System.lineSeparator());
            }

            file.setLength(tmpraf.length());
            file.close();
            tmpraf.close();
            tmpFile.delete();

            System.out.println(" Skill updated. ");
        } else {
            file.close();
            System.out.println(" Input skill does not exists. ");
        }
        return skill;
    }

    @Override
    public List<Skill> findAll() throws IOException {
        String numberString;
        String skillName;
        List<Skill> listSkill = new ArrayList<>();

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('!');

            Long id = Long.parseLong(numberString.substring(0, index));
            skillName = numberString.substring(index + 1);
            listSkill.add(new Skill(id, skillName));
        }
        return listSkill;
    }
}
