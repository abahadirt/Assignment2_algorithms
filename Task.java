import java.time.LocalTime;

public class Task implements Comparable {
    public String name;
    public String start;
    public int duration;
    public int importance;
    public boolean urgent;

    /*
        Getter methods
     */
    public String getName() {
    	return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean isUrgent() {
        return this.urgent;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
    	// YOUR CODE HERE
        int hour=  Integer.parseInt(start.substring(0,2))+duration;
        if(hour <10) {
        	return 0+Integer.toString(hour)+(start.substring(3,5));
        }
        return Integer.toString(hour)+(start.substring(3,5));
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
        // YOUR CODE HERE
    	double weight = (this.importance * (urgent ? 2000 : 1))/this.duration;
    	
    	
    	
        return weight;

    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Object o) {
        // YOUR CODE HERE
    	int time1 = Integer.parseInt(this.getFinishTime().replace(":",""));
    	Task b= (Task) o; 
    	int time2 = Integer.parseInt(b.getFinishTime().replace(":",""));
    	if(time1 > time2) {
    		return 1;
    	}

    	if(time1 == time2) {
    		return 0;
    	}
    	
    	

    	
        return -1;
    }
}
