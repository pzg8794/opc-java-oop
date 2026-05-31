/*
 * Config.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log:
 * Finally Done
 *
 */
import java.util.ArrayList;

/** 
 * Config.java is a class that represents a list of jugs for the water puzzle.
 * 
 * @author Piter Garcia
 */
public class Config {

    /*
     * contains a list of jugs.
     */
	private ArrayList<Jugs> jugs; 

	
	
    /**
     * Constructor, creates a list of jugs.
     */
	public Config( ArrayList<Jugs> tp) {
		this.jugs = tp;


	}
	
	
	
	/**
     * Gets a list of jugs.
     *
     * @return jugs, a list of jugs.
     */
	public ArrayList<Jugs> getJugs(){
		return jugs;
	}

	
	
    /**
     * Generates an unique Id for a list of jugs.
     *
     * @return uniqueID, an unique ID for a list of jugs.
     */
	public int hashCode(){
		int uniqueId = 0;
		String s = new String();
		for( Jugs e : jugs){

			if( e.isEmpty()){
				uniqueId = 0;
				s += "" + uniqueId;
			}else if(e.isFull()){
				uniqueId = e.getCapacity();
				s += "" + uniqueId;
			}else {
				uniqueId = e.getGallons();
				s += "" + uniqueId;
			}
			uniqueId = Integer.parseInt(s);
		}		
		return uniqueId;
	}

	
	
	
    /**
     * Checks whether a list is equal or not, this comparison is useful for 
     * the HashCode method implementation.
     *
     * @param o, is a config variable that contains a list of jugs.
     * @return true, it returns true if the list is the same.
     * @return false,it returns false if the list is false.
     */
	public boolean equals(Object o){
		if ( o instanceof Config){
			Config temp = (Config) o;
			if( this.jugs.equals(temp.getJugs())){
				return true;
			}
		}
		return false;
	}

	public String toString(){
		String s = new String();
		for ( Jugs j : jugs){
			s += j.toString() + " ";
		}
		return s;
	}

	public Config configClone() {
		ArrayList<Jugs> copy = new ArrayList<Jugs>();
		for ( Jugs j : jugs){
			copy.add(new Jugs(j));
		}
		Config c = new Config(copy);
		return c;
	}
}
