import com.databse.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter thought: ");

        String thought = input.nextLine();
        //db.InsertIntoDatabase(thought);
        db.ReadAllThoughts();
    }
}