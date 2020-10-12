package Model;

/**
 * ZipperException handles all possible exceptions in Zipper and Unzipper class.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */

public class ZipperException extends Exception {
    /**
     * A constructor of Zipper Exception
     * @param errorMessage errorMessage to pass
     */
    public ZipperException(String errorMessage){
        super(errorMessage);
    }
}
