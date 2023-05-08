import java.io.FilterOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsersRepository repository = new UsersRepository("example.txt");
        System.out.println(repository.findAll());
        System.out.println(repository.findAllByAge(23));
        System.out.println(repository.findAllByAge(561));
        System.out.println(repository.findAllByAge(56));
        repository.deleteById(123);
        System.out.println(repository.findAllByAge(23));
        System.out.println(repository.findById(45));
        User user = new User(20,"sadar");
        user.setId(3214);
        System.out.println(repository.findAll());
        repository.update(user);
        System.out.println(repository.findAll());
        User newUser = new User(23,"Botan");
        User secondUser = new User(26, "Andrey");
        User thirdUser = new User(29, "Nikolay");
        repository.save(newUser);
        repository.save(secondUser);
        repository.save(thirdUser);
        System.out.println(repository.findAll());
        repository.deleteById(768);
        System.out.println(repository.findAll());
    }
}