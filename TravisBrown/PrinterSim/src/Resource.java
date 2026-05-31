/*
 * $Id: Resource.java,v 1.8 2011/03/24 04:32:04 jeh Exp $
 */

/**
 * The generic description of any resource required by a print job
 * and offered by a printer.
 * 
 * Most of the methods listed here only mentioned for emphasis.
 * 
 * @author James Heliotis
 */
public interface Resource extends Comparable< Resource > {

    /**
     * Fetch the name of this resource type.
     * @return the unique string assigned to this resource type.
     */
    public String getTypeName();
    
    /**
     * Are two resources actually the same?
     * Needed so that resources can be used in collections that get searched.
     * <br/>
     * postcondition: equals( x ) => compareTo( x ) == 0
     * 
     * @param other the resource being compared to this one
     * @return true iff other is the same class as this resource
     *              and contains the same value.
     */
    public boolean equals( Object other );
    
    /**
     * @return a human-understandable description of this resource
     */
    public String toString();
    
    /**
     * Compare two resources based on their typeNames.
     * If the types, and therefore typeNames, are the same,
     * the comparison is implementing-class-dependent.
     * <br/>
     * postcondition: compareTo( x ) == 0 => equals( x )
     * @return getTypeName().compareTo( other.getTypeName() )
     */
    public int compareTo( Resource other );
}