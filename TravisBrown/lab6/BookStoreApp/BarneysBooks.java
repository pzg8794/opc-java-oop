/*
 * BarneysBooks.java
 * 
 * $Id: Store.java,v 1.1 2013/07/16 22:52:25 pzg8794 Exp $
 */


import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * this class represents the model of the book store
 * @author pitergarcia
 *
 */
public class BarneysBooks {

	//store containing the books
	private Store store;
	// a list of books resulting from the user search
	private static ArrayList<Book> books = new ArrayList<Book>();

	
	
	/**
	 * Constructor to generate the inventory of the store
	 * @param store
	 * @throws FileNotFoundException
	 */
	public BarneysBooks(Store store) throws FileNotFoundException{
		this.store = store;
		this.store.fillInventory("input.txt");
		
	}
	
	
	
	/**
	 * main(String[] args), this function is creating frame named
	 * concentration. This frame is a GUI that simulates a game 
	 * named concentration. Most of JFrame and Swing functions are 
	 * used for this game.
	 *
	 * @param       String[], args are being ignored.
	 * @throws FileNotFoundException 
	 */
	public static void main ( String[] args) throws FileNotFoundException{
		
        if ( args.length != 1 ) {
            System.out.println( "Usage: java Store filename" );

        } else {
            Store store = new Store();

            // read the inventory, which may throw a FileNotFoundException.
            try {
				store.fillInventory( args[0] );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            
         new BarneysBooks(store);
           
        }
	}

	
	
	
	/**
	 * Search for books that have the string text, this type of search
	 * is done by the title of the books.
	 * @param text, string all books have in commune
	 * @return list, a list of books
	 */
	public String[] getTitleBooks(String text) {
		
		String[] list = new String[store.getInventory().size()];
		
		if(text.equals("")){
			books.clear();
			books.addAll(store.getInventory());
		}else{
			books.clear();
			books.addAll(store.listMatching(text, Store.TITLE_SEARCH));
		}
		int i = 0;
		for( Book b : books){
			list[i++] = b.getTitle();
			System.out.println(b.getTitle());
		}
		
		return list;
	}

	
	
	
	
	/**
	 * Search for books that have the string text, this type of search
	 * is done by the author of the books.
	 * @param text, string all books have in commune
	 * @return list, a list of books
	 */
	public String[] getAuthorBooks(String text) {
		
		String[] list = new String[store.getInventory().size()];
		
		if(text.equals("")){
			books.clear();
			books.addAll(store.getInventory());
		}else{
			books.clear();
			books.addAll(store.listMatching(text, Store.AUTHOR_SEARCH));
		}
		
		int i = 0;
		for( Book b : books){
			list[i++] = b.getTitle();
			System.out.println(b.getTitle());
		}
		
		return list;
	}

	
	
	
	/**
	 * gets the store, that contains all details of the books.
	 * @return store, contains all details of the books.
	 */
	public Store getStore() {
		return store;
	}
	
	
	
	
	/**
	 * gets the list of books in the store, for main display 
	 * purpose
	 * @return list of books.
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	
	
	/**
	 * Search for books that have the string text, this type of search
	 * is done by the media of the books.
	 * @param text, string all books have in commune
	 * @return list, a list of books
	 */
	public String[] getMediaBooks(String text) {
		
		String[] list = new String[store.getInventory().size()];
		
		if(text.equals("")){
			books.clear();
			books.addAll(store.getInventory());
		}else{
			books.clear();
			books.addAll(store.listMatching(text, Store.MEDIA_SEARCH));
		}
		
		int i = 0;
		for( Book b : books){
			list[i++] = b.getTitle();
			System.out.println(b.getTitle());
		}
		
		return list;
	}
}
