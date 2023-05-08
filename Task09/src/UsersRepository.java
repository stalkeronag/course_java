import java.io.*;
import java.util.*;

public class UsersRepository {

    private final String path;

    private int lastGeneratedId = 100;

    private final ArrayList<User> contentsUsers;

    private final HashMap<Integer, Integer> mapIndexRowUser;

    public UsersRepository(String path) {
        this.path = path;
        contentsUsers = new ArrayList<>();
        mapIndexRowUser = new HashMap<>();
        fullContentsArray();
        updateMapIndexes();
    }

    public List<User> findAll() {
        return contentsUsers;
    }

    public List<User> findAllByAge(int age) {
        ArrayList<User> result = new ArrayList<>();
        for (User contentsUser : contentsUsers) {
            if (contentsUser.getAge() == age) {
                result.add(contentsUser);
            }
        }
        return result;
    }

    public User findById(int id) {
        if (mapIndexRowUser.containsKey(id)) {
            int index = mapIndexRowUser.get(id);
            return contentsUsers.get(index);
        } else {
            throw new IllegalStateException(new UserNotFoundException("пользоватеь не найден"));
        }
    }

    public void update(User user) {
        int id = user.getId();
        if (mapIndexRowUser.containsKey(id)) {
            int index = mapIndexRowUser.get(id);
            contentsUsers.get(index).setAge(user.getAge());
            contentsUsers.get(index).setName(user.getName());
            updateFile();
        } else {
            throw new IllegalStateException(new UserNotFoundException("ползователь не найден"));
        }
    }

    public void deleteById(int id) {
        if (mapIndexRowUser.containsKey(id)) {
            int index = mapIndexRowUser.get(id);
            contentsUsers.remove(index);
            updateMapIndexes();
            updateFile();
        } else {
            throw new IllegalStateException(new UserNotFoundException("пользователь не найден"));
        }
    }

    public void save(User user) {
        Random random = new Random(lastGeneratedId);
        int idUser = Math.abs(random.nextInt());
        while (mapIndexRowUser.containsKey(idUser & 10000)) {
            idUser = Math.abs(random.nextInt());
        }
        lastGeneratedId = idUser & 10000;
        user.setId(lastGeneratedId);
        contentsUsers.add(user);
        int lastIndex = contentsUsers.size() - 1;
        mapIndexRowUser.put(lastGeneratedId, lastIndex);
        updateFile();
    }


    private void updateFile() {

        try (FileWriter writer = new FileWriter(path, false);
             BufferedWriter buffWriter = new BufferedWriter(writer)) {
            for (User contentsUser : contentsUsers) {
                buffWriter.write(contentsUser.toString());
                buffWriter.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void fullContentsArray() {
        String[] words;
        try (FileReader reader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split(" ");
                int id = Integer.parseInt(words[0]);
                String name = words[1];
                int age = Integer.parseInt(words[2]);
                User user = new User(age, name);
                user.setId(id);
                contentsUsers.add(user);
            }
            int lastIndex = contentsUsers.size() - 1;
            if (lastIndex > -1) {
                lastGeneratedId = contentsUsers.get(lastIndex).getId();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateMapIndexes() {
        for (int i = 0; i < contentsUsers.size(); i++) {
            int id = contentsUsers.get(i).getId();
            mapIndexRowUser.put(id, i);
        }
    }
}
