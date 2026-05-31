import java.io.*;
import java.net.*;
import java.util.*;


public class Client{


	public static void main(String[] args){
	
 	

	try{
	Socket client = new Socket("184.12.12.154", 5000);
	BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

	while(true){
		String test = reader.readLine();
		System.out.println(test);
        }

	

		//client.close();
	}catch(Exception e){
		System.out.println(e);
	
	}


	}


}
