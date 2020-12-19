package view;

import controller.ZipperController;
import model.ZipperException;

import java.util.List;
import java.util.Scanner;

/**
 * @author Ashesh Piyush Sheth, as2462@rit.edu
 * @author Herat Alkeshkumar Patel, hp9198@rit.edu
 */

/**
 *  Main class of the View of the MVC. Gets command from the user and prints the result.
 */
public class ZipperUtility {
    private ZipperController enterController;

    /**
     * Creates an object of ZipperController to enter the controller of the MVC
     */
    public ZipperUtility(){
       enterController = new ZipperController();
    }

    /**
     * The program runs in a loop to get input from the user and print the output.
     */
    private void runCommands(){
        while(true){
            System.out.print("enter command >> ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] cmd = input.split(" ");

            try {
                List<String> output = enterController.checkCommand(cmd);
                for(String out : output){
                    System.out.println(out);
                }
            }
            catch (ZipperException ze){
                System.out.println(ze.getMessage());
                if(ze.getCause()!=null){
                    ze.printStackTrace();
                }
            }
            catch (NullPointerException ne){
                System.out.println("Quitting...");
                System.exit(0);
            }
        }
    }

    /**
     * Creates an object of this class and calls the runCommand method for execution of the commands
     * @param args
     */
    public static void main(String[] args){
        ZipperUtility zipUnzip = new ZipperUtility();
        zipUnzip.runCommands();
    }
}
