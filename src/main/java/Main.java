import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static String isExecutableInPath(String executableName) {
        String pathEnv = System.getenv("PATH");
        if (pathEnv == null) {
            return null;
        }

        // Spliting by the system-dependent path separator (; on Windows, : on Unix)
        return Stream.of(pathEnv.split(Pattern.quote(File.pathSeparator)))
                .map(Paths::get)
                .map(dir -> dir.resolve(executableName))
                .filter(fullPath -> Files.isRegularFile(fullPath) && Files.isExecutable(fullPath))
                .map(Path::toAbsolutePath)
                .map(Path::toString)
                .findFirst()
                .orElse(null); // Returns null if no match is found
    }

    public static void main(String[] args) throws Exception {

        HashSet<String> builtIn = new HashSet<>(Arrays.asList("echo", "exit", "type"));

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
                        System.out.println(commandToCheck + " is a shell builtin");
                    } else {
                        String executablePath = isExecutableInPath(commandToCheck);
                        if (executablePath != null) {
                            System.out.println(commandToCheck + " is " + executablePath);
                        } else {
                            System.out.println(commandToCheck+ ": not found");
                        }

                    }
                }
                default -> System.out.println(userInput[0] + ": command not found");
            }
        }
    }
}
