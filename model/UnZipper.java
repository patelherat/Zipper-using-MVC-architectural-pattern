package model;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * @author Ashesh Piyush Sheth, as2462@rit.edu
 * @author Herat Alkeshkumar Patel, hp9198@rit.edu
 */

/**
 * Unarchives files to specified destination
 */
public class UnZipper {
    private static final int BUFFER_SIZE = 4096;

    /**
     * Empty constructor
     */
    public UnZipper(){ }

    /**
     * Unarchives the source file into the dest directory
     * @param src from where to unarchive files
     * @param dest where to store the unarchived files
     * @throws ZipperException if failed to uncompress archive
     */
    public void unarchiveFiles(String src, String dest) throws ZipperException {
        File srcFile = new File(src);
        File destDir = new File(dest);
        if(!srcFile.exists() || srcFile.isDirectory() || destDir.isFile()){
            throw new ZipperException("Error processing most recent command!\n" +
                    "Failed to uncompress archive!");
        }
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(src))) {
            ZipEntry newEntry = zis.getNextEntry();
            while (newEntry != null) {
                String newFile = dest + "/" + newEntry.getName();
                if (!newEntry.isDirectory()) {
                    extractFile(zis, newFile);
                } else {
                    throw new ZipperException("Error processing most recent command!\n" +
                            "Failed to uncompress archive!\n" +
                            "Directory '" + newEntry.getName() + "' found inside zip, which is not a feature of the homework");
                }
                zis.closeEntry();
                newEntry = zis.getNextEntry();
            }
        }
        catch (IOException ie){
            throw new ZipperException("Error processing most recent command!\n" +
                    "Failed to uncompress archive!", ie);
        }
    }

    /**
     * Extract each file in the directory
     * @param zis ZipInputStream
     * @param newFile each file's name
     * @throws IOException
     */
    private void extractFile(ZipInputStream zis, String newFile) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
        byte[] bytesInput = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zis.read(bytesInput)) != -1) {
            bos.write(bytesInput, 0, read);
        }
        bos.close();
    }
}
