/*
 * $Id: Job.java,v 1.12 2011/03/24 04:46:00 jeh Exp $
 */

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A rudimentary print job. Each job contains a unique id, the file to
 * be printed, and all the resources it needs.
 * 
 * @author Jim Heliotis
 * @author K. Reek
 * @author Paul Tymann
 */
public class Job {

    /**
     *  Class variable that gets incremented to assign unique IDs to each job
     */
    static int nextJobID = 0;

    /**
     * Unique numerical ID of this job
     */
    private int id;

    /**
     * The time (number of ticks) required to print this job
     */
    private int time;
    
    /**
     * The SortedSet of resources required to print this job.
     */
    private SortedSet< Resource > requires;

    /**
     * Does this resource's type conflict with the given SortedSet of resources?
     * 
     * @param r the resource to be checked against the SortedSet
     * @param res the SortedSet of resources
     * @return true iff there is a resource in res
     *              whose typeName is the same as r's
     */
    private static boolean conflict( Resource r, SortedSet< Resource > res ) {
    	String rName = r.getTypeName();
    	for ( Resource aRes: res ) {
    		if ( aRes.getTypeName().equals( rName ) ) return true;
    	}
    	return false;
    }
    
    /**
     * Create a new job, with the given resource requirements. The job will
     * automatically be assigned a unique job id.
     * If more than one resource of the same type ID (class) is given,
     * only the first one is used; the rest are ignored.
     * 
     * @param runningTime the number of ticks needed to print the job.
     *                    If 0, a pseudo-random time is used.
     * @param needs an array of resource requirements for this job.
     */
    public Job( int runningTime, Resource... needs ) {

        // Assign the next available ID to this job.
        //
        id = nextJobID;
        nextJobID += 1;

        // Record the resources.
        //
        requires = new TreeSet< Resource >();
        for ( Resource r : needs ) {
            if ( conflict( r, requires ) ) {
                System.err.println( "Duplicate " + r.getTypeName() +
                		            " resource " + r + " ignored." );
            }
            else {
                requires.add( r );
            }
        }
        
        // Set the job's printing time.
        //
        time = ( runningTime == 0 ) ? randomizeJobDuration( id ) : runningTime;
    }

    /**
     * How much time will it take for this job to print?
     * 
     * @return the time required to print this job.
     */
    public int getTime() {
        return time;
    }

    /**
     * Get the resources a printer must have to print this job.
     * 
     * @return the (resource type, resource) SortedSet required to print this job.
     */
    public SortedSet< Resource > getResources() {
        return requires;
    }

    /**
     * Convert this Job to a string. The format is
     * <tt>Job(id, time, [</tt><i>resource-list</i><tt>])</tt>.
     * 
     * @return a string representation of this job.
     */
    @Override
    public String toString() {
        String result = "Job(" + id + ", " + time + ", [";
        for ( Resource r: requires ) {
        	result += " " + r.getTypeName() + ": " + r;
        }
        result += " ])";
        return result;
    }

    /**
     * Compute a the job time based on its ID. Its range is from 1 to 5 units.
     * 
     * Note that the duration is not really random. This is done so that the
     * output produced by the program is deterministic.
     * 
     * @return pseudo-random job duration from 1 to 5 units.
     */
    private static int randomizeJobDuration( int id ) {
        int duration = Math.abs( ( id % 2 == 0 ) ? 12 * id : 16 - 3 * id );
        return duration % 5 + 1;
    }

} // Job