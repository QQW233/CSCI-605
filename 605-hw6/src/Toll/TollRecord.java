package Toll;

import Exit.*;
/**
 * TollRecord class that is responsible to store all the information of a single toll event
 * The depart method need to be executed to complete one trip.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */

public class TollRecord implements TollsRUs,Comparable<TollRecord>{
    private final int arrival_time;
    private final int arrival_exit;
    //total toll for this trip
    private Double toll;
    //license plate tag
    private final String plate;
    private int departure_time;
    private int departure_exit;
    //speed of the vehicle, may be infinity
    private String speed;
    //whether or not this vehicle is a speeder
    private Boolean is_speeder;

    /**
     * A constructor of Toll record.
     * @param arrival_time arrival_time of the new toll record
     * @param plate license plate tag of the vehicle
     * @param arrival_exit exit the vehicle arrived at
     * @throws Exception throw exception if the arrival_exit is not valid.
     */
    public TollRecord(int arrival_time,String plate,int arrival_exit) throws Exception {
        if (!ExitInfo.isValid(arrival_exit))
            throw new Exception();
        this.arrival_time = arrival_time;
        this.arrival_exit = arrival_exit;
        this.plate = plate;
        this.departure_exit = -1;
        this.departure_time = -1;
        toll = 0.0;
        is_speeder = false;
    }

    /**
     * A depart method to finish a trip. This method will also calculate the speed of the vehicle and test
     * if it is a speeder.
     * @param departure_time time of departure
     * @param departure_exit from which gate the vehicle departed
     * @return total toll of this trip
     * @throws Exception throws an exception if the departure time is not valid or departure_exit is not valid.
     */
    public double depart(int departure_time, int departure_exit) throws Exception{
        int time_spent = departure_time - arrival_time;
        if (time_spent < 0 || !ExitInfo.isValid(departure_exit)) throw new Exception();
        this.departure_time = departure_time;
        this.departure_exit = departure_exit;
        toll = ExitInfo.getToll(arrival_exit,departure_exit);
        //To avoid division by zero error. Infinity velocity is logically impossible.
        if (time_spent == 0) {
            speed = "Infinity MpH";
            is_speeder = true;
        }
        else {
            double velocity = Math.abs(ExitInfo.getMileMarker(arrival_exit) - ExitInfo.getMileMarker(departure_exit)) /
                    (departure_time - arrival_time) * MINUTES_PER_HOUR;
            if (velocity > SPEED_LIMIT) is_speeder = true;
            speed = String.format(SPEED_FORMAT,velocity);}
        return  toll;
    }

    /**
     * An equals method to test. Return true only when all fields of two TollRecord Object is identical.
     * @param o An object to be tested
     * @return this equals to o or not
     */
    public boolean equals(Object o) {
        if (!(o instanceof TollRecord)) return false;
        return departure_time == ((TollRecord) o).departure_time &&
                departure_exit == ((TollRecord) o).departure_exit &&
                arrival_time == ((TollRecord) o).arrival_time &&
                arrival_exit == ((TollRecord) o).arrival_exit &&
                plate.equals(((TollRecord) o).plate);
    }

    /**
     * A to string method only used in unit test
     * @return A string representation of TollRecord.
     */
    public String toString(){
        if (departure_time == -1){
            return String.format("[%s]{(%d,%d)}",plate,arrival_exit,arrival_time);
        }
        else{
            return String.format("[%s]{(%d,%d),(%d,%d)}",
                    plate,arrival_exit,arrival_time,departure_exit,departure_time);
        }
    }

    /**
     * report the basic information of the object
     * @return A formatted String representation of Tollrecord
     */
    public String report(){
        if (departure_exit == -1) return String.format(INCOMPLE_TOLL_RECORD_FORMAT,
                plate,arrival_exit,arrival_time);
        else return String.format(COMPLETE_TOLL_RECORD_FORMAT,
                plate,arrival_exit,arrival_time,departure_exit,departure_time);
    }

    /**
     * report the billing information of the  Tollrecord
     * @return A formatted String representation of Tollrecord plus Billing information
     */
    public String reportToll(){
        if (departure_exit == -1) return String.format(INCOMPLE_TOLL_RECORD_FORMAT,
                plate,arrival_exit,arrival_time);
        else return String.format(COMPLETE_TOLL_RECORD_FORMAT,
                plate,arrival_exit,arrival_time,departure_exit,departure_time) + ": " +
                String.format(DOLLAR_FORMAT,toll);
    }

    /**
     * report speeder information
     * @return Only supposed to use when speeder is true. Will report detailed information about exits
     * the vehicle passed.
     */
    public String reportSpeeder(){
        return "Vehicle " + plate + ", starting at time " + arrival_time + NL +
                "  from " + ExitInfo.getName(arrival_exit) + NL +
                "  to " + ExitInfo.getName(departure_exit) + NL +
                "  " + speed;

    }

    /**
     * A hashcode generation method.
     * @return a hash code of TollRecord object
     */
    @Override
    public int hashCode(){
        return arrival_time + arrival_exit + departure_time + departure_exit + plate.hashCode();
    }

    /**
     * A compareTo method used in generating natural ordering of Tollrecord
     * @param o Another object of toll record
     * @return If one of the object is incomplete, it is considered smaller. After that it will compare the
     * license plate tag of two TollRecord objects. If two TollRecord objects have the same plate tag, this method
     * will compare their arrival time.
     */
    @Override
    public int compareTo(TollRecord o) {
        int result;
        if (this.departure_exit == -1 ^ o.departure_exit == -1)
            return o.departure_exit - this.departure_exit;
        result = this.plate.compareTo(o.plate);
        if (result == 0) result = arrival_time - o.arrival_time;
        return result;
    }

    /**
     *
     * A getter method of is_speeder
     * @return a boolean representation of whether the object is speeder
     */
    public boolean isSpeeder() {
        return  is_speeder;
    }

    /**
     * A getter method of toll
     * @return toll
     */
    public Double getToll() {
        return toll;
    }

    /**
     * A getter method of plate
     * @return plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * A getter method of arrival_exit
     * @return arrival_exit
     */
    public int getArrival_exit() {
        return arrival_exit;
    }

    /**
     * A getter method of Depature_exit
     * @return Depature_exit
     */
    public int getDeparture_exit() {
        return departure_exit;
    }
}
