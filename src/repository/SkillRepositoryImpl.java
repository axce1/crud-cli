package repository;

import model.Skill;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static utils.IOUtils.getFile;
import static utils.IOUtils.getNextId;

public class SkillRepositoryImpl implements SkillRepository{

    RandomAccessFile file;
    int index;

    @Override
    public Skill save(Skill skill) throws IOException {
        boolean isExists = false;
        String numberString;
        String skillName;
        long id = 0;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            numberString = file.readLine();
            index = numberString.indexOf('!');
            id = Long.parseLong(numberString.substring(0, index));
            skillName = numberString.substring(index + 1);

            if (skillName.equals(skill.getName())) {
                isExists = true;
                break;
            }
        }

        if (!isExists) {
            numberString = getNextId(id) + "~" + skill.getName();
            file.writeBytes(numberString);
            file.writeBytes(System.lineSeparator());
            System.out.println(" Skill added.");

        } else {
            System.out.println("Already exists");
        }

        isExists = false;
        file.close();
        return skill;
    }

    @Override
    public void delete(Skill skill) throws IOException {
        String numberString;
        String skillName;
        long position;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            position = file.getFilePointer();
            numberString = file.readLine();
            index = numberString.indexOf('!');
            skillName = numberString.substring(index + 1);

            if (skillName.equals(skill.getName())) {
                byte[] remainingBytes = new byte[(int) (file.length() - file.getFilePointer())];
                file.read(remainingBytes);
                file.getChannel().truncate(position);
                file.write(remainingBytes);
                break;
            }
        }

        file.close();
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
        long position;

        file = getFile("skill.txt");

        while (file.getFilePointer() < file.length()) {
            position = file.getFilePointer();
            numberString = file.readLine();
            index = numberString.indexOf('!');
            id = Long.parseLong(numberString.substring(0, index));

            if (id == skill.getId()) {
                byte[] remainingBytes = new byte[(int) (file.length() - file.getFilePointer())];
                file.read(remainingBytes);
                file.getChannel().truncate(position);
                numberString = id + "!" + skill.getName();
                file.writeBytes(numberString);
                file.writeBytes(System.lineSeparator());
                file.write(remainingBytes);
                System.out.println("Skill updated.");
                break;
            }
        }

        file.close();
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
