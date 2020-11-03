package hw9;

/**
 * Woolie represents a single woolie object waiting to get across a bridge.
 *
 * @author Qiwen Quan, qq5575@g.rit.edu
 * @author Steve Gao, sg2369@rit.edu
 */
public class Woolie extends Thread{
    private final String name;
    private final long crossingTime;
    private final String destination;
    private final TrollsBridge bridge;

    /**
     * A Woolie constructor. Initialize the fields.
     *
     * @param name  name of the woolie
     * @param time crossing time the woolie would spend to cross the bridge
     * @param destination destination city
     * @param bridge the bridge woolie is trying to cross
     */
    public Woolie (String name,long time,String destination,TrollsBridge bridge){
        this.name = name;
        this.crossingTime = time;
        this.destination = destination;
        this.bridge = bridge;
    }

    /**
     * run will try to get woolie onto the bridge,
     * cross the bridge and get off the bridge.
     */
    @Override
    public void run() {
        bridge.enterBridgePlease(this);
        try {
            for (int i = 1; i <= crossingTime; i++){
                Thread.sleep(1000);
                System.out.println(String.format("\t%s %d seconds",name,i));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bridge.leave(this);
    }

    public String woolieName(){
        return this.name;
    }

    public String getDestination(){
        return this.destination;
    }
}
