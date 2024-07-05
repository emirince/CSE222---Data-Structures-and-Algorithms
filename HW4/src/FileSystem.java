import java.sql.Timestamp;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// FileSystem class represents the file system and provides various operations on it
class FileSystem{
    private Directory root; // Root directory of the file system

    // Constructor to initialize the file system with a root directory
    public FileSystem(){
        root = new Directory("root", new Timestamp(System.currentTimeMillis()), null);
    }

    // Get the root directory of the file system
    public Directory getRoot() {
        return root;
    }

    // Change the current directory to the specified path
    public Directory changeDirectory(String path, Directory currentDirectory){
        // Find the directory corresponding to the given path
        Directory newDirectory = findDirectory(path);
        
        if(newDirectory != null){
            currentDirectory = newDirectory;
            System.out.println("Directory changed to: " + path);
            return newDirectory;
        } else {
            System.out.println("Directory not found.");
            return currentDirectory;
        }
    }

    // Find a directory in the file system given its path
    public Directory findDirectory(String path){
        String[] directories = path.split("/");
        Directory current = root;

        for(String directory : directories){
            if (directory.isEmpty()){
                continue;
            }

            boolean found = false;
            for(FileSystemElement child : current.getChildren()){
                if(child instanceof Directory && child.getName().equals(directory)){
                    current = (Directory) child;
                    found = true;
                    break;
                }
            }

            if(!found){
                return null;
            }
        }

        return current;
    }

    // List the contents of the specified directory
    public void listDirectoryContents(Directory currentDirectory){
        List<FileSystemElement> children = currentDirectory.getChildren();
        for(FileSystemElement child : children){
            if (child instanceof Directory){
                System.out.println("* " + child.getName() + "/");
            } else {
                System.out.println(child.getName());
            }
        }
    }

