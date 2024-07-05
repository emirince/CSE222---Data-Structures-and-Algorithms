import java.sql.Timestamp;

// File class representing a file in the file system
class File extends FileSystemElement {
    
    // Constructor to initialize a file with a name, creation date, and parent directory
    public File(String name, Timestamp dateCreated, FileSystemElement parent) {
        super(name, dateCreated, parent);
    }
}
