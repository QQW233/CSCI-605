package Model;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Unzipper extracts all files in a given zip file to the destination directory.
 * Throws ZipperException if error occurred.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Unzipper {
    /**
     * Unzip method that unzips given zip file to a destination directory. This directory will be created during the process.
     *
     * @param source path to a zip file
     * @param dest destination for output
     * @return A string representation of unzip result
     * @throws ZipperException will throw if the destination is not available or
     * the zip file is not valid.
     */
    public static String unzip(String source,String dest) throws ZipperException {
        try {
            StringBuilder builder = new StringBuilder();
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(source);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry entry = zis.getNextEntry();
            builder.append(String.format("Extracting the archive '%s' " +
                    "and saving entries in directory '%s'...\n",source,dest));
            File dir = new File(dest);
            if (!dir.exists()) dir.mkdirs();
            // extract all the files in the zip file
            while (entry != null){
                File temp = new File(dest,entry.getName());
                builder.append(System.getProperty("user.dir")).append(File.separator).append(dest).append(File.separator).append(entry.getName()).append("\n");
                FileOutputStream fos = new FileOutputStream(temp);
                int length;
                while ((length = zis.read(buffer)) > 0){
                    fos.write(buffer,0,length);
                }
                fos.close();
                entry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            return builder.toString();
        } catch (FileNotFoundException e) {
            throw new ZipperException("Failed to uncompress archive!");
        } catch (IOException e) {
            throw new ZipperException("Failed to uncompress archive!");
        }
    }
}
