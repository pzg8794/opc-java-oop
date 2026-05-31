import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;


public class PrintSimRun {

	private static int ManagerN;
	private static LinkedList<Manager> Manager = new LinkedList<Manager>();
	private static Scanner in = null;
	
	
	
	public static void main(String[] args){
		
		
		String temp = null;
		// Open the data file.
		//
		String fileName = "data-in.txt";
		try {
		    in = new Scanner( new File( fileName ) );
		}
		catch ( FileNotFoundException e ) {
		    System.err.println( "MyProgram: Unable to open data file" );
		    // Terminate the program here somehow, or see below.
		}
		

		ManagerN = Integer.parseInt(in.next());
		
		Queue<Job> list = new LinkedList<Job>();
		LinkedList<Integer> tickL = new LinkedList<Integer>();
		int tick = 0;
		int k = 0;
		while( in.hasNext()){
			temp = in.next();

			if( temp.equals("job")){
				
				if( !list.isEmpty())
					tickL.add(k-1, tick);
				
				list.add(new Job(Integer.parseInt(in.next()), new PaperColor(in.next()), 
						new PaperSize(in.next() + "x" + in.next())));
				k++;
				tick = 0;
			}else if(temp.equals("tick")){
				tick++;
			}
			
		}
		System.out.println("List of All Jobs : \n" + list + "\n\n");
		
		System.out.println("List of ticks for Jobs : \n");
		String tks = "";
		
		int d = 0;
		for(Integer tk : tickL)
			tks += "| Job Id: " + d++ + " No of Ticks: " + tk;
		
		System.out.println(tickL +  "\n" + tks + "\n");
		
		System.out.println("List of All thicks After all Jobs : \n" + tick +
				"\n\n");
		
		HashSet<Source> set = new HashSet<Source>();
		//CREATING JOBS
		int id =0;
		for( Job j: list){
		
			Iterator<Resource> itr = j.getResources().iterator();
			Resource tp1 = null, tp2 = null;
			while(itr.hasNext()){
				tp1 = itr.next();
				tp2 = itr.next();
			}
			
			String[] s = j.toString().split(",");
			String s1 = s[0].charAt(s.length+1)+"";
			
			if( id >= 9)
				s1 += s[0].charAt(s.length+2);

			id = Integer.parseInt(s1);
			//	System.out.println(s1);

			set.add(new Source(id, j.getTime(), tp1, tp2));
		}
		System.out.println("Organized (In Color) List of Jobs : \n" + set + "\n\n");
		
		//ENQUEUEING JOBS
		LinkedList<Queue<Source>> queues = new LinkedList<Queue<Source>>();
		do{
			LinkedList<Source> q = new LinkedList<Source>();
			
			// System.out.println(set.toArray()[0]);
			q.add((Source) set.toArray()[0]);	
			set.remove(set.toArray()[0]);
			
			
			for( int i =0 ; i < set.size() ; i++){
			
				while(i < set.size()-1 && q.peek().hashCode() == set.toArray()[i+1].hashCode()){
					
					//	System.out.println(set.toArray()[i+1]);
					q.add((Source) set.toArray()[i+1]);	
					set.remove(set.toArray()[i+1]);
				}
				
				if( q.peek().hashCode() == set.toArray()[i].hashCode()){
					
					//	System.out.println(set.toArray()[i]);
					q.addLast((Source) set.toArray()[i]);	
					set.remove(set.toArray()[i]);

				}
			}
			queues.addFirst(q);
			
		}while(!set.isEmpty());
		
		
		//ORGANIZING QUEUES
		System.out.println("Created Queues for all Jobs : ");
		LinkedList<Queue<Source>> queues1 = new LinkedList<Queue<Source>>();
		PQueue set2 = null;
		int i = -1;
		for( Queue<Source> q : queues){
			set2 = new PQueue (q);
			q = (Queue<Source>) set2.getSet();
			queues1.add(q);
			System.out.println("Queue #" + ++i + " " + q);
		}
		System.out.println();
		
		TreeSet<PQueue> ordered = new TreeSet<PQueue>();
		i = -1;
		for( Queue<Source> q : queues1){
			ordered.add(new PQueue(q, q.peek().getId()));
		}
		
		int n = 0;
		for( PQueue pq : ordered){
			pq.setId(n++);
		}
		System.out.println("Organized Set of Queues: \n" + ordered + "\n");
		
		System.out.println("Organized Set of Queues: \n" + ordered + "\n");
		
		Manager managers = new Manager(ordered.size());
		System.out.println(" Adding Queues to a Manager: ");
		int pId = 0;
		do{
			managers.enqueue(ordered.pollFirst(), pId);
			pId++;
				
		}while( !ordered.isEmpty());
		System.out.println(" \nDisplaying Queues for Managers: ");
		System.out.println(managers);
		
		System.out.println(" \nDisplaying Waiting Queues: ");
		System.out.println(ordered);
		
		ArrayList<Integer>key = new ArrayList<Integer>();
		ArrayList<Integer>mSource = new ArrayList<Integer>();
		
		for( Manager p: managers.getQueue()){
			
			for( int j=0; j < p.getManagerN().size(); j++){
				Source tp2 = p.getManagerN().get(j);
				key.add(tp2.getId());
			}
			
			mSource.add(p.getManagerN().peek().getId());
		}
		System.out.println("\nJobs On The Peek of the Queues: \n" + mSource + "\n\n");
		
		System.out.println("Text file " + fileName + " \n");
		int x = 0;
		int size = managers.getQueue().size();
		LinkedList<Source> hookUp = new LinkedList<Source>();
		LinkedList<PQueue> qhookUp = new LinkedList<PQueue>();
		PrinterSim printing = new PrinterSim(ManagerN);
		
		
		for( Manager p: managers.getQueue()){
			
			for( int j=0; j < p.getManagerN().size(); j++){
				
				Source tp2 = p.getManagerN().get(j);

				if(tp2.getId() < tickL.size())
					tp2.setTicks(tickL.get(tp2.getId()));
			}
		}
		
//		managers.removeQFS();
//		managers.sort();
		
		
		System.out.println("SET " + managers.getAllQueues());
		printing.setPrinters(managers.getAllQueues());
		
		printing.startPrint(size);
		do{
					qhookUp.add(managers.pop());
					hookUp.add(managers.removeQFS());
					managers.sort();
					System.out.println(managers);
					
					if( hookUp.peek().getId() < tickL.size() && hookUp.peek().getTicks() > 0){
						printing.setPrinters(qhookUp, hookUp, hookUp.peek().getTicks() );
						// System.out.println("Job Id: " + tp2.getId());
						//	if(p.getManagerN().isEmpty())
						//	printing.setPrinters(null, null, -1);
					
					qhookUp.clear();
					hookUp.clear();
					size = 0;
					}
					
				
				if(managers.firstQ().getSet().isEmpty()){
					managers.pop();
				}
				
		}while( !managers.isEmpty());

		
		System.out.println("Left Over " + qhookUp);
		printing.setPrinters(qhookUp, hookUp, tick);
	}

	public LinkedList<Manager> getManager() {
		return Manager;
	}

	public int getManagerN() {
		return ManagerN;
	}
}
