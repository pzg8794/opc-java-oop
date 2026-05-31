/*
 * HardCover.java
 *
 * Version:
 *  $Id: 
 *
 * Revisions:
 *  $Log: 
 *
 */




/**
 * HardcoverBook represents books with hard covers.
 * @author piter garcia
 *
 */
public class HardcoverBook extends Book {

	/*
	 *  coverMaterial - of the book.
	 */
	private Media coverMaterial;
	
	/*
	 *  material - of the book.
	 */
	private String material;
	
	
	
	
	
	/**
	 * The HardcoverBook constructor instantiates the instance by 
	 * initializing all the fields with the specified values.
	 * 
	 * @param title,  the title of the book
	 * @param author,  the author of the book
	 * @param cost, the cost of the book in cents
	 * @param coverMaterial, coverMaterial - of the book.
	 */
	public HardcoverBook(String title, String author, Integer cost, String coverMaterial) {
		super(title, author, cost, Media.Hardcover);
		this.coverMaterial = Media.Hardcover;
		material = coverMaterial;
	}


	
	
	/**
	 * The getMedia method extends the standard media information string by 
	 * adding text describing the cover material of this instance. There is
	 *  a trailing period(.) at the end of the returned text.
	 *  
	 *  overrides - getMedia in class Book
	 *  
	 *  @return the book's media.
	 */
	public String getMedia(){
		return coverMaterial.name() + ": " + material;
	}

	
	
	/**
	 * The isForSale implementation depends on the book's media. Some kinds
	 * of books are offered only for rent, not for sale.
	 * 
	 * @return true if this instance is for a final sale.
	 */
	public boolean isForSale(){
		return true;
	}
	
	
	
	/**
	 * The toString represents an HardcoverBook by adding " {cover material}."
	 *  to the standard string representation of a Book. The {cover material} 
	 *  in this case is the instance's cover material. There is a trailing 
	 *  period(.) at the end of the returned text.
	 *  
	 *  Overrides:
	 *  toString in class Book
	 *  
	 *  @return string representation of this hardcover book.
	 *  
	 */
	public String toString(){
		return super.toString() + this.material + ".";
	}
}
