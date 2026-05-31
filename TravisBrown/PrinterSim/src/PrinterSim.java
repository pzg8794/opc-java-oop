import java.util.LinkedList;
import java.util.TreeSet;


public class PrinterSim {

	private int printersN;
	private PQueue pingQ;
	private Source pingS;
	private LinkedList<PQueue> wListQ = new LinkedList<PQueue>(); 
	private PQueue comingQ ;
	private Source comingS;
	private LinkedList<Source> wListS = new LinkedList<Source>();
	private PrinterSim printer;
	private static LinkedList<Integer> pInUse = new LinkedList<Integer>();
	private LinkedList<PrinterSim> list = new LinkedList<PrinterSim>();
	private static int counter = 0;
	private int n;
	private int id;
	private int ticks;
	private boolean available;
	private int qId;
	private TreeSet<PQueue> set;
	private static int prio = -1;
	private int pri = 0;

	public PrinterSim(int managerN) {
		
		printersN = managerN;
		list = new LinkedList<PrinterSim>();
		
		int i = -1;
		while( ++i != printersN)
			list.add(printer = new PrinterSim(managerN, i));
			
		
		System.out.println("Created the following printers: \n");
		for(PrinterSim p: list)
			System.out.println("Printer #" + p.id);
		
		System.out.println("\nSimulation begins ...");
	}

	public PrinterSim() {
	}

	public PrinterSim(int managerN, int i) {
		id = i;
		printersN = managerN;
	}

	public PrinterSim(PQueue qhookUp, Source hookUp) {
		this.add(qhookUp, hookUp);
		id = counter;
		counter++;
	}

	public PrinterSim(int id2, Source first) {
		qId = id2;
		this.pingS = first;
	}

	public void setPrinters(LinkedList<PQueue> qhookUp,
			LinkedList<Source> hookUp) {

		
		int m = 0;
		
		while(!hookUp.isEmpty()){

			comingQ = qhookUp.peek();
			comingS = hookUp.peek();
			
				ticks = hookUp.peek().getTicks();
				int avel = -1;
				boolean stop= false;
			
				avel = printerLookUp();	
				printersUpdate();
				
				for(int x = 0; x< counter; x++){

					System.out.println("Waiting List "+  x + " "+ list.get(x).wListS );
					
					if(list.get(x).pingQ.getId() != qhookUp.peek().getId()){

						System.out.println("empty "+  x + " "+ list.get(x).pingS + " " +
								list.get(x).pingS.timeLeft() );

						if(list.get(x).pingS.isDonePrinting() && list.get(x).pri == prio){
							
							refillNL(x);
							avel = -1;
							stop = true;
						}else if( list.get(x).pingS.isDonePrinting()){
							avel = x;
						}
					}

				}

				if(avel != -1 && !pInUse.contains(qhookUp.peek().getId())){

					useAnotherPrinter(avel);

				}else if(!stop){
					
					vacancy();
				}
				status(hookUp.peek().getTicks());
				hookUp.poll();
				qhookUp.poll();
		}

	}


	private void vacancy() {
		
		for( PrinterSim ps: list){
			
			if(ps.pingQ.getId() == comingQ.getId()){

				checkAvel(ps, comingQ, comingS);
				break;
				
			}else if( ps.wListS.isEmpty()){
	
				checkAvel(ps, comingQ, comingS);
				break;
			}
		}
		
	}

	private void useAnotherPrinter(int avel) {
		System.out.println(" PA "+ avel);
		list.get(avel).add(comingQ, comingS);
		list.get(avel).available = true;
	}

	private void refillNL(int x) {
		System.out.println("empty2 " + list.get(x).pingS);
		for( Integer i: pInUse)
			if( i == list.get(x).pingQ.getId())
				pInUse.remove(i);

		list.get(x).pingQ = comingQ;
		list.get(x).pingS = comingS;
	}

	private void status(Integer ticks2) {
		
		if( ticks2 > 0){
			for(int i= 0; i< ticks ; i++ ){
				update();
				this.startPrint(n);
			}
		}
		
	}

	private void checkAvel(PrinterSim ps, PQueue pQueue, Source source) {
		
		if( ps.pingS.timeLeft() != 0){
			
			ps.wListQ.add(pQueue);
			ps.wListS.add(source);
			ps.available = true;
			
		}else{
			
			ps.pingQ = pQueue;
			ps.pingS = source;
			ps.available = true;
		}
		
	}

	private void printersUpdate() {
		// TODO Auto-generated method stub
		
	}

	private int printerLookUp() {
		int avel = -1;
		for(int x = 0; x< list.size(); x++){
			if(list.get(x).isAvailable() || !list.get(x).available){
				avel = x;
				break;
			}
		}
		return avel;
	}

	private void add(PQueue first, Source peek) {
		
		this.pingQ = first;
		this.pingS = peek;
		counter++;
	}

