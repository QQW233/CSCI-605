import static java.lang.System.exit;
import java.util.Scanner;

/**
 * hw2.ScheduleMaker contains a courselist of all courses available and user's current schedule.
 * It can add or remove classes from courselist to schedule or show current schedule.
 * SchduleMaker also contains a main function that will take command line arguments
 * and do user desired task.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */

public class ScheduleMaker {

    /** Menu option number for adding a class. */
    private static final int ADD_CLASS = 1;
    /** Menu option number for removing a class. */
    private static final int REMOVE_CLASS = 2;
    /** Menu option number for showing the current schedule. */
    private static final int SHOW_SCHED = 3;
    /** Menu option number to quit the program. */
    private static final int QUIT = 4;
    /** Error message displayed when user inputs an invalid option. */
    private static final String INVALID_OPTION = "Invalid option";
    /** List of the available courses. */
    private CourseList courselist;
    /** The current schedule, containing some sessions. */
    private Schedule schedule;
    /** Scanner to read user input. */
    private Scanner in;

    /**
     * A constructor for hw2.ScheduleMaker
     *
     * @param filename a filename of a txt file containing the courselist
     *
     * filename should be under the same directory where the program is run
     * courselist will be invalid if the file is not proper documented.
     */
    public ScheduleMaker(String filename) {
        courselist = new CourseList(filename);
        schedule = new Schedule();
        in = new Scanner(System.in);
    }

    /**
     * A function to interact with the user when user want to add a class.
     *
     * it will print the courselist first using courselist.toString()
     * then it will take the next user input int and try to add the
     * section with that index to schedule. It will successfully add the
     * section if there's no other section with the same name and the section
     * doesn't have time conflict with other sections.
     */
    public void addClass() {
        System.out.println("Here are the courses and meeting times: ");
        //try to print the courselist
        System.out.println(courselist.toString());
        System.out.println("What class would you like to add?");
        //takes next user input int
        int input;
        if (this.in.hasNextInt()) input = this.in.nextInt();
        else {
            this.in.nextLine();
            System.err.println("Please enter a number");
            return;
            }
        in.nextLine();
        //tests if the course is already in courselist
        if (input >= courselist.getSize() || input < 0){
            System.err.println("Class index out of bound.");
        }
        else if (schedule.contains(courselist.getClass(input))) {
            System.out.println("That section is already on your schedule.");
        }
        //tests if a course with the same name already exists in the schedule
        else if (schedule.contains((courselist.getClass(input)).getCourse())){
            System.out.println("Another section of that course is already on your schedule.");
        }
        //tests if there is time conflict
        else if (!schedule.fits(courselist.getClass(input))){
            System.out.println("Time for this course conflicts with others on your schedule");
        }
        //add the course to courselist
        else {
            schedule.add(courselist.getClass(input));
            System.out.println("Course section successfully added.");
        }

    }

    /**
     * A function to interact with the user when user want to remove a class.
     *
     * it will print the schedule first using schedule.toString()
     * then it will reader's input and try to remove a course with the
     * same name from the schedule. Fails if there is no such course.
     */
    public void removeClass() {
        if(schedule.isEmpty()){
            System.out.println("You do not have any class on schedule yet.");
            return;
        }
        System.out.print("This is your current schedule:\n\n");
        schedule.show();
        System.out.println("\nWhat class would you like to remove (enter the course's name)?");
        //reads the next line
        String input = this.in.nextLine();
        //tests if the section exists in schedule
        if(schedule.contains(input)){
            if (schedule.remove(input)) {
                System.out.println("Course successfully deleted.");
            } else {
                System.out.println("Couldn't delete the course");
            }
        }
        //fail message
        else {
            System.out.println("There is no course with that name in your schedule.");
        }

    }

    /**
     * A function to print the menu.
     *
     * printMenu() only print the menu, user input should be handled by
     * other function.
     */
    public void printMenu() {
        System.out.println("\nChoices are");
        System.out.println("1. Find a class to add to your schedule");
        System.out.println("2. Remove a class from your schedule");
        System.out.println("3. Print your schedule");
        System.out.println("4. Quit");
        System.out.println("What would you like to do?");

    }

    /**
     * main method that uses the command line argument to create a new schedulemaker
     * it will ask the user which action to take and use the schedulemaker to execute.
     *
     * @param args - should be a filename that directs to a file containing the courselist
     * under the running directory.
     *
     * main will send error message when args is empty.
     */
    public static void main(String[] args) {
        //tests if the args is empty
        if (args.length == 0) {
            System.err.println("Usage: java hw2.ScheduleMaker course-list-file");
            exit(0);
        }
        //creates a new schedulemaker using the given filename
        ScheduleMaker schedulemaker = new ScheduleMaker(args[0]);
        //print menu for the first time
        System.out.println("Welcome to hw2.Schedule Maker!");
        schedulemaker.printMenu();
        //read next int
        int input;
        //execute until the user input is 4
        while (schedulemaker.in.hasNext()){
            // checks if the input is valid
            if(schedulemaker.in.hasNextInt()){
                input = schedulemaker.in.nextInt();
                schedulemaker.in.nextLine();
                if (input == QUIT) break;
                //call schedulemaker functions to perform the asked action
                switch (input) {
                    case ADD_CLASS -> schedulemaker.addClass();
                    case REMOVE_CLASS -> schedulemaker.removeClass();
                    case SHOW_SCHED -> schedulemaker.schedule.show();
                    default -> System.out.println(INVALID_OPTION);
                }
                schedulemaker.printMenu();
            }
            else{
                System.err.println("Please enter a number.");
                schedulemaker.in.nextLine();
                schedulemaker.printMenu();
            }
        }
        //exiting message
        System.out.println("Thanks for using hw2.Schedule Maker.");
    }


}
