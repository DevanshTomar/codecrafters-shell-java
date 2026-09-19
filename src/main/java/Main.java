import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            String userInput = IO.readln("$ ");
            System.out.println(userInput + ": command not found");
        }
    }
}
