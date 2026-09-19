import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            String userInput = IO.readln("$ ").trim();

            switch (userInput) {
                case "exit" -> System.exit(0);
                default -> System.out.println(userInput + ": command not found");
            }
        }
    }
}
