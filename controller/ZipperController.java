package controller;

import model.UnZipper;
import model.Zipper;
import model.ZipperException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ashesh Piyush Sheth, as2462@rit.edu
 * @author Herat Alkeshkumar Patel, hp9198@rit.edu
 */

/**
 * Controller class of the MVC. Decides which class and method to call from the model and returns the output to the view.
 */
public class ZipperController {
    private Zipper newZip;
    private UnZipper newUnzip;

    /**
     * Creates objects of the model classes to further call their methods
     */
    public ZipperController(){
        newZip = new Zipper();
        newUnzip = new UnZipper();
    }

    /**
     * Calls the Zipper method addFile to add the file to the list of archives
     * @param newFile name of file
     * @return Statement that the file has been added
     * @throws ZipperException exception thrown by Zipper method addFile
     */
    public List<String> addFile(String newFile) throws ZipperException {
        newZip.addFile(newFile);
        List<String> contents = new ArrayList<>();
        contents.add(newFile + " added to list of files to be archived.");
        return contents;
    }

    /**
     * Calls the Zipper method archiveFiles to get the list of files added to be archived
     * @param zipName name of the zip to created
     * @return Statement that archive has been created
     * @throws ZipperException exception thrown by Zipper method archiveFiles
     */
    public List<String> archiveFiles(String zipName) throws ZipperException {
        long size = newZip.archiveFiles(zipName);
        List<String> contents = new ArrayList<>();
        contents.add("Archive successfully created: " + zipName + "(" + size + " bytes)");
        return contents;
    }

    /**
     * Calls the Zipper method clearList to clear the list of files added to be archived
     * @return Statement that the list has been cleared
     */
    public List<String> clearList(){
        newZip.clearList();
        List<String> contents = new ArrayList<>();
        contents.add("Files to be archived cleared.");
        return contents;
    }

    /**
     * Calls the Zipper method listArchives to get the list of files to be archived
     * @return List of Files to be archived
     * @throws ZipperException exception thrown by Zipper method
     */
    public List<String> listArchives() throws ZipperException{
        List<String> contents = newZip.listArchives();
        contents.add(0, "Files to be archived:");
        return contents;
    }

    /**
     * Calls the Zipper method listContent to get the list of files and directories in a specific directory
     * @param directory Name of the directory
     * @return List of files and directories
     * @throws ZipperException exception thrown by Zipper method
     */
    public List<String> listContent(String directory) throws ZipperException {
        List<String> contents = newZip.listContent(directory);
        contents.add(0, "Listing files in ’" + directory + "’...");
        return contents;
    }

    /**
     * Calls the Zipper method unarchiveFiles to unarchive the zip file into a specific folder
     * @param src source file
     * @param dest directory to be extracted into
     * @return List of files extracted
     */
    public List<String> unarchiveFiles(String src, String dest){
        List<String> contents = new ArrayList<>();
        try{
            contents.add("Extracting the archive '" + src +  "' and saving entries in directory '" + dest + "'...");
            newUnzip.unarchiveFiles(src, dest);
            contents.addAll(newZip.listContent(dest));
        }
        catch (ZipperException ze){
            contents.add(ze.getMessage());
        }
        return contents;
    }

    /**
     * Checks the command and calls the methods accordingly
     * @param cmd command
     * @return output received from model
     * @throws ZipperException exception thrown by model classes
     */
    public List<String> checkCommand(String[] cmd) throws ZipperException {
        List<String> out = new ArrayList<>();
        if(cmd[0].equals("help"))
        {
            if(cmd.length == 1)
            {
                out.add("Enter one of the following commands:\n" +
                        "  add <file path> - adds file indicated by the path to the collection of files to archive.\n" +
                        "  archive <file path> - writes the collection of added files to the file indicated by the path.\n" +
                        "  clear - clears the current list of files to be archived.\n" +
                        "  files - prints the current list of files to be archived.\n" +
                        "  list <directory> - lists the files in the specified directory\n" +
                        "  quit - quits the Zipper Utility.\n" +
                        "  unarchive <source> <destination> - extracts the archive specified by source and saves the extracted entries in the destination directory.");
                return out;
            }
            else
                throw new ZipperException("Error processing most recent command!\n" +
                        "Invalid arguments.");
        }
        else if(cmd[0].equals("clear"))
        {
            if(cmd.length == 1)
                return clearList();
            else
                throw new ZipperException("Error processing most recent command!\n" +
                    "Invalid arguments.");
        }
        else if(cmd[0].equals("files")){
            if(cmd.length == 1)
                return listArchives();
            else
                throw new ZipperException("Error processing most recent command!\n" +
                        "Invalid arguments.");
        }
        else if(cmd[0].equals("quit")){
            if(cmd.length == 1)
                return null;
            else
                throw new ZipperException("Error processing most recent command!\n" +
                        "Invalid arguments.");
        }
        else if(cmd[0].equals("add")){
            if(cmd.length == 2)
                return addFile(cmd[1]);
            else
                throw new ZipperException("Error processing most recent command!\n" +
                        "Invalid arguments.");
        }
        else if(cmd[0].equals("archive"))
        {
            if(cmd.length == 2)
                return archiveFiles(cmd[1]);
            else
                throw new ZipperException("Error processing most recent command!\n" +
                        "Invalid arguments.");
        }
        else if(cmd[0].equals("list"))
        {
            if(cmd.length == 2)
                return listContent(cmd[1]);
            else
                throw new ZipperException("Error processing most recent command!\n" +
                        "Invalid arguments.");
        }
        else if(cmd[0].equals("unarchive")){
            if(cmd.length == 3)
                return unarchiveFiles(cmd[1], cmd[2]);
            else
                throw new ZipperException("Error processing most recent command!\n" +
                    "Invalid arguments.");
        }
        else{
            throw new ZipperException("Unrecognized command: " + cmd[0] + "\n" +
                    "Use 'help' to display a list of commands.");
        }

    }

    public Zipper getNewZip() {
        return newZip;
    }

    public UnZipper getNewUnzip() {
        return newUnzip;
    }
}
