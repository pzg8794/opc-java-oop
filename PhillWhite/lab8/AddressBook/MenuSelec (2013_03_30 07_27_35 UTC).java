
import java.util.Vector;
/*
 *  Created:      02/07/2012
 *  Last Changed: 02/07/2012
 *  
 *  MenuSelec.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */



/**
 * This program is the heart of the GUI. It does all the data work
 * for the menu that is presented to the user. All functions such as
 * all contacts, common and unique contacts are solved here on this class.
 *
 * @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 */


public class MenuSelec {
	Contacts list = new Contacts();
	@SuppressWarnings({ })
	Contacts list1 = new Contacts();
	@SuppressWarnings("rawtypes")
	Vector tmp = new Vector();
		
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public Vector allContacts() {
		
		@SuppressWarnings("unused")
		Contacts tempList = new Contacts();
		int i = 0;
		
		while(  i != list.workNames.length){
			
			tmp.add(list.workNames[i]);
			i++;
		}
		
		int x = 0;
		while(  x != list.friendNames.length){
			
			if( !workContains(list.friendNames[x]))
					tmp.add(list.friendNames[x]);
			
			x++;
		}
		
		return tmp;
	}

	@SuppressWarnings("static-access")
	public boolean workContains( String tmp){
		
		int i = 0;
		boolean answer =  false;
		
		while( i != list.workNames.length){
			
			if( list.workNames[i] == tmp)
				answer = true;

			
			i++;
		}
		
		return  answer;

	}
	
	
	@SuppressWarnings("static-access")
	public boolean friendsContains( String tmp){
		
		int x = 0;
		boolean answer =  false;
		
		while( x != list.friendNames.length){
			
			
			if( list.friendNames[x] == tmp)
				return true;
			
			x++;
		}
	
		return answer;
	}
	
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public Vector communContacts() {
		
		Contacts list = new Contacts();
		Vector tmp = new Vector();
		
			@SuppressWarnings("unused")
			Contacts tempList = new Contacts();
			int i = 0;
			if( list.workNames.length > list.friendNames.length){
				while(  i != list.workNames.length){
				
					if(friendsContains(list.workNames[i]))
						tmp.add(list.workNames[i]);
						
					i++;
				}
			}
			else{
				int x = 0;
				while(  x != list.friendNames.length){
				
					if( workContains(list.friendNames[x]))
						tmp.add(list.friendNames[x]);
				
					x++;
				}
			}
		
		return tmp;
	}

	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public Vector uniqueContacts() {
		
		Contacts list = new Contacts();
	
		Vector tmp = new Vector();
		
			@SuppressWarnings("unused")
			Contacts tempList = new Contacts();
			int i = 0;
			if( list.workNames.length > list.friendNames.length){
				while(  i != list.workNames.length){
				
					if(!friendsContains(list.workNames[i]))
						tmp.add(list.workNames[i]);
						
					i++;
				}
			}
			else{
				int x = 0;
				while(  x != list.friendNames.length){
				
					if( !workContains(list.friendNames[x]))
						tmp.add(list.friendNames[x]);
				
					x++;
				}
			}
		
		return tmp;
	}

}
