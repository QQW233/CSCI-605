package Toll;
import Exit.ExitInfo;
import java.util.*;
/**
 * TollRoadDatabase takes a filename and read the file to generate all toll information.
 * It has different collections to handle different possible need of the user including
 * look up billing information of certain plate tag or at certain exit.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class TollRoadDatabase implements TollsRUs{
    //A hash map for exit lookup
    private HashMap<Integer, TreeSet<TollRecord>> exit_database;
    //A hash map for plate tag lookup
    private HashMap<String, TreeSet<TollRecord>> plate_database;
    //A treeSet of all completed trips
    private TreeSet<TollRecord> completed_trips;
    //A treeMap of all trips in progress
    private TreeMap<String,TollRecord> incomplete_trips;
    //A treeset of all speeder record.
    private TreeSet<TollRecord> speeders;

    /**
     * A constructor that will read all needed information from given file
     * @param filename a filename to toll record data
     * @throws Exception throws exception if the file is not valid.
     */
    public TollRoadDatabase (String filename) throws Exception {
        String[] event;
        TollRecord temp;
        exit_database = new HashMap<>();
        plate_database = new HashMap<>();
        completed_trips = new TreeSet<>();
        incomplete_trips = new TreeMap<>();
        speeders = new TreeSet<>();
        String curr_plate;
        int curr_exit;
        for (String line : FileHandler.open(filename)) {
            event = line.trim().split(",");
            //test if the given license tage is already on trip
            if (incomplete_trips.get(event[1]) == null) {
                incomplete_trips.put(event[1],
                        new TollRecord(Integer.parseInt(event[0]), event[1], Integer.parseInt(event[2])));
            } else {
                //complete the running trip, and move it to completed trips
                temp = incomplete_trips.get(event[1]);
                temp.depart(Integer.parseInt(event[0]), Integer.parseInt(event[2]));
                completed_trips.add(temp);
                incomplete_trips.remove(event[1]);
                //test if the vehicle is a speeder.
                if (temp.isSpeeder()) speeders.add(temp);
            }
        }
        //Add all completed trips into plate_database for future lookup
        for (TollRecord completed_trip : completed_trips) {
            curr_plate = completed_trip.getPlate();
            plate_database.computeIfAbsent(curr_plate, k -> new TreeSet<>());
            plate_database.get(curr_plate).add(completed_trip);
        }
        //Add all completed trips into exit_database for future lookup
        for (TollRecord completed_trip : completed_trips) {
            curr_exit = completed_trip.getArrival_exit();
            exit_database.computeIfAbsent(curr_exit, k -> new TreeSet<>());
            exit_database.get(curr_exit).add(completed_trip);
            curr_exit = completed_trip.getDeparture_exit();
            exit_database.computeIfAbsent(curr_exit, k -> new TreeSet<>());
            exit_database.get(curr_exit).add(completed_trip);
        }
        //Add all incomplete trips into exit_database
        Iterator <Map.Entry<String,TollRecord>> tollRecordIterator = incomplete_trips.entrySet().iterator();
        while (tollRecordIterator.hasNext()){
            temp = tollRecordIterator.next().getValue();
            curr_exit = temp.getArrival_exit();
            exit_database.computeIfAbsent(curr_exit, k -> new TreeSet<>());
            exit_database.get(curr_exit).add(temp);
        }
        //Indicate the sum of completed trips.
        System.out.println(NL + completed_trips.size() + " completed trips");
    }

    /**
     *Print the billing information of all completed trips and their total toll
     */
    public void reportCompletedTrips(){
        System.out.println(NL + "BILLING INFORMATION" + NL + "===================");
        double total_toll = 0.0;
        for (TollRecord completed_trip : completed_trips) {
            System.out.println(completed_trip.reportToll());
            total_toll += completed_trip.getToll();
        }
        System.out.println("Total: " + String.format(DOLLAR_FORMAT,total_toll) + NL);
    }

    /**
     * Print the information of all on-going trips.
     */
    public void reportIncompletedTrips(){
        System.out.println(NL + "On-Road Report" + NL + "==============");
        Iterator <Map.Entry<String,TollRecord>> tollRecordIterator = incomplete_trips.entrySet().iterator();
        while (tollRecordIterator.hasNext()){
            System.out.println(tollRecordIterator.next().getValue().report());
        }
        System.out.println();
    }

    /**
     * Print the information of all speeders.
     */
    public void reportSpeeders(){
        System.out.println(NL + "SPEEDER REPORT" + NL + "==============");
        for (TollRecord speeder : speeders){
            System.out.println(speeder.reportSpeeder());
        }
    }

    /**
     * Print the information of given plate tag, and its total toll.
     * @param plate the plate tag to look for
     */
    public void reportPlate(String plate){
        double total_toll = 0.0;
        if(plate_database.get(plate) == null) {
            System.out.println(NL + "Vehicle total due: " + String.format(DOLLAR_FORMAT,total_toll) + NL);
            return;
        }
        for(TollRecord completed_trip : plate_database.get(plate)){
            total_toll += completed_trip.getToll();
            System.out.println(completed_trip.reportToll());
        }
        System.out.println(NL + "Vehicle total due: " + String.format(DOLLAR_FORMAT,total_toll) + NL);
    }

    /**
     * Print all the activity at given exit.
     * @param exit the exit to look for
     */
    public void reportExit(int exit){
        if(exit < 0 || exit > ExitInfo.LAST_EXIT){
            System.out.println("Exit does not exist.");
            return;
        }
        System.out.println(NL + "EXIT " + exit + " REPORT" + NL + "==============");
        if(exit_database.get(exit) == null) {
            System.out.println();
            return;
        }
        for(TollRecord trip : exit_database.get(exit)){
            System.out.println(trip.report());
        }
        System.out.println();
    }
}
