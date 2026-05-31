/* 
 * Quad.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */


/** 
 * Display Operating System Name
 * 
 * @author      Piter Garcia
 */
class OsName {
    public static void main (String args []) {  // main program
    	
    	String OperatingSystemName= System.getProperty("os.name");
    	System.out.println("OS: " + OperatingSystemName);
    }
}