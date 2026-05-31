
public class PaperColor implements Resource{

	/*
	 * 
	 */
	private String source;
	
	/**
	 * 
	 * @param source
	 */
	public PaperColor(String source){
		this.source = source;

	}
	
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
	}
	
	
	
	
	/**
	 * 
	 */
	@Override
	public String getTypeName() {
	
		return "color";
	
	}

	
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Resource other) {
		
		if( other instanceof PaperColor){
			PaperColor temp = (PaperColor)other;
			if(this.source.equals(temp.source))
				return 1;
			else 
				return -1;
		}
		return -1;
	}
	
	/**
	 * 
	 */
	public String toString(){

		return source;
	}

	
		
	/**
	 * 
	 * @return
	 */
	public String getSize() {
		return source;
	}
	
	public boolean equals(Resource tp){
		if( tp instanceof PaperColor){
			PaperColor temp = (PaperColor)tp;
			return this.source.equals(temp.source);
		}
		return false;
	}

	
    public int hashCode(){
		return source.hashCode();
    }
}
