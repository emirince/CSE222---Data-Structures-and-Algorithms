import java.time.LocalDateTime;
import java.util.List;

public class Person {
    private String name; // The name of the person
    private int age; // The age of the person
    private List<String> hobbies; // The list of hobbies of the person
    private LocalDateTime timestamp; // The timestamp of when the person object was created

    // Constructor to initialize the person's details
    public Person(String name, int age, List<String> hobbies, LocalDateTime timestamp) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.timestamp = timestamp;
    }

    /**
     * Gets the name of the person.
     * 
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of the person.
     * 
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the list of hobbies of the person.
     * 
     * @return the list of hobbies
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * Gets the timestamp of when the person object was created.
     * 
     * @return the timestamp of creation
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Override toString method to return the name of the person
    @Override
    public String toString() {
        return name;
    }
}