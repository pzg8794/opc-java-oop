
public class PaperSize implements Resource{

	private double PaperSize;
	private double width;
	private int length;
	
	public PaperSize(String PaperSize) {
		String[] s = PaperSize.split("x");
	
		width = Double.parseDouble(s[0]);
		length = Integer.parseInt(s[1]);
		this.PaperSize = width *length;
	}

	public void setPaperSize(int PaperSize) {
		this.PaperSize = PaperSize;
	}

	public double getPaperSize() {
		return PaperSize;
	}

	@Override
	public String getTypeName() {
		return "size";
	}

	@Override
	public int compareTo(Resource other) {
		if( other instanceof PaperSize){
			PaperSize tp = (PaperSize)other;
			if( this.hashCode() <= tp.hashCode())
				return 1;
			else 
				return -1;
		}
		return 1;
	}
	
	public boolean equals(Resource tp){
		if( tp instanceof PaperSize){
			PaperSize temp = (PaperSize)tp;
			return this.hashCode() == temp.hashCode();
		}
		return false;
	}
	
	public String toString(){
		return width + "x" + length;
	}
	
    public int hashCode(){
		return (int) PaperSize;
    }

}
