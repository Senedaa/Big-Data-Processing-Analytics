import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenerateRandomNumbers {
    public static void main(String[] args) {
        System.out.println("How many random numbers to generate:");
        Scanner input = new Scanner(System.in);
        int RandomNumCount = input.nextInt();
        System.out.println("What's the radius?");
        int radius = input.nextInt();
        int diameter = radius * 2;
        input.close();

        try {
            File file = new File("./PiCalculationInput");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < RandomNumCount; i++) {
                int xvalue = (int) (Math.random() * diameter);
                int yvalue = (int) (Math.random() * diameter);
                writer.write("(" + xvalue + "," + yvalue + ") ");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
