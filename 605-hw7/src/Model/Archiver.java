package Model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
/**
 * Archiver class used to handle all options and call zipper or unzipper if needed.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Archiver {
    Set<String> files_list;

    public Archiver(){
        files_list = new HashSet<>();
    }

    /**
     * Adds a new filename instance to the list of files
     * @param filename a new file entry
     * @return A string representing the result
     * @throws ZipperException will throw exception if filename does not direct to a file
     */
    public String add(String filename) throws ZipperException {
        File tmp = new File(filename);
        if (!tmp.exists()||!tmp.isFile())
            throw new ZipperException
                    (String.format("File does not exist: %s",
                            new StringBuilder().append(System.getProperty("user.dir")).append(File.separator).append(filename).toString()));
        files_list.add(filename);
        return String.format("%s added to list of files to be archived.",filename);
    }

    /**
     * clears the list of files
     * @return A string indicating successful execution
     */
    public String clear(){
        files_list.clear();
        return "Files to be archived cleared.";
    }

    /**
     * A toString method that prints the content of list of files.
     * @return A string representing list of files.
     */
    public String toString(){
        String result;
        if (files_list.isEmpty()){
            result = "There are no files to be archived. Use the add command to add a file.";
        }
        else {
            StringBuilder builder = new StringBuilder();
            builder.append("Files to be archived:\n");
            for (String line : files_list){
                builder.append(System.getProperty("user.dir"));
                builder.append("\\");
                builder.append(line);
                builder.append("\\");
                builder.append("\n");
            }
            result = builder.toString();
        }
        return result;
    }

    /**
     * return the all the available commands.
     * @return a String representation of all available commands.
     */
    public String help(){
        return "Enter one of the following commands:\n" +
                "  add <file path> - adds file indicated by the path to the collection of files to archive.\n" +
                "  archive <file path> - writes the collection of added files to the file indicated by the path.\n" +
                "  clear - clears the current list of files to be archived.\n" +
                "  files - prints the current list of files to be archived.\n" +
                "  list <directory> - lists the files in the specified directory\n" +
                "  quit - quits the Zipper Utility.\n" +
                "  unarchive <source> <destination> - extracts the archive specified by source and saves the extracted entries in the destination directory.";
    }

    /**
     * list all the files and directories under given destination
     * @param dest a string directing to a directory
     * @return A string representation of all files and directories under given directory
     * @throws ZipperException will throw exception if the path given is not a directory
     */
    public String list(String dest) throws ZipperException {
        StringBuilder builder = new StringBuilder();
        File destDir = new File(dest);
        File[] files = destDir.listFiles();
        if (files == null){
            throw new ZipperException(String.format("Path is not a directory: %s",dest));
        }
        builder.append(String.format("Listing files in '%s'...\n",dest));
        for (File f: files){
            builder.append("  ");
            builder.append(System.getProperty("user.dir"));
            builder.append(File.separator);
            builder.append(dest);
            builder.append(File.separator);
            builder.append(f.getName());
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Zips all files in files_list to given destination
     * @param dest a path for the output
     * @return A string indicating whether the execution is successful
     * @throws ZipperException will throw exception if problem is met during zipping
     */
    public String archive (String dest) throws ZipperException {
        int total = Zipper.createzip(files_list,dest);
        return String.format("Archive successfully created: %s(%d bytes)",dest,total);
    }

    /**
     * Unzip given file to user desired destination
     * @param source source zip file
     * @param dest output destination
     * @return A string representation of the unzip content
     * @throws ZipperException will throw Exception if unzip failed.
     */
    public String unarchive (String source, String dest) throws ZipperException {
        return Unzipper.unzip(source,dest);
    }
}