    // Create a new file in the specified directory
    public void createFile(String name, Directory currentDirectory){
        File newFile = new File(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
        currentDirectory.addChild(newFile);
        System.out.println("File created: " + name);
    }

    // Create a new directory in the specified directory
    public void createDirectory(String name, Directory currentDirectory){
        Directory newDirectory = new Directory(name, new Timestamp(System.currentTimeMillis()), currentDirectory);
        currentDirectory.addChild(newDirectory);
        System.out.println("Directory created: " + name + "/");
    }

    // Delete a file from the specified directory
    public void deleteFile(String name, Directory currentDirectory) {
        List<FileSystemElement> children = currentDirectory.getChildren();
        boolean found = false;
        for (FileSystemElement child : children) {
            if (child instanceof File && child.getName().equals(name)) {
                currentDirectory.removeChild(child); 
                System.out.println("File deleted: " + name);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("File not found."); 
        }
    }

    // Delete a directory from the specified directory
    public void deleteDirectory(String name, Directory currentDirectory) {
        if (recursiveDeleteDirectory(name, currentDirectory)) {
            System.out.println("Directory deleted: " + name + "/");
        } else {
            System.out.println("Directory not found.");
        }
    }

    // Recursively delete a directory and all its contents
    private boolean recursiveDeleteDirectory(String name, Directory currentDirectory) {
        List<FileSystemElement> children = currentDirectory.getChildren();
        for (FileSystemElement child : children) {
            if (child instanceof Directory && child.getName().equals(name)) {
                Directory dirToDelete = (Directory) child;
                if (dirToDelete.getChildren().isEmpty()) {
                    currentDirectory.removeChild(dirToDelete); 
                    return true;
                } else {
                    recursiveDeleteAllSubdirectories(dirToDelete); 
                    currentDirectory.removeChild(dirToDelete);
                    return true;
                }
            } else if (child instanceof Directory) {
                if (recursiveDeleteDirectory(name, (Directory) child)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Recursively delete all subdirectories of a directory
    private void recursiveDeleteAllSubdirectories(Directory directory) {
        List<FileSystemElement> children = new LinkedList<>(directory.getChildren()); 
        children.forEach(child -> {
            if (child instanceof Directory) {
                recursiveDeleteAllSubdirectories((Directory) child);
            }
            directory.removeChild(child);
        });
    }

    // Move a file or directory to the specified path
    public void moveFileOrDirectory(String name, String newPath, Directory currentDirectory){
        FileSystemElement fileOrDir = findFileOrDirectory(name, currentDirectory);
        
        if (fileOrDir != null){
            Directory newParentDir = findDirectory(newPath);
            if (newParentDir != null){
                currentDirectory.removeChild(fileOrDir);
                fileOrDir.parent = newParentDir;
                newParentDir.addChild(fileOrDir);
                System.out.println("File/directory moved: " + name + " to " + newPath);
            } else {
                System.out.println("Destination directory not found.");
            }
        } else {
            System.out.println("File/directory not found.");
        }
    }

    // Find a file or directory with the specified name in the given directory
    private FileSystemElement findFileOrDirectory(String name, Directory currentDirectory) {
        List<FileSystemElement> children = currentDirectory.getChildren();
        for (FileSystemElement child : children) {
            if(child.getName().equals(name)){
                return child;
            }
        }
        return null;
    }

     // Search for a file or directory containing the specified query string
    public void searchFileOrDirectory(String query) {
        boolean found = searchRecursively(root, query, false);
        if (!found) {
            System.out.println("No files or directories found containing: " + query);
        }
    }

    // Recursively search for a file or directory containing the specified query string
    private boolean searchRecursively(FileSystemElement current, String query, boolean found) {
        if (current.getName().contains(query)) {
            System.out.println("Found: " + getPath(current));
            found = true;  // Update found to true as we have a match
        }
        if (current instanceof Directory) {
            List<FileSystemElement> children = ((Directory) current).getChildren();
            for (FileSystemElement child : children) {
                found = searchRecursively(child, query, found);  // Recursively search and update found
            }
        }
        return found;
    }


    // Get the path of the specified file or directory
    public String getPath(FileSystemElement element) {
        StringBuilder path = new StringBuilder();
        FileSystemElement current = element;
        while (current != null) {
            if(current.getName().equals("root")){
                path.insert(0, "/");
            } else {
                path.insert(0, current.getName() + "/");
            }
            current = current.getParent();
        }
        return path.toString();
    }

    // Print the directory tree starting from the specified directory
    public void printDirectoryTree(Directory currentDirectory) {
        printTree(root, 0, false, currentDirectory);
    }

    // Recursively print the directory tree starting from the specified element
    private void printTree(FileSystemElement current, int depth, boolean isDone, Directory currentDirectory) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        if (current instanceof Directory) {
            if (current == currentDirectory) {
                System.out.println("* " + current.getName() + "/ (Current directory)");
                isDone = true;
            } else {
                System.out.println("* " + current.getName() + "/");
                if (isDone) {
                    return;
                }
            }
            
            List<FileSystemElement> children = ((Directory) current).getChildren();
            for (FileSystemElement child : children) {
                printTree(child, depth + 1, isDone, currentDirectory);
            }
        } else {
            System.out.println(current.getName());
            if (isDone) {
                return;
            }
        }
    }

    // Sort the contents of the specified directory by date created
    public void sortContentsByDateCreated(Directory currentDirectory) {
        List<FileSystemElement> children = currentDirectory.getChildren();
        if(children.isEmpty()){
            System.out.println("There is no content to sort");
        } else {
            children.sort(Comparator.comparing(FileSystemElement::getDateCreated));
            for (FileSystemElement child : children) {
                if(child instanceof File){
                    System.out.println(child.getName() + " (" + child.getDateCreated() + ")");
                } else {
                    System.out.println("* " + child.getName() + " (" + child.getDateCreated() + ")");
                }
            }
        }  
    }

    // Delete a file or directory with the specified name from the given directory
    public void deleteFileOrDirectory(String name, Directory currentDirectory) {
        FileSystemElement fileOrDir = findFileOrDirectory(name, currentDirectory);
        if (fileOrDir != null) {
            if (fileOrDir instanceof File) {
                deleteFile(name, currentDirectory);
            } else if (fileOrDir instanceof Directory) {
                deleteDirectory(name, currentDirectory);
            }
        } else {
            System.out.println("File/directory not found.");
        }
    }
}
