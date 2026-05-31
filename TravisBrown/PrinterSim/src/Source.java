

public class Source extends Job implements Comparable<Source> {

	/*
	 * 
	 */
	private PaperColor PaperColor;
	private PaperSize PaperSize;
	private int time;
	private int id;
	private Integer ticks;
	private boolean startedPrinting;
	public boolean empty;
	
	
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}



	public void setTime(int time) {
		this.time = time;
	}
	
	
	
	
	public Source(int i, int time, Resource ... resource) {
		super(time, resource);
		id = i;
		this.time = time;
		setPaperColor(resource[0]);
		setPaperSize(resource[1]);
		this.startedPrinting = false;
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
	public int compareTo(Source other) {
	
		if( this.getColor().equals(other.getColor()) && 
				(this.getSize().equals(this.getSize()))){
			return 1;
		}
		return -1;
	}

	
	public Resource getSize() {
		return PaperSize;
	}




	public boolean equals(Source tp){
		if( this.hashCode() == tp.hashCode()){
			return true;
		}
		return false;
	}

	
    public int hashCode(){
    	int temp = 0;
    	if(this.PaperColor.equals("white") )
    		temp = 10;
    	if(this.PaperColor.equals("blue") )
    		temp = 20;
    	if(this.PaperColor.equals("yellow") )
    		temp = 30;
    	if(this.PaperColor.equals("green") )
    		temp = 40;
    	if(this.PaperColor.equals("pink") )
    		temp = 50;
    	if(this.PaperColor.equals("red") )
    		temp = 60;
    	else
    		temp = 70;
    		
		return (this.PaperColor.hashCode() - this.PaperSize.hashCode())*temp;
    }




	public Resource getColor() {
		return PaperColor;
	}




	public void setPaperColor(Resource PaperColor) {
		this.PaperColor = (PaperColor)PaperColor;
	}




	public void setPaperSize(Resource PaperSize) {
		this.PaperSize = (PaperSize)PaperSize;
	}
	
	public String toString(){
		return "Job(" + this.id + ", " + this.getTime() + ", [ " + 
			this.PaperColor.getTypeName() + ": " + this.PaperColor.toString()
			+ " "+this.PaperSize.getTypeName() + ": " + 
			this.PaperSize.toString() + " ])";
	}



	public Integer getTicks() {
		return ticks;
	}

	
	public int timeLeft() {
		return time;
	}



	public void setTicks(Integer ticks) {
		this.ticks = ticks;
	}
	
	
	public void startPrinting(){
		if( time > 0)
			time--;
		this.startedPrinting = true;
	}


	public void keepPrinting(){
		
		if(ticks > 0)
			ticks--;
		
		if( time > 0)
			time--;
	}

	public boolean isDonePrinting() {
		return this.startedPrinting && time == 0;
	}




	public boolean startedPrinting() {
		return startedPrinting;
	}
	
}
