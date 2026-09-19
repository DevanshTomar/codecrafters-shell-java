import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        HashSet<String> builtIn = new HashSet<>(Arrays.asList("echo", "exit", "Type"));

        while (true) {
            String[] userInput = IO.readln("$ ").trim().split(" ");
            if (userInput.length == 0) continue;

            String command = userInput[0];

            switch (command) {
                case "exit" -> System.exit(0);
                case "echo" -> {
                    if (userInput.length == 1) {
                        continue;
                    }
                    String[] strings = Arrays.copyOfRange(userInput, 1, userInput.length);
                    System.out.println(String.join(" ", strings));
                }
                case "type" -> {
                    String commandToCheck = userInput[1];
                    if (builtIn.contains(commandToCheck)) {
                        System.out.println(commandToCheck + ": is a shell builtin");
                    } else {
                        System.out.println(commandToCheck+ ": command not found");
                    }
                }
                default -> System.out.println(userInput[0] + ": command not found");
            }
        }
    }
}
