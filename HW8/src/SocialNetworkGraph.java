import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SocialNetworkGraph {
    // Date and time formatter for consistent timestamp formatting
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Map to store people in the network by their names
    private Map<String, Person> people;
    
    // Graph representation of friendships, mapping each person to their list of friends
    private Map<Person, List<Person>> graph;

    // Constructor initializes the people and graph maps
    public SocialNetworkGraph() {
        people = new HashMap<>();
        graph = new HashMap<>();
    }

    /**
     * Adds a new person to the network.
     * 
     * @param name     the name of the person
     * @param age      the age of the person
     * @param hobbies  the hobbies of the person
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        LocalDateTime timestamp = LocalDateTime.now(); // Current timestamp
        Person newPerson = new Person(name, age, hobbies, timestamp); // Create new person object
        people.put(name, newPerson); // Add person to people map
        graph.put(newPerson, new ArrayList<>()); // Initialize empty list of friends for the person in the graph
        System.out.println("Person added: " + name + " (Timestamp: " + timestamp.format(formatter) + ")"); // Log addition
    }

    /**
     * Removes a person from the network.
     * 
     * @param name      the name of the person
     * @param timestamp the timestamp of the person's creation
     */
    public void removePerson(String name, LocalDateTime timestamp) {
        Person person = people.get(name); // Retrieve person from people map
        if (person != null && person.getTimestamp().withNano(0).equals(timestamp.withNano(0))) { // Check if person exists and timestamp matches
            graph.remove(person); // Remove person from graph
            for (List<Person> friends : graph.values()) { // Remove person from all friends lists
                friends.remove(person);
            }
            people.remove(name); // Remove person from people map
            System.out.println("Person removed: " + name); // Log removal
        } else {
            System.out.println("Person not found."); // Log if person not found
        }
    }

    /**
     * Adds a friendship between two people in the network.
     * 
     * @param name1      the name of the first person
     * @param timestamp1 the timestamp of the first person's creation
     * @param name2      the name of the second person
     * @param timestamp2 the timestamp of the second person's creation
     */
    public void addFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person person1 = people.get(name1); // Retrieve first person from people map
        Person person2 = people.get(name2); // Retrieve second person from people map

        if (person1 != null && person2 != null && person1.getTimestamp().withNano(0).equals(timestamp1.withNano(0)) && person2.getTimestamp().withNano(0).equals(timestamp2.withNano(0))) {
            graph.get(person1).add(person2); // Add second person to first person's friends list
            graph.get(person2).add(person1); // Add first person to second person's friends list
            System.out.println("Friendship added between " + name1 + " and " + name2); // Log friendship addition
        } else {
            System.out.println("One or both persons not found."); // Log if one or both persons not found
        }
    }

    /**
     * Removes a friendship between two people in the network.
     * 
     * @param name1      the name of the first person
     * @param timestamp1 the timestamp of the first person's creation
     * @param name2      the name of the second person
     * @param timestamp2 the timestamp of the second person's creation
     */
    public void removeFriendship(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person person1 = people.get(name1); // Retrieve first person from people map
        Person person2 = people.get(name2); // Retrieve second person from people map

        if (person1 != null && person2 != null && person1.getTimestamp().withNano(0).equals(timestamp1.withNano(0)) && person2.getTimestamp().withNano(0).equals(timestamp2.withNano(0))) {
            graph.get(person1).remove(person2); // Remove second person from first person's friends list
            graph.get(person2).remove(person1); // Remove first person from second person's friends list
            System.out.println("Friendship removed between " + name1 + " and " + name2); // Log friendship removal
        } else {
            System.out.println("One or both persons not found."); // Log if one or both persons not found
        }
    }

    /**
     * Finds the shortest path between two people in the network using Breadth-First Search (BFS).
     * 
     * @param name1      the name of the first person
     * @param timestamp1 the timestamp of the first person's creation
     * @param name2      the name of the second person
     * @param timestamp2 the timestamp of the second person's creation
     */
    public void findShortestPath(String name1, LocalDateTime timestamp1, String name2, LocalDateTime timestamp2) {
        Person start = people.get(name1); // Retrieve start person from people map
        Person end = people.get(name2); // Retrieve end person from people map

        if (start == null || end == null || !start.getTimestamp().withNano(0).equals(timestamp1.withNano(0)) || !end.getTimestamp().withNano(0).equals(timestamp2.withNano(0))) {
            System.out.println("One or both persons not found."); // Log if one or both persons not found
            return;
        }

        Map<Person, Person> prev = new HashMap<>(); // Map to store the previous person in the shortest path
        Queue<Person> queue = new LinkedList<>(); // Queue for BFS
        Set<Person> visited = new HashSet<>(); // Set of visited persons

        queue.add(start); // Add start person to queue
        visited.add(start); // Mark start person as visited

        while (!queue.isEmpty()) {
            Person current = queue.poll(); // Get the next person from the queue
            if (current.equals(end)) {
                break; // If end person is found, exit the loop
            }

            for (Person neighbor : graph.get(current)) { // Iterate over friends of the current person
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor); // Add unvisited friends to the queue
                    visited.add(neighbor); // Mark friend as visited
                    prev.put(neighbor, current); // Set the current person as the previous person for the friend
                }
            }
        }

        if (!prev.containsKey(end)) {
            System.out.println("No path found."); // Log if no path found
        } else {
            List<Person> path = new LinkedList<>(); // List to store the path
            for (Person at = end; at != null; at = prev.get(at)) { // Reconstruct the path from end to start
                path.add(at);
            }
            Collections.reverse(path); // Reverse the path to get it from start to end
            System.out.println("Shortest path: " + path.stream().map(Person::getName).reduce((a, b) -> a + " -> " + b).orElse("")); // Log the shortest path
        }
    }

    /**
     * Suggests friends for a person in the network based on mutual friends and common hobbies.
     * 
     * @param name            the name of the person
     * @param timestamp       the timestamp of the person's creation
     * @param maxSuggestions  the maximum number of suggestions to return
     */
    public void suggestFriends(String name, LocalDateTime timestamp, int maxSuggestions) {
        Person person = people.get(name); // Retrieve person from people map
        if (person == null || !person.getTimestamp().withNano(0).equals(timestamp.withNano(0))) {
            System.out.println("Person not found."); // Log if person not found
            return;
        }

        Map<Person, Double> scores = new HashMap<>(); // Map to store friend suggestion scores
        for (Person friend : graph.get(person)) { // Iterate over friends of the person
            for (Person friendOfFriend : graph.get(friend)) { // Iterate over friends of friends
                if (!friendOfFriend.equals(person) && !graph.get(person).contains(friendOfFriend)) {
                    double score = calculateScore(person, friendOfFriend); // Calculate friend suggestion score
                    scores.put(friendOfFriend, scores.getOrDefault(friendOfFriend, 0.0) + score); // Add score to the map
                }
            }
        }

        List<Map.Entry<Person, Double>> suggestions = new ArrayList<>(scores.entrySet()); // Convert map entries to a list
        suggestions.sort(Map.Entry.<Person, Double>comparingByValue().reversed()); // Sort suggestions by score
        suggestions = suggestions.subList(0, Math.min(maxSuggestions, suggestions.size())); // Limit the number of suggestions

        System.out.println("Suggested friends for " + name + ":"); // Log suggested friends
        for (Map.Entry<Person, Double> suggestion : suggestions) {
            Person suggestedFriend = suggestion.getKey();
            double score = suggestion.getValue();
            System.out.println(suggestedFriend.getName() + " (Score: " + score + ", " + countMutualFriends(person, suggestedFriend) + " mutual friends, " + countCommonHobbies(person, suggestedFriend) + " common hobbies)");
        }
    }

    /**
     * Calculates the friend suggestion score between two people in the network.
     * The score is based on the number of mutual friends and common hobbies.
     * 
     * @param person    the first person
     * @param candidate the second person
     * @return the friend suggestion score
     */
    private double calculateScore(Person person, Person candidate) {
        long mutualFriends = graph.get(person).stream().filter(graph.get(candidate)::contains).count(); // Count mutual friends
        long commonHobbies = person.getHobbies().stream().filter(candidate.getHobbies()::contains).count(); // Count common hobbies
        return mutualFriends * 1.0 + commonHobbies * 0.5; // Calculate score based on mutual friends and common hobbies
    }

    /**
     * Counts the number of mutual friends between two people in the network.
     * 
     * @param person    the first person
     * @param candidate the second person
     * @return the number of mutual friends
     */
    private long countMutualFriends(Person person, Person candidate) {
        return graph.get(person).stream().filter(graph.get(candidate)::contains).count(); // Count mutual friends
    }

    /**
     * Counts the number of common hobbies between two people in the network.
     * 
     * @param person    the first person
     * @param candidate the second person
     * @return the number of common hobbies
     */
    private long countCommonHobbies(Person person, Person candidate) {
        return person.getHobbies().stream().filter(candidate.getHobbies()::contains).count(); // Count common hobbies
    }

    /**
     * Counts the number of clusters in the social network graph using Breadth-First Search (BFS).
     * A cluster is a group of connected people in the network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>(); // Set of visited persons
        int clusterCount = 0; // Counter for clusters
        List<List<Person>> clusters = new ArrayList<>(); // List to store clusters

        for (Person person : people.values()) { // Iterate over all people in the network
            if (!visited.contains(person)) { // If person is not visited
                clusterCount++; // Increment cluster counter
                List<Person> cluster = new ArrayList<>(); // Initialize new cluster
                bfs(person, visited, cluster); // Perform BFS to explore the cluster
                clusters.add(cluster); // Add cluster to the list of clusters
            }
        }

        System.out.println("Number of clusters found: " + clusterCount); // Log number of clusters
        int clusterIndex = 1;
        for (List<Person> cluster : clusters) { // Iterate over clusters
            System.out.println("\nCluster " + clusterIndex++ + ":"); // Log cluster index
            for (Person person : cluster) { // Iterate over persons in the cluster
                System.out.println(person.getName()); // Log person name
            }
        }
    }

    /**
     * Performs a Breadth-First Search (BFS) starting from the given person.
     * This method is used to explore a cluster of connected people.
     * 
     * @param start    the starting person for the BFS
     * @param visited  the set of visited people
     * @param cluster  the list of people in the current cluster
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>(); // Queue for BFS
        queue.add(start); // Add start person to queue
        visited.add(start); // Mark start person as visited
        cluster.add(start); // Add start person to current cluster

        while (!queue.isEmpty()) {
            Person current = queue.poll(); // Get the next person from the queue
            for (Person neighbor : graph.get(current)) { // Iterate over friends of the current person
                if (!visited.contains(neighbor)) { // If friend is not visited
                    queue.add(neighbor); // Add friend to the queue
                    visited.add(neighbor); // Mark friend as visited
                    cluster.add(neighbor); // Add friend to current cluster
                }
            }
        }
    }
}