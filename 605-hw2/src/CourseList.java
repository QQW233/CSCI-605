import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseList {
    private List<Section> sections;

    public CourseList(String filename) {
        sections = new ArrayList<Section>();
        File courses = new File (filename);
        try {
            Scanner reader = new Scanner(courses);
            while (reader.hasNextLine()){
                String[] data = (reader.nextLine()).split(" ");
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

    public Section getClass(int cnum){
        return sections.get(cnum);
    }

    public String toString(){
        String result = "";
        int i = 0;
        while (i < sections.size()){
            result += i + ": " + getClass(i).toString() +"\n";
            i++;
        }
        return result;
    }
}
