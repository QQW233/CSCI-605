package hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * Schedule class is used to maintain the user's current schedule and perform relevant operations.
 * 
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Schedule {

    /** classes records the class that is currently on the user's schedule. */
    private List<Section> classes;

    /**
     * Constructor. Initialize the field classes.
     */
    public Schedule(){
        classes = new ArrayList<>();
    }

    /**
     * contains checks if a specific class is already on the user's schedule according to the class's name.
     * @param courseName the name of the course to be checked.
     * @return whether or not the course already exists on the schedule. If so, return true.
     */
    public boolean contains(String courseName){
        for(int i=0; i < classes.size(); i++)
            if(classes.get(i).getCourse().equals(courseName))
                return true;
        return false;
    }

    /**
     * contains checks if a specific section is already on the user's schedule according to the section object passed.
     * @param section the specific section to be checked.
     * @return whether or not that specific section is on the user's schedule. If so, return true.
     */
    public boolean contains(Section section){
        for(int i=0; i < classes.size(); i++)
            if(classes.get(i).equals(section))
                return true;
        return false;
    }

    /**
     * isEmpty checks if the current schedule contains no class.
     * @return whether or not the current schedule is empty. If so, return true.
     */
    public boolean isEmpty(){
        if(classes.size() == 0)
            return true;
        return false;
    }

    /**
     * fits checks if a specific section fits in the current schedule, which means that it doesn't conflict in time with any class that is already on the user's schedule.
     * @param s the section to be checked.
     * @return whether or not the specific section fits the current schedule. If so, return true.
     */
    public boolean fits(Section s){
        for(int i=0; i < classes.size(); i++)
            if(s.inConflict(classes.get(i)))
                return false;
        return true;
    }

    /**
     * add adds a specific section to the current schedule.
     * @param s the section to be added to the schedule.
     */
    public void add(Section s){
        classes.add(s);
    }

    /**
     * remove attempts to remove a specific class from the current schedule. It will check if the remove option is legal. If legal, the class will be removed from the schedule.
     * @param course name of the course that is to be removed.
     * @return whether or not the remove action is successful. If so, return true.
     */
    public boolean remove(String course){
        for(int i=0;i < classes.size();i++)
            if(classes.get(i).getCourse().equals(course)){
                classes.remove(i);
                return true;
            }
        return false;
    }

    /**
     * toString generates a String that contains the information of the hw2.Schedule object.
     * @return A String that contains information about the hw2.Schedule object.
     */
    public String toString(){
        return "hw2.Schedule with " + classes.size() + " classes";
    }

    /**
     * show method displays the current schedule using system output.
     */
    public void show(){
        System.out.print("\nThis is your current schedule:\n\n");
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for(int i=0; i < 5; i++){
            System.out.println("----" + weekdays[i] + "----");
            for(int j=0; j < classes.size(); j++){
                System.out.print(classes.get(j).meetingTime(i));
            }
        }
    }

}
