package test;

import Model.Archiver;
import Model.ZipperException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * A test class for archiver
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
class ArchiverTest {
    @Test
    void addtest() throws ZipperException {
        Archiver a = new Archiver();
        assertEquals(a.add("data/books.PNG"),"data/books.PNG added to list of files to be archived.");
   }

   void toStringtest() throws ZipperException {
        Archiver a = new Archiver();
       assertEquals(a,"There are no files to be archived. Use the add command to add a file.");
   }

   void cleartest(){
        Archiver a = new Archiver();
        assertEquals(a.clear(),"Files to be archived cleared.");
   }

   void helptest(){
        Archiver a = new Archiver();
        assertEquals(a.help(),"Enter one of the following commands:\n" +
                "  add <file path> - adds file indicated by the path to the collection of files to archive.\n" +
                "  archive <file path> - writes the collection of added files to the file indicated by the path.\n" +
                "  clear - clears the current list of files to be archived.\n" +
                "  files - prints the current list of files to be archived.\n" +
                "  list <directory> - lists the files in the specified directory\n" +
                "  quit - quits the Zipper Utility.\n" +
                "  unarchive <source> <destination> - extracts the archive specified by source and saves the extracted entries in the destination directory.");
   }
}