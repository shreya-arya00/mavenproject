import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {
    String firstName;
    String lastName;
    String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email;
    }

    public boolean contains(String query) {
        // Check if any part of the person's details contains the query
        return firstName.toLowerCase().contains(query) ||
                lastName.toLowerCase().contains(query) ||
                email.toLowerCase().contains(query);
    }
}

public class Main {
    private static List<Person> people = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Read and store people data
        readPeopleData();

        // Display the menu and process user input until exit
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    searchPerson();
                    break;
                case 2:
                    printAllPeople();
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        } while (choice != 0);
    }

    private static void readPeopleData() {
        // Read the number of people
        System.out.println("Enter the number of people:");
        int numPeople = Integer.parseInt(scanner.nextLine());

        // Read and store people data
        for (int i = 0; i < numPeople; i++) {
            String[] personData = scanner.nextLine().split("\\s+", 3);
            String firstName = personData[0];
            String lastName = personData[1];
            String email = (personData.length == 3) ? personData[2] : "";
            people.add(new Person(firstName, lastName, email));
        }
    }

    private static void displayMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    private static int getUserChoice() {
        System.out.print("> ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void searchPerson() {
        System.out.println("Enter a name or email to search all suitable people.");
        String query = scanner.nextLine().trim().toLowerCase();

        // Search and print people matching the query
        List<Person> foundPeople = new ArrayList<>();
        for (Person person : people) {
            if (person.contains(query)) {
                foundPeople.add(person);
            }
        }

        // Print the results
        if (!foundPeople.isEmpty()) {
            System.out.println("=== Found people ===");
            for (Person foundPerson : foundPeople) {
                System.out.println(foundPerson);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private static void printAllPeople() {
        System.out.println("=== List of people ===");
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
