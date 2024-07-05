import java.sql.Timestamp;
import java.util.LinkedList;

// Directory class representing a directory in the file system
class Directory extends FileSystemElement {
    private LinkedList<FileSystemElement> children; // List of children (files or subdirectories) in this directory

    // Constructor to initialize a directory with a name, creation date, and parent directory
    public Directory(String name, Timestamp dateCreated, FileSystemElement parent) {
        super(name, dateCreated, parent);
        children = new LinkedList<>();
    }

    // Add a child (file or subdirectory) to this directory
    public void addChild(FileSystemElement child) {
        children.add(child);
    }

    // Remove a child (file or subdirectory) from this directory
    public void removeChild(FileSystemElement child) {
        children.remove(child);
    }

    // Get the list of children (files or subdirectories) in this directory
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }
}
