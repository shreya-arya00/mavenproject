import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
}

public class Main {
    private static List<Person> people = new ArrayList<>();
    private static Map<String, List<Integer>> invertedIndex = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].equals("--data")) {
            System.out.println("Usage: java PeopleSearch --data <filename>");
            System.exit(1);
        }

        // Read and store people data from the specified file
        String filename = args[1];
        readPeopleDataFromFile(filename);

        // Build the inverted index
        buildInvertedIndex();

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

    private static void readPeopleDataFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] personData = line.split("\\s+", 3);
                String firstName = personData[0];
                String lastName = personData[1];
                String email = (personData.length == 3) ? personData[2] : "";
                people.add(new Person(firstName, lastName, email));

                // Update the inverted index
                updateInvertedIndex(firstName.toLowerCase(), index);
                updateInvertedIndex(lastName.toLowerCase(), index);
                updateInvertedIndex(email.toLowerCase(), index);

                index++;
            }
        } catch (IOException e) {
            System.out.println("Error reading data from file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void updateInvertedIndex(String key, int index) {
        invertedIndex.computeIfAbsent(key, k -> new ArrayList<>()).add(index);
    }

    private static void buildInvertedIndex() {
        // The inverted index is already built during data reading.
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
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine().toUpperCase();

        System.out.println("Enter a name or email to search all suitable people.");
        String[] query = scanner.nextLine().trim().toLowerCase().split("\\s+");

        List<Integer> resultIndexes = new ArrayList<>();
        Set<Integer> tempSet = new HashSet<>();

        switch (strategy) {
            case "ALL":
                for (String term : query) {
                    List<Integer> indexes = invertedIndex.getOrDefault(term, Collections.emptyList());
                    if (resultIndexes.isEmpty()) {
                        resultIndexes.addAll(indexes);
                    } else {
                        resultIndexes.retainAll(indexes);
                    }
                }
                break;
            case "ANY":
                for (String term : query) {
                    List<Integer> indexes = invertedIndex.getOrDefault(term, Collections.emptyList());
                    tempSet.addAll(indexes);
                }
                resultIndexes.addAll(tempSet);
                break;
            case "NONE":
                List<Integer> allIndexes = new ArrayList<>();
                for (String term : query) {
                    List<Integer> indexes = invertedIndex.getOrDefault(term, Collections.emptyList());
                    allIndexes.addAll(indexes);
                }
                for (int i = 0; i < people.size(); i++) {
                    if (!allIndexes.contains(i)) {
                        resultIndexes.add(i);
                    }
                }
                break;
            default:
                System.out.println("Invalid strategy. Please enter ALL, ANY, or NONE.");
                return;
        }

        // Print the results
        if (!resultIndexes.isEmpty()) {
            System.out.println(resultIndexes.size() + " persons found:");
            for (int index : resultIndexes) {
                System.out.println(people.get(index));
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