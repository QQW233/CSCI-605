import java.util.List;

public class Section {
    private String name;

    public Section(String name, List<Boolean> days, int start, int end){
        this.name = name;
    }

    public String getCourse(){
        return "CSCI 605";
    }

    public String toString(){
        return name;
    }
}
