import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// Клас User
class User {
    private final int id;
    private final String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}

public class OptionalExample {

    // Метод пошуку користувача за ідентифікатором
    public static Optional<User> findUserById(List<User> users, int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення списку користувачів
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Олександр"));
        users.add(new User(2, "Марія"));
        users.add(new User(3, "Андрій"));

        // Введення ідентифікатора користувача
        System.out.println("Введіть ідентифікатор користувача для пошуку:");
        int id = Integer.parseInt(scanner.nextLine());

        // Пошук користувача
        Optional<User> userOptional = findUserById(users, id);

        // Виведення результату
        userOptional.ifPresentOrElse(
                user -> System.out.println("Знайдений користувач: " + user),
                () -> System.out.println("Користувач з ідентифікатором " + id + " не знайдений.")
        );

        scanner.close();
    }
}
