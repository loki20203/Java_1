import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TextAnalyzer {

    private static String text; // Робимо "text" змінною класу

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення тексту користувачем
        System.out.println("Введіть текст:");
        text = scanner.nextLine(); // Ініціалізуємо змінну класу

        while (true) {
            System.out.println("\nВиберіть операцію:");
            System.out.println("1. Пошук слова");
            System.out.println("2. Заміна слова");
            System.out.println("3. Підрахунок кількості слів");
            System.out.println("4. Вийти");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 4) {
                System.out.println("Програма завершена.");
                break;
            }

            switch (choice) {
                case 1 -> {
                    // Пошук слова
                    System.out.println("Введіть слово для пошуку:");
                    String wordToFind = scanner.nextLine();

                    Function<String, Boolean> wordSearch = (word) -> text.contains(word);
                    boolean found = wordSearch.apply(wordToFind);

                    if (found) {
                        System.out.println("Слово знайдено в тексті.");
                    } else {
                        System.out.println("Слово не знайдено.");
                    }
                }
                case 2 -> {
                    // Заміна слова
                    System.out.println("Введіть слово для заміни:");
                    String wordToReplace = scanner.nextLine();
                    System.out.println("Введіть нове слово:");
                    String newWord = scanner.nextLine();

                    BiFunction<String, String, String> wordReplace = (oldWord, replacement) ->
                            text.replaceAll(oldWord, replacement);

                    text = wordReplace.apply(wordToReplace, newWord); // Оновлюємо текст
                    System.out.println("Оновлений текст: " + text);
                }
                case 3 -> {
                    // Підрахунок кількості слів
                    Function<String, Integer> wordCount = (inputText) -> {
                        String[] words = inputText.trim().split("\\s+");
                        return words.length;
                    };

                    int count = wordCount.apply(text);
                    System.out.println("Кількість слів у тексті: " + count);
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }
}
