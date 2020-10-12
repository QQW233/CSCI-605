package Controller;

import Model.Archiver;
import Model.ZipperException;
/**
 * Controller used to call methods in module according to input and return the reuslt.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Controller {
    private Archiver archiver = new Archiver();

    /**
     * command_parsing takes in a list of commands and decide which method
     * in archiver to call and return corresponding result.
     * @param command user command input
     * @return a String indicates the result
     * @throws ZipperException Will throw exception when argument is invalid or
     * exception is thrown in executing.
     */
    public String command_parsing(String[] command) throws ZipperException {
        switch (command[0]) {
            case "help":
                return archiver.help();
            case "add":
                if (command.length < 2)
                    throw new ZipperException("Invalid arguments.");
                return archiver.add(command[1]);
            case "archive":
                if (command.length < 2)
                    throw new ZipperException("Invalid arguments.");
                return archiver.archive(command[1]);
            case "clear":
                return archiver.clear();
            case "files":
                return archiver.toString();
            case "list":
                if (command.length < 2)
                    throw new ZipperException("Invalid arguments.");
                return archiver.list(command[1]);
            case "unarchive":
                if (command.length < 3)
                    throw new ZipperException("Invalid arguments.");
                return archiver.unarchive(command[1],command[2]);
            default:
                return String.format("Unrecognized command: %s\n",command[0])
                        + "Use 'help' to display a list of commands.";
        }
    }
}
