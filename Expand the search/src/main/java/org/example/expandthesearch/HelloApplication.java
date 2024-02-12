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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of people
        System.out.println("Enter the number of people:");
        int numPeople = Integer.parseInt(scanner.nextLine());

        // Read and store people data
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numPeople; i++) {
            String[] personData = scanner.nextLine().split("\\s+", 3);
            String firstName = personData[0];
            String lastName = personData[1];
            String email = (personData.length == 3) ? personData[2] : "";
            people.add(new Person(firstName, lastName, email));
        }

        // Read the number of search queries
        System.out.println("Enter the number of search queries:");
        int numQueries = Integer.parseInt(scanner.nextLine());

        // Process each search query
        for (int i = 0; i < numQueries; i++) {
            // Read and process the query
            System.out.println("Enter data to search people:");
            String query = scanner.nextLine().trim().toLowerCase();
            searchPeople(people, query);
        }
    }

    public static void searchPeople(List<Person> people, String query) {
        // Search and print people matching the query
        List<Person> foundPeople = new ArrayList<>();
        for (Person person : people) {
            if (person.contains(query)) {
                foundPeople.add(person);
            }
        }

        // Print the results
        if (!foundPeople.isEmpty()) {
            System.out.println("Found people:");
            for (Person foundPerson : foundPeople) {
                System.out.println(foundPerson);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }
}
