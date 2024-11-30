import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StreamApiTextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        System.out.println("Виберіть джерело тексту:");
        System.out.println("1. Ввести текст вручну");
        System.out.println("2. Зчитати текст з файлу");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.println("Введіть текст:");
            text = scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Введіть шлях до файлу:");
            String filePath = scanner.nextLine();
            try {
                Path path = Paths.get(filePath);
                text = Files.readString(path);
                System.out.println("Текст успішно зчитано з файлу.");
            } catch (IOException e) {
                System.out.println("Помилка читання файлу: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Невірний вибір.");
            return;
        }

        // Операції Stream API
        List<String> words = Arrays.stream(text.split("\\s+")) // Розбиття на слова
                .map(String::toLowerCase)                      // Приведення до нижнього регістру
                .map(word -> word.replaceAll("[^a-zA-Zа-яА-Я]", "")) // Видалення пунктуації
                .filter(word -> !word.isEmpty())               // Видалення порожніх слів
                .collect(Collectors.toList());

        System.out.println("\nВиберіть операцію:");
        System.out.println("1. Показати всі слова");
        System.out.println("2. Фільтрувати слова, що починаються на певну літеру");
        System.out.println("3. Сортувати слова за алфавітом");
        System.out.println("4. Підрахувати кількість унікальних слів");

        int operation = Integer.parseInt(scanner.nextLine());

        switch (operation) {
            case 1 -> {
                System.out.println("Всі слова:");
                words.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Введіть літеру для фільтрації:");
                String letter = scanner.nextLine().toLowerCase();
                List<String> filteredWords = words.stream()
                        .filter(word -> word.startsWith(letter))
                        .collect(Collectors.toList());
                System.out.println("Слова, що починаються на '" + letter + "':");
                filteredWords.forEach(System.out::println);
            }
            case 3 -> {
                List<String> sortedWords = words.stream()
                        .sorted()
                        .collect(Collectors.toList());
                System.out.println("Слова за алфавітом:");
                sortedWords.forEach(System.out::println);
            }
            case 4 -> {
                long uniqueWordCount = words.stream()
                        .distinct()
                        .count();
                System.out.println("Кількість унікальних слів: " + uniqueWordCount);
            }
            default -> System.out.println("Невірний вибір.");
        }

        scanner.close();
    }
}
