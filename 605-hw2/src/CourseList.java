import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Courselist contains a list of sections available for the user to pick from.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 */

public class CourseList {
    private List<Section> sections;

    /**
     * CourseList constructor function that creates a course list from given file
     * @param filename a filename directs to a proper documented txt file containing course information
     *
     * The given file is supposed to be documented, otherwise the sections created will not be viable.
     * the file should be under the same directory as will the program is run
     */
    public CourseList(String filename) {
        sections = new ArrayList<Section>();
        //try to open the given file
        File courses = new File (filename);
        try {
            //create a file scanner
            Scanner reader = new Scanner(courses);
            //while the scanner didn't reach the end of the file
            while (reader.hasNextLine()){
                //using white space as deliminator and store strings in data
                String[] data = (reader.nextLine()).split(" ");
                //add a new section with given parameters in txt file
                sections.add(new Section(data[0],
                        List.of(data[1].contains("M"),data[1].contains("T"),
                        data[1].contains("W"),data[1].contains("R"),
                        data[1].contains("F")),
                        Integer.parseInt(data[2]),Integer.parseInt(data[3])));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * getClass is a getter that returns the section with given index
     * @param cnum a index of the section the user want to obtain
     * @return the section with given index
     * cnum is supposed to be within the range of sections list, otherwise this function will fail.
     */
    public Section getClass(int cnum){
        return sections.get(cnum);
    }

    /**
     * toString creates a string representation of the sections
     * @return A string representation of the sections.
     * This function relies on getClass(int) and section.toString().
     */
    public String toString(){
        //initialize an empty string
        String result = "";
        int i = 0;
        //iterate through sections
        while (i < sections.size()){
            //add string representations of all sections using section.toString() to result.
            result += i + ": " + getClass(i).toString() +"\n";
            i++;
        }
        return result;
    }
}
