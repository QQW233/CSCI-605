package test;

import Model.Archiver;
import Model.ZipperException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for archiver. It contains test for add, files, toString, clear and help method.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
class ArchiverTest {

    /**
     * Test to add file to the compress list.
     * @throws ZipperException Error during the compressing process
     */
    @Test
    void addtest() throws ZipperException {
        Archiver a = new Archiver();
        assertEquals(a.add("data/books.PNG"),"data/books.PNG added to list of files to be archived.");
   }

    /**
     * Test to print the current compress list.
     * @throws ZipperException Error during the compressing process
     */
    @Test
    void filestest() throws ZipperException {
        Archiver a = new Archiver();
        a.add("data/books.png");
        a.add("data/tacos.jpg");
        String path = System.getProperty("user.dir");
        assertEquals(a.toString(),"Files to be archived:\n" + path + "\\data/tacos.jpg\\\n" + path + "\\data/books.png\\\n");
    }

    /**
     * Test the archive toString method.
     * @throws ZipperException Error during the compressing process
     */
   @Test
   void toStringtest() throws ZipperException {
        Archiver a = new Archiver();
       assertEquals(a.toString(),"There are no files to be archived. Use the add command to add a file.");
   }

    /**
     * Test to clear the current compress list.
     * @throws ZipperException Error during the compressing process
     */
   @Test
   void cleartest(){
        Archiver a = new Archiver();
        assertEquals(a.clear(),"Files to be archived cleared.");
   }

    /**
     * Test the help command which should print a menu of available options.
     * @throws ZipperException Error during the compressing process
     */
   @Test
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