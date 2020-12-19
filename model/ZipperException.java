package model;


/**
 * @author Ashesh Piyush Sheth, as2462@rit.edu
 * @author Herat Alkeshkumar Patel, hp9198@rit.edu
 */

/**
 * Custom Exception for Zipping and Unzipping
 */
public class ZipperException extends Exception {

    /**
     * Constructor used when another exception is handled and passed as Throwable in custom exception
     * @param msg Custom message
     * @param error Throwable
     */
    public ZipperException(String msg, Throwable error){super(msg, error);}

    /**
     * Cunstructor used when custom exception is created
     * @param msg Custom message
     */
    public ZipperException(String msg) {
        super(msg);
    }
}
