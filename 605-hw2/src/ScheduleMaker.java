import static java.lang.System.exit;

public class ScheduleMaker {

    static final int ADD_CLASS = 1;
    static final int REMOVE_CLASS = 2;
    static final int SHOW_SCHED = 3;
    static final int QUIT = 4;

    static final String INVALID_OPTION = "Invalid Option";
    private CourseList courselist;
    private Schedule schedule;
    private static java.util.Scanner in = new java.util.Scanner(System.in);

    public ScheduleMaker(String filename) {
        courselist = new CourseList(filename);
        schedule = new Schedule();
    }

    public void addClass() {
        System.out.println("Here are the courses and meeting times: ");
        System.out.println(courselist.toString());
        System.out.println("What class would you like to add?");
        int input = in.nextInt();
        in.nextLine();
        if (schedule.fits(courselist.getClass(input))){
            schedule.add(courselist.getClass(input));
            System.out.println("Course section successfully added.");
        }
        else if (schedule.contains(courselist.getClass(input))) {
            System.out.println("That section is already on your schedule.");
        }
        else if (schedule.contains((courselist.getClass(input)).getCourse())){
            System.out.println("Another section of that course is already on your schedule.");
        }
        else {
            System.out.println("Time for this course conflicts with others on your schedule");
        }

    }

    public void removeClass() {
        System.out.println("This is your current schedule: ");
        schedule.show();
        System.out.println("What class would you like to remove (enter the course's name)?");
        String input = in.nextLine();
        if(schedule.contains(input)){
            if (schedule.remove(input)) {
                System.out.println("Course successfully deleted.");
            } else {
                System.out.println("Couldn't delete the course");
            }
        }
        else {
            System.out.println("There is no course with that name in your schedule.");
        }

    }

    public void printMenu() {
        System.out.println("Choices are");
        System.out.println("1. Find a class to add to your schedule");
        System.out.println("2. Remove a class from your schedule");
        System.out.println("3. Print your schedule");
        System.out.println("4. Quit");
        System.out.println("What would you like to do?");

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java ScheduleMaker course-list-file");
            exit(0);
        }
        ScheduleMaker schedulemaker = new ScheduleMaker(args[0]);
        System.out.println("Welcome to Schedule Maker!");
        schedulemaker.printMenu();
        int input = in.nextInt();
        in.nextLine();
        while (input != QUIT){
            switch (input) {
                case ADD_CLASS -> schedulemaker.addClass();
                case REMOVE_CLASS -> schedulemaker.removeClass();
                case SHOW_SCHED -> schedulemaker.schedule.show();
                default -> System.out.println(INVALID_OPTION);
            }
            schedulemaker.printMenu();
            input = in.nextInt();
            in.nextLine();
        }

        System.out.println("Thanks for using Schedule Maker.");
    }


}
