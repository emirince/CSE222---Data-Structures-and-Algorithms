import java.sql.Timestamp;

// Abstract class representing a file system element (either file or directory)
abstract class FileSystemElement {
    
    // Properties for name, creation date, and parent directory
    protected String name;
    protected Timestamp dateCreated;
    protected FileSystemElement parent;

    // Constructor to initialize a file system element with a name, creation date, and parent directory
    public FileSystemElement(String name, Timestamp dateCreated, FileSystemElement parent) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.parent = parent;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for creation date
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    // Getter method for parent directory
    public FileSystemElement getParent() {
        return parent;
    }
}
