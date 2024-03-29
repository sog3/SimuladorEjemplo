package cpu;

import Items.Cola;

/**
 * @author Ahmed Elmowafy
 * Main queue is the main list of jobs in the simulation, this class is used 
 * to keep the main queue and a temp queue which is used to keep a copy of the
 * main queue to be used when the user wants to restart the same simulation with
 * the same jobs data.
 */
public class MainQueue {
 
    private static Cola MainQ;  // main list of jobs of the simulation
    private static Cola tempMainQ;  // temp copy of the main list to restart the simulation
    
    /**
     * create new main queue and fill its jobs with random data
     * @param numOfJobs number of jobs in the queue
     * @return the main queue of jobs
     */
    public static Cola createNew(int numOfJobs){
        MainQ = new Cola(numOfJobs);
        MainQ.fill();   // fill the queue with random data jobs
        tempMainQ = MainQ.getClearCopy(); // take a temp copy
        return MainQ;
    }
    
    /**
     * reset the main queue to its initial data
     */
    public static void reset()
    {
        MainQ = tempMainQ.getClearCopy();
    }
    
    /**
     * fill the main queue with jobs from another queue
     * @param list queue of jobs
     */
    public static void add(Cola list)
    {
        MainQ = list.getClearCopy();
        tempMainQ = MainQ.getClearCopy();
    }
    
    /**
     * getter of the Main queue
     * @return a separate copy of the main queue
     */
    public static Cola get(){
        return MainQ.getCopy();
    }
    
}