	private void update() {
		boolean stop = false;
	
		System.out.println("Update Method");
		
	
		for(int x = 0; x< counter; x++){
		
				//	System.out.println(s.timeLeft());
			if(list.get(x).available){
				
				if(list.get(x).pingS.startedPrinting()){
					
					if( list.get(x).pingS.empty && !list.get(x).wListS.isEmpty()){
						stop = refillWL(x);
					}
					
					
					if(list.get(x).pingS.isDonePrinting()){
						
						finish(x);
						
					}else{
						
						keepPrinting(x);
									
					}
					
					if(stop)
						list.get(x).pri = -1;
					
					list.get(x).pingS.keepPrinting();
			
				}else{
					
					start(x);

				}
			}
		}

	}

	private boolean refillWL(int x) {
		System.out.println("Here");
		list.get(x).pingQ = list.get(x).wListQ.poll();
		list.get(x).pingS = list.get(x).wListS.poll();
		list.get(x).pingS.startPrinting();
		return true;
	}

	private void keepPrinting(int x) {
		System.out.println("Printer #" + list.get(x).id + " is still printing " + list.get(x).pingS
				+ ", time left " + list.get(x).pingS.timeLeft());
		
	}

	private void finish(int x) {
		
		if(!list.get(x).pingS.empty)
		System.out.println("Printer #" + list.get(x).id + " has finished printing " 
				+ list.get(x).pingS + " tl: " + list.get(x).pingS.timeLeft());
		
		list.get(x).pingS.empty = true;
		list.get(x).pri = ++prio;
		
	}

	private void start(int x) {
		
		list.get(x).pingS.startPrinting();
		if(!pInUse.contains(list.get(x).pingQ.getId())){
			System.out.println("Queue #" + list.get(x).pingQ.getId() + " is now hookedup to Printer #"
					+ list.get(x).id);
			String r = "";
			for( Resource s1: list.get(x).pingS.getResources())
				r += " " + s1.getTypeName() + " " + s1;
			System.out.println("Printer #" + list.get(x).id + " now has resources " + r);
			pInUse.add(list.get(x).pingQ.getId());
		}
		System.out.println("Printer #" + list.get(x).id + " is starting " + list.get(x).pingS);
		
	}

	private boolean isAvailable() {
		
		if(this.id > counter){
			for(int i = 0; i< counter; i++){
				if( list.get(i).pingQ.empty)
					return false;
			}
				
		}
		if( this.pingS == null )
			return true;
		else{
			
			return false;
		}
	}


	public void startPrint(int sizes) {


		System.out.println("\n--------------------------------- \n"+
				"The time is now " + n++ +"\n-------------------------------" +
				"--");
	}

	public void setPrinters(LinkedList<PQueue> qhookUp,
			LinkedList<Source> hookUp, Integer ticks) {
		

		for(int tick = 0; tick<ticks; tick++){
			setPrinters(qhookUp, hookUp);
			qhookUp.clear();
			hookUp.clear();
		}
	}

	public void endOfFile(int ticks) {
		System.out.println("Ticks After All Jobs:\n" + ticks);
		
	}
	
	public String toString(){
		return this.pingQ + " " + this.pingS;
	}

	public void setPrinters(TreeSet<PQueue> set) {
		this.set = set;
		
//		while(!set.isEmpty()){
//
//			int avel = -1;
//			
//			if( counter == 0){
//				
//				list.add(new PrinterSim(set.first().getId(), set.first().first()));
//				
//			}else{
//			
//				for(int x = 0; x< list.size(); x++){
//					if(list.get(x).isAvailable() || !list.get(x).available){
//						avel = x;
//						break;
//					}
//				}
//			}
//
//			if(avel != -1 && !pInUse.contains(set.first().first().getId())){
//
//				System.out.println(" PA "+ avel);
//				list.get(avel).add(set.first().getId(), set.first().first());
//				list.get(avel).available = true;
//				
//			}else{
//				
//				boolean found = false;
//				for( PrinterSim ps: list){
//					
//					if(ps.qId == set.pollFirst().getId()){
//	
//					
//						ps.wListS.add(set.first().first());
//						ps.available = true;
//						found = true;
//						break;
//						
//					}
//				}
//				
//				if(!found && counter< this.printersN)
//					list.add(new PrinterSim(set.first().getId(), set.first().first()));
//			}
//			
//			if( set.first().first().getTicks() > 0){
//				for(int i= 0; i< set.first().first().getTicks() ; i++ ){
//					update();
//					this.startPrint(n);
//				}
//			}
//			set.first().poll();
//			if(set.first().getSet()== null)
//				set.pollFirst();
//
//
//	}
		
	}

	private void add(int id2, Source first) {
		this.qId = id2;
		this.pingS = first;		
	}

}
