import java.util.List;

/**
 * Section is used to store the information of a specific class section and perform the relevant operations.
 * 
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Section {

    /** represents days from Monday to Friday, each with a single capital letter. */
    public static final String DAYSTRING = "MTWRF";
    /** name of the class section. */
    private String name;
    /** records on which days the class section takes place. It is a List of length 5 and use true to represent that this class takes place on that specific day and false if not. */
    private List<Boolean> meetDays;
    /** startTime of the class section */
    private int startTime;
    /** endTime of the class section */
    private int endTime;

    /**
     * Constructor, it constructs a Section object and fills the corresponding fields with given value.
     * @param name name of the class section.
     * @param days on which days this class takes place. Represented as a List of Boolean of length 5.
     * @param start start time of the class section.
     * @param end end time of the class section.
     */
    public Section(String name, List<Boolean> days, int start, int end){
        this.name = name;
        this.meetDays = days;
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * checks if another object is the same as this Section object
     * @param other the object to be checked
     * @return whether or not the given object is the same as this one. If so, return true.
     */
    public boolean equals(Object other){
        // First check class type, then check each field.
        if (!(other instanceof Section))
            return false;
        if (!this.name.equals(((Section) other).name))
            return false;
        if(this.startTime != ((Section) other).startTime || this.endTime != ((Section) other).endTime)
            return false;
        if(!this.meetDays.equals(((Section) other).meetDays))
            return false;
        return true;
    }

    /**
     * checks if another section has time conflicts with this section.
     * @param other the other section to be checked.
     * @return whether or not there is a time conflict. If so, return true.
     */
    public boolean inConflict(Section other){
        for(int i=0; i < 5; i++) {
            // for each day, check if two sections take place at overlapping time.
            if (other.meetDays.get(i) && this.meetDays.get(i) && !(other.startTime > this.endTime || other.endTime < this.startTime))
                return true;
        }
        return false;
    }

    /**
     * generates a String that contains the information of the section object.
     * @return A String that contains information about the section object.
     */
    public String toString(){
        String meetDayStr = "";
        for(int i=0; i < 5; i++)
            if(this.meetDays.get(i))
                meetDayStr += DAYSTRING.charAt(i);
        return this.name + ": " + meetDayStr + " at " + startTime + "-" + this.endTime;
    }

    /**
     * returns a String that contains information of the meeting time on a specific day of this section.
     * @param day the day on which the meeting time of this section is being returned.
     * @return a String that contains the meeting time information of this section on a specific day. If this section doesn't take place on that day, an empty String will be returned.
     */
    public String meetingTime(int day){
        if(this.meetDays.get(day) == true)
            return this.startTime + "-" + this.endTime + ": " + this.name + "\n";
        return "";
    }

    /**
     * Get the name of this class section.
     * @return the name of this class section.
     */
    public String getCourse(){
        return this.name;
    }

}
