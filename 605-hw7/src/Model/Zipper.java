package Model;

import java.io.*;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zipper compress given file(s) to a destination zip file.
 * This class will throw ZipperException if error occurred.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */

public class Zipper {
    /**
     * A zipper method to create a zip file with a set a paths
     *
     * @param source a set of file paths
     * @param target target destination to output the zip file
     * @return The number of bytes being compressed
     * @throws ZipperException will throw exception if source is invalid or failed to create target zip.
     */
    public static int createzip(Set<String> source, String target) throws ZipperException {
        try {
            int total = 0;
            byte[] buffer = new byte[1024];
            int length;
            FileOutputStream fos = new FileOutputStream(target);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for(String file : source){
                File temp = new File(file);
                FileInputStream fis = new FileInputStream(temp);
                zos.putNextEntry(new ZipEntry(temp.getName()));
                while((length = fis.read(buffer)) > 0){
                    total += length;
                    zos.write(buffer,0,length);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            return total;
        } catch (FileNotFoundException e) {
            throw new ZipperException("File not found");
        } catch (IOException e) {
            throw new ZipperException("IOException");
        }
    }
}

