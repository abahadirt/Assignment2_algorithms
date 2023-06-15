import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Planner {

    public final Task[] taskArray;
    public final Integer[] compatibility;
    public final Double[] maxWeight;
    public final ArrayList<Task> planDynamic;
    public final ArrayList<Task> planGreedy;

    public Planner(Task[] taskArray) {

        // Should be instantiated with an Task array
        // All the properties of this class should be initialized here

        this.taskArray = taskArray;
        this.compatibility = new Integer[taskArray.length];
        maxWeight = new Double[taskArray.length];
        Arrays.fill(maxWeight, 0.0);  //TEHLIKE

        this.planDynamic = new ArrayList<>();
        this.planGreedy = new ArrayList<>();
    }

    /**
     * @param index of the {@link Task}
     * @return Returns the index of the last compatible {@link Task},
     * returns -1 if there are no compatible {@link Task}s.
     */
    public int binarySearch(int index) {
        // YOUR CODE HERE
    	int right =0;
    	int left = index-1;
    	int mid = (right + left)/2;
    	int key =Integer.parseInt( this.taskArray[index].start.replace(":", "") );
    	while(right <= left) {
    		if (Integer.parseInt( this.taskArray[mid].getFinishTime().replace(":", "") ) <= key ){  
    	        right = mid + 1;
    	      }
    	      else{  
    	         left = mid - 1;  
    	      }  
    	      mid = (right + left)/2;  
    	   }
    	return left;
    	}
    	
    	
    	
    	
    	

    


    /**
     * {@link #compatibility} must be filled after calling this method
     */
    public void calculateCompatibility() {
        // YOUR CODE HERE
    	for(int i = 0; i<this.taskArray.length;i++) {
    		this.compatibility[i]= (Integer) binarySearch(i);
    	}
    }


    /**
     * Uses {@link #taskArray} property
     * This function is for generating a plan using the dynamic programming approach.
     * @return Returns a list of planned tasks.
     */
    public ArrayList<Task> planDynamic() {
        // YOUR CODE HERE
        calculateCompatibility();
        System.out.println("Calculating max array\n---------------------");
        calculateMaxWeight(this.taskArray.length-1);
        System.out.println("\nCalculating the dynamic solution\n--------------------------------");
        solveDynamic(this.taskArray.length-1);
        Collections.reverse(planDynamic);
        System.out.println("\nDynamic Schedule\n----------------");
        for(int i=0; i<planDynamic.size();i++) {
        	System.out.println("At "+planDynamic.get(i).start+", "+planDynamic.get(i).name);
        }
        
        return planDynamic;
    }

    /**
     * {@link #planDynamic} must be filled after calling this method
     */
    public void solveDynamic(int i) {
        // YOUR CODE HERE
    	
    	System.out.println("Called solveDynamic("+i+")");
    	if(i==0 || this.compatibility[i] ==-1) {
    		planDynamic.add(this.taskArray[i]);
    		return;
    	}
    	else if( (this.taskArray[i].getWeight() + this.maxWeight[this.compatibility[i]]) > this.maxWeight[i-1] ) {
    		planDynamic.add(this.taskArray[i]);
    		solveDynamic(this.compatibility[i]);
    	}
    	else {
    		solveDynamic(i-1);
    	}
    	
    }

    /**
     * {@link #maxWeight} must be filled after calling this method
     */
    /* This function calculates maximum weights and prints out whether it has been called before or not  */
    public Double calculateMaxWeight(int i) {
        // YOUR CODE HERE
    	
    	System.out.println("Called calculateMaxWeight("+i+")");
    	if(i==-1) {
    		
    		return 0.0;
    	}
    	if(this.maxWeight[i]==0) {
    		this.maxWeight[i] = Math.max(this.taskArray[i].getWeight() +calculateMaxWeight(compatibility[i]),calculateMaxWeight(i-1));
    	}
    	
    	return this.maxWeight[i];
        
        
        
        
   
    }

    /**
     * {@link #planGreedy} must be filled after calling this method
     * Uses {@link #taskArray} property
     *
     * @return Returns a list of scheduled assignments
     */

    /*
     * This function is for generating a plan using the greedy approach.
     * */
    public ArrayList<Task> planGreedy() {
        // YOUR CODE HERE
    	planGreedy.add(this.taskArray[0]);
    	int finishTime = Integer.parseInt(this.taskArray[0].getFinishTime().replace(":",""));
    	for(int i = 1; i<this.taskArray.length; i++) {
    		if(Integer.parseInt(this.taskArray[i].start.replace(":","")) >= finishTime) {
    			planGreedy.add(this.taskArray[i]);
    			finishTime = Integer.parseInt(this.taskArray[i].getFinishTime().replace(":",""));	
    		}
    		
    	
    	}
    	
    	
    	System.out.println("\nGreedy Schedule\n---------------");
    	for(int i=0; i<planGreedy.size();i++) {
    		System.out.println("At "+planGreedy.get(i).start+", "+planGreedy.get(i).name);
    	}
    	
        return planGreedy;
    }
}
