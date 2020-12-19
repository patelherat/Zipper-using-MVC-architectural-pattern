package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @author Ashesh Piyush Sheth, as2462@rit.edu
 * @author Herat Alkeshkumar Patel, hp9198@rit.edu
 */

/**
 * Creates archive file using list of files added. Lists content of directory for archiving.
 */
public class Zipper {

    private List<File> archive;


    /**
     * Creates List to store path of files to be archived
     */
    public Zipper(){
        archive = new ArrayList<>();
    }


    /**
     * Gives list of files in specified directory
     * @param directory for which files are to be specified
     * @return files
     * @throws ZipperException if path is not a directory
     */
    public List<String> listContent(String directory) throws ZipperException{
        List<String> contents = new ArrayList<>();
        File newFile = new File(Paths.get(directory).toAbsolutePath().toString());
        if(newFile.isDirectory()){
            File[] contentFiles = newFile.listFiles();
            for(File eachFile : contentFiles){
                contents.add("\t" + eachFile.getAbsolutePath());
            }
            return contents;
        }
        else{
            throw new ZipperException("Error processing most recent command!\n" +
                    "Path is not a directory: " + directory);
        }
    }

    /**
     * Adds file in archive list
     * @param newFile file added to list
     * @throws ZipperException if it's a directory or file does not exist
     */
    public void addFile(String newFile) throws ZipperException{
        File archiveFile = new File(Paths.get(newFile).toAbsolutePath().toString());
        if(archiveFile.isFile()){
            archive.add(archiveFile);
        }
        else if(archiveFile.isDirectory()){
            throw new ZipperException("Error processing most recent command!\n" +
                    "File is a directory: " + archiveFile.getAbsolutePath());
        }
        else{
            throw new ZipperException("Error processing most recent command!\n" +
                    "File does not exist: " + archiveFile.getAbsolutePath());
        }
    }

    /**
     * Stores list of files to be archived
     * @return files to be archived
     * @throws ZipperException if there are no files to be archived
     */
    public List<String> listArchives() throws ZipperException{
        if(archive.isEmpty()){
            throw new ZipperException("There are no files to be archived. Use the add command to add a file.");
        }
        List<String> files = new ArrayList<>();
        for(File archiveFiles : archive){
            files.add("\t" + archiveFiles.getAbsolutePath());
        }
        return files;
    }

    /**
     * Clears the list to be archived
     */
    public void clearList(){
        archive.clear();
    }

    /**
     * Archives the file
     * @param zipName Name of the zip file to be created
     * @return number of files to be zipped
     * @throws ZipperException if the file is not zip or there are no files to be archived
     */
    public long archiveFiles(String zipName) throws ZipperException {
        if(zipName.length()<5 || (zipName.length()>4 && !zipName.substring(zipName.length()-4).equals(".zip"))){
            throw new ZipperException("Error processing most recent command!\n" +
                    "File should be a zip file like 'temp.zip'");
        }
        File fileExists = null;
        if(archive.isEmpty()){
            throw new ZipperException("Error processing most recent command!\n" +
                    "There are no files to be archived. Use the add command to add a file.");
        }
        long size = 0;
        try(FileOutputStream fos = new FileOutputStream(zipName);
            ZipOutputStream zos = new ZipOutputStream(fos)){


            for (File files : archive) {
                fileExists = files;
                zos.putNextEntry(new ZipEntry(files.getName()));

                byte[] bytesOutput = Files.readAllBytes(Paths.get(files.getAbsolutePath()));
                zos.write(bytesOutput, 0, bytesOutput.length);
                size += bytesOutput.length;
                zos.closeEntry();
            }
            return size;
        }
        catch (IOException ie){
            throw new ZipperException("Error processing most recent command!\n" +
                    "File does not exist: " + fileExists.getAbsolutePath(), ie);
        }
    }

    /**
     * Getter method for Archive
     * @return archive
     */
    public List<File> getArchive() {
        return archive;
    }
}
