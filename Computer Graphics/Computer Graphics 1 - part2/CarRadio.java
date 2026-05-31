/*
 * CarRadio.java
 *
 * Version:
 *  $Id: CarRadio.java,v 1.1 2013/03/18 15:26:15 pzg8794 Exp $
 * Revisions:
 *  $Log: CarRadio.java,v $
 *  Revision 1.1  2013/03/18 15:26:15  pzg8794
 *  Lab2 - Radio
 *
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** This class represents an AM/FM car radio.
 * 
 * @author Piter Garcia
 *
 */

public class CarRadio {
	/*
	*
	*/
	private boolean power;
	/*
	*
	*/
	private boolean mute;
	/*
	*
	*/
	private int volumen;
	/*
	*
	*/
	private String band;

	private String line;
	private String set;
	private int counter;
	
	private String channel;
	private String channelAM;
	private String channelFM;
	
	private String frequency;
	private int frequencyAM;
	private int frequencyFM;
	private String frequenciesAM;
	private String frequenciesFM;
	
	private String volumenT = new String();
	private List<String> sets = new ArrayList<String>();
	
	/*
    * Constant value of 20 representing the maximum volume of the radio.
	*/
	private static int MAX_VOLUMEN = 20;
	/*
	* Constant value of 0 representing the minimum volume of the radio.
	*/
	private static int MIN_VOLUMEN = 0;
	
	/*
	* Location of file to read
    */
	private static File file = new File("textFile3.txt");
        private static Scanner scanner;

    
	/**	
	* Construct a car radio with factory default settings and with the supplied 
	* station data.
	*
	* @param sd  The local station data, used to determine viable stations and 
	* station ids.
	*/
	public CarRadio(StationData sd) {
		power   = false;
		mute    = false;
		volumen = 0;
		band    = "AM";
		
		volumenT = " 0";
		set      = "    ";
		counter  = 0;
		
		channel   = "****";
		channelAM = "****";
		channelFM = "****";
		
		frequency     = "    " + String.valueOf(FreqBand.AM.minFreq());
		frequencyAM   = FreqBand.AM.minFreq();
		frequencyFM   = FreqBand.FM.minFreq();
		frequenciesAM = "    520"; 
		frequenciesFM = "   87.9";
	}

	
	
	/**
	* Turns the car radio on and off.When the car radio is off, it retains, but 
	* buttons do not change, the state of the volume (and mute), am/fm band and 
	* frequency, and presets.
	*
	* @exception IOException
	*
	*/
	public void powerBtn() throws IOException {
		
		//if( (strLine = in.readLine()) == "power")
		power = !power;
		
	}

	
	
	
	/**
	* Increases the car radio volume by one unit.The car radio has a maximum 
	* volume of 20; attempts to increase the volume beyond the maximum volume 
	* should not change the volume.The factory default setting for the volume is 0.
	*
	*/
	public void volumeUpBtn() {
		
		if(power) {
		      
		      if( volumen < MAX_VOLUMEN)
			      volumen++;
		      else
			      volumen = MAX_VOLUMEN;
		
		      volumenT = String.valueOf(volumen);
		
		      if( volumenT.length() < 2)
		              volumenT= " " +  volumenT;
		}
	}

	
	
	
	/**
	* Decreases the car radio volume by one unit.The car radio has a minimum 
	* volume of 0; attempts to decrease the volume beyond the minimum volume should
	* not change the volume.The factory default setting for the volume is 0.
	*
	*/
	public void volumeDownBtn() {
		
		if( volumen > MIN_VOLUMEN)
			volumen--;
		else
			volumen = MIN_VOLUMEN;
		
		volumenT = String.valueOf(volumen);
		
		if( volumenT.length() < 2)
			 volumenT= " " +  volumenT;
	}

	
 
	
	/** 
    * Toggles the mute status of the car radio.When the car radio is muted, the
	* volume may be changed, but the car radio remains muted.The factory default 
	* setting for the mute status is unmuted.
	*
	*/
	public void muteBtn() {
		
		if( mute == false)
			mute = true;
		else 
			mute = false;
		
	}

	
	
	/**
	* Tune to or set preset #5.There are primary and secondary preset#5 frequencies
	* for both the AM band and the FM band. If the radio is unprepared to set a 
	* preset frequency: If the car radio is not tuned to the primary preset #5 
	* frequency (for the current frequency band), then tune to the primary preset 
	* #5 frequency (for the current frequency band). Otherwise (if the car radio 
	* is already tuned to the primary preset #5 frequency (for the current 
	* frequency band)), then tune to the secondary preset #5 frequency (for the 
	* current frequency band).
	*
	*If the radio is prepared to set a primary preset frequency: Set the primary 
	* preset #5 frequency (for the current frequency band) to the tuned frequency.
	
	* If the radio is prepared to set a secondary preset frequency: Set the 
	* secondary preset #5 frequency (for the current frequency band) to the tuned 
	* frequency.
	*
	* The factory default settings for the primary and secondary preset #5 
	* frequencies for the AM band are the minimum AM band frequency (520kHz). 
	* The factory default settings for the primary and secondary preset #5 
	* frequencies for the FM band are the minimum FM band frequency (87.9MHz).
	*
	*/
	public void preset5Btn() {
		
		   counter -= 5;
		   if( counter < 0)
			   counter = 0;
		   
		   set = "    ";	
	}

	
	
	
	
	/** 
	* Tune to or set preset #4.There are primary and secondary preset#4 frequencies
	* for both the AM band and the FM band.
	*
	* If the radio is unprepared to set a preset frequency: If the car radio is not 
	* tuned to the primary preset#4 frequency (for the current frequency band), 
	* then tune to the primary preset#4 frequency (for the current frequency band). 
	* Otherwise (if car radio is already tuned to the primary preset#4 frequency 
	* (for the current frequency band)), then tune to the secondary preset #4 
	* frequency (for the current frequency band).
	*
	* If the radio is prepared to set a primary preset frequency: Set the primary 
	* preset #4 frequency (for the current frequency band) to the tuned frequency.
	*
	* If the radio is prepared to set secondary preset frequency:Set the secondary
	* preset #4 frequency (for the current frequency band) to the tuned frequency.
	*
	* The factory default settings for primary and secondary preset#4 frequencies
	* for the AM band are the minimum  band frequency(520kHz). The factory default 
	* settings for the primary and secondary preset #4 frequencies for the FM band
	* are the minimum FM band frequency (87.9MHz).
	*
	*/
	public void preset4Btn() {
		
		   counter -= 4;
		   if( counter < 0)
			   counter = 0;
		   
	 	   set = "    ";	
	}

	
	
	
	
	/**
    * Tune to or set preset #3.There are primary and secondary preset #3 
	* frequencies for both the AM band and the FM  band.
    *
	* If the radio is unprepared to set a preset frequency: If car radio is
	* not tuned to the primary preset#3 frequency (for current frequency band), 
	* then tune to the primary preset#3 frequency (for current frequency band).
	* Otherwise (if car radio is already tuned to the primary preset#3 frequency
	* (for the current frequency band)), then tune to the secondary preset #3 
	* frequency (for the current frequency band).
    *
	* If the radio is prepared to set a primary preset frequency: Set the primary 
	* preset #3 frequency (for the current frequency band) to the tuned frequency.
	*
    * If the radio is prepared to set a secondary preset frequency: Set the
	* secondary preset#3 frequency (for the current frequency band) to the tuned
	* frequency.
	*
	* If the radio is prepared to set a secondary preset frequency: Unprepare the 
	* radio to set a preset frequency.
	*
	* The factory default setting is unprepared to set a preset frequency.
	*
    * The factory default settings for the primary and secondary preset #3 
    * frequencies for the AM band are the minimum AM band frequency (520kHz). The
    * factory default settings for the primary and secondary preset#3 frequencies
    * for the FM band are the minimum FM band frequency (87.9MHz).
    *
	*/
	public void preset3Btn() {
		
        counter -= 3;
        if( counter < 0)
            counter = 0;
        
        set = "    ";
		
	}
    
	
	
	
	/**	
    * Tune to or set preset #2.
	* There are primary and secondary preset #2 frequencies for both the AM band 
    * and the FM band.
	*
	* If the radio is unprepared to set a preset frequency: If the car radio is 
    * not tuned to the primary preset #2 frequency (for the current frequency 
    * band), then tune to the primary preset #2 frequency (for the current 
    * frequency band). Otherwise (if the car
	* radio is already tuned to the primary preset #2 frequency (for the current frequency
	* band)), then tune to the secondary preset #2 frequency (for the current frequency band).
	*
	* If the radio is prepared to set a primary preset frequency: Set the primary preset #2
	* frequency (for the current frequency band) to the tuned frequency.
	*
	* If the radio is prepared to set a secondary preset frequency: Set the secondary preset
	* #2 frequency (for the current frequency band) to t*he tuned frequency.
	*
	* The factory default settings for the primary and secondary preset #2 frequencies for
	* the AM band are the minimum AM band frequency (520kHz). The factory default settings
	* for the primary and secondary preset #2 frequencies for the FM band are the minimum FM
	* band frequency (87.9MHz).
	*/
    public void preset2Btn() {
		
        counter -= 2;
        if( counter < 0)
            counter = 0;
        
        set = "    ";
		
	}
    
	
	
	
	/**
    * Tune to or set preset #1.
	* There are primary and secondary preset #1 frequencies for both the AM band and the FM band.
	*
	* If the radio is unprepared to set a preset frequency: If the car radio is not tuned to the
	* primary preset #1 frequency (for the current frequency band), then tune to the primary
	* preset #1 frequency (for the current frequency band). Otherwise (if the car radio is
	* already tuned to the primary preset #1 frequency (for the current frequency band)), then
	* tune to the secondary preset #1 frequency (for the current frequency band).
	*
	* If the radio is prepared to set a primary preset frequency: Set the primary preset #1
	* frequency (for the current frequency band) to the tuned frequency.
	*
	* If the radio is prepared to set a secondary preset frequency: Set the secondary preset #1
	* frequency (for the current frequency band) to the tuned frequency.
	*
	* The factory default settings for the primary and secondary preset #1 frequencies for the
	* AM band are the minimum AM band frequency (520kHz). The factory default settings for the
	* primary and secondary preset #1 frequencies for the FM band are the minimum FM band
	* frequency (87.9MHz).
    */
	public void preset1Btn() {
        
        counter--;
        if( counter < 0)
            counter = 0;
        
        set = "    ";
        
	}
    
	
	
	
	/**
    * Prepare to set a primary or secondary preset frequency.
	* If the radio is unprepared to set a preset frequency: Prepare the radio to set a primary
	* preset frequency.
	*
	* If the radio is prepared to set a primary preset frequency: Prepare the radio to set a
	* secondary preset frequency.
	*
	* If the radio is prepared to set a secondary preset frequency: Unprepare the radio to set
    * a preset frequency.
	*
	* The factory default setting is unprepared to set a preset frequency.
	*
	* After powering the radio on or off, toggling the frequency band, changing the tuned
	* frequency (either through tuning or seeking), or setting a primary or secondary preset,
	* the radio is unprepared to set a preset frequency.
	*
    */
    public void setBtn() {
		counter++;
		
		
		switch(counter){
                
			case 1:
                set = "SET1";
		 	    break;
		 	    
			case 2:
                set = "SET2";
	 	    	break;
	 	    	
			case 3:
                set = "SET3";
 	    		break;
 	    		
			case 4:
                set = "SET4";
	    		break;
	    		
			case 5:
                set = "SET5";
 				break;
                
			default:
				counter = 5;
				//setBtn();
				break;
		}
	}
    
    
	
	
	
	/**
    * Decreases the tuned frequency until a viable station is tuned.
	* Seeks through decreasing frequencies (and "wrapping" to the maximum frequency after
	* seeking past the minimum frequency for the current frequency band) for a viable station
    * A station is viable if the StationData object with which the car radio was constructed
	* returns a non-null String from the lookupFreq method.
	*
	* If no viable stations are found after seeking through all frequencies, then the car 
    * radio remains tuned to the original frequency.
	*
    */
    public void seekDownBtn() {
		
		tuneDownBtn();
		
		if(channel == "****" ){
			
			while( channel == "****")
				tuneDownBtn();
            
		}
	}
    
	
	
	
	/** 
    * Increases the tuned frequency until a viable station is tuned.
	* Seeks through increasing frequencies (and "wrapping" to the minimum frequency after seeking
	* past the maximum frequency for the current frequency band) for a viable station. A station is
	* viable if the StationData object with which the car radio was constructed returns a non-null
	* String from the lookupFreq method.
	*
	* If no viable stations are found after seeking through all frequencies, then the car radio
	* remains tuned to the original frequency.
	*
    */
    public void seekUpBtn() {
        
		tuneUpBtn();
		
		if(channel == "****" ){
			
			while( channel == "****")
				tuneUpBtn();
			
		}
	}
    
	
	
	
	/**	
    * Decreases the tuned frequency by one unit.
	* While in the AM band, the frequency is decreased by 10kHz. The minimum AM
	* band frequency is 520kHz; decreasing the frequency beyond the minimum
	* frequency "wraps" to the maximum frequency (1610kHz).
	*
	* While in the FM band, the frequency is decreased by 200kHz. The minimum FM
	* band frequency is 87.9MHz; decreasing the frequency beyond the minimum
	* frequency "wraps" to the maximum frequency (107.9MHz).
	*
    */
    public void tuneDownBtn() {
        
		if( band.contains("AM")){
		   	if( frequencyAM > FreqBand.AM.minFreq()){
			   	frequencyAM = frequencyAM - FreqBand.AM.spacing();
				this.frequency = String.valueOf(frequencyAM);
			}else {
				frequencyAM = FreqBand.AM.maxFreq();
				this.frequency = String.valueOf(frequencyAM);
			}
			
			char[] fm = frequency.toCharArray();
			int i=0;
			
			if( fm.length>= 4)
				frequenciesAM = "   ";
			else
				frequenciesAM = "    ";
            
            
			while(i != fm.length){
                
				frequenciesAM = frequenciesAM + fm[i];
				i++;
			}
			frequency = frequenciesAM;
            
			
			channelAM = StationData.BostonMA.lookupFreq(FreqBand.AM, frequencyAM);
			if( channelAM == null)
				channelAM = "****";
			
			channel = channelAM;
            
		}
		if(band.contains("FM")){
			if( frequencyFM > FreqBand.FM.minFreq()){
				frequencyFM = frequencyFM - FreqBand.FM.spacing();
				this.frequency = String.valueOf(frequencyFM);
			} else {
				frequencyFM = FreqBand.FM.maxFreq();
				this.frequency = String.valueOf(frequencyFM);
			}
            
			char[] fm = frequency.toCharArray();
			int i = fm.length - 1 ;
            
			if( fm[i] == '0' ){
				
				while( fm[i] == '0')
					i--;
				
				frequenciesFM = "." + fm[i]   ;
				i--;
				
				while( i != -1){
					frequenciesFM = fm[i] + frequenciesFM;
					i--;
				}
			}
			else{
				frequenciesFM  = null;
				
				while( i != -1){
					frequenciesFM = fm[i] + frequenciesFM;
					i--;
				}
                
			}
			if( fm.length >= 6 )
				frequenciesFM = "  " + frequenciesFM;
			else
				frequenciesFM = "   " + frequenciesFM;
            
			frequency = frequenciesFM;
			channelFM = StationData.BostonMA.lookupFreq(FreqBand.FM, frequencyFM);
			
			if( channelFM == null)
				channelFM = "****";
			
			channel = channelFM;
            
		}
	}
    
	
	
	
	/**
    * Increases the tuned frequency by one unit.
	* While in the AM band, the frequency is increased by 10kHz. The maximum AM
	* band frequency is 1610kHz; increasing the frequency beyond the maximum
	* frequency "wraps" to the minimum frequency (520kHz).
    *
	* While in the FM band, the frequency is increased by 200kHz. The maximum FM
	* band frequency is 107.9MHz; increasing the frequency beyond the maximum
	* frequency "wraps" to the minimum frequency (87.9MHz).
	*
	* The factory default setting for the frequency when in the AM band is the
	* minimum frequency (520kHz). The factory default setting for the frequency
	* when in the FM band is the minimum frequency (87.9MHz).
	*
    */
    public void tuneUpBtn() {
		
        if( band.contains("AM")){
            if( frequencyAM < FreqBand.AM.maxFreq()){
                frequencyAM = frequencyAM + FreqBand.AM.spacing();
                this.frequency = String.valueOf(frequencyAM);
                //char[] fm = frequency.toCharArray();
            } else {
                frequencyAM = FreqBand.AM.minFreq();
                this.frequency = String.valueOf(frequencyAM);
            }
			
            char[] fm = frequency.toCharArray();
            int i=0;
			
            if( fm.length>= 4){
                frequenciesAM = "   ";
            }
            else{
                frequenciesAM = "    ";
            }
            
            while(i != fm.length){
                
                frequenciesAM = frequenciesAM + fm[i];
                i++;
            }
            frequency = frequenciesAM;
            
			
            channelAM = StationData.BostonMA.lookupFreq(FreqBand.AM, frequencyAM);
            if( channelAM == null)
                channelAM = "****";
			
            channel = channelAM;
        }
        if(band.contains("FM")){
            if( frequencyFM < FreqBand.FM.maxFreq()){
                frequencyFM = frequencyFM + FreqBand.FM.spacing();
                this.frequency = String.valueOf(frequencyFM);
            } else {
                frequencyFM = FreqBand.FM.minFreq();
                this.frequency = String.valueOf(frequencyFM);
            }
            
            char[] fm = frequency.toCharArray();
            int i = fm.length - 1 ;
            
            if( fm[i] == '0' ){
                
                while( fm[i] == '0')
                    i--;
                
                frequenciesFM = "." + fm[i];
                i--;
                
                while( i != -1){
                    
                    frequenciesFM = fm[i] + frequenciesFM;
                    i--;
                }
            }
            else{
                frequenciesFM  = null;
                while( i != -1){
                    
                    frequenciesFM = fm[i] + frequenciesFM;
                    i--;
                }
            }
            if( fm.length >= 6){
                String fix = "  ";
                frequenciesFM = fix + frequenciesFM;
            }else{
                String fix = "   ";
                frequenciesFM = fix + frequenciesFM;
            }
            frequency = frequenciesFM;
			
            channelFM = StationData.BostonMA.lookupFreq(FreqBand.FM, frequencyFM);
            if( channelFM == null)
                channelFM = "****";
			
            channel = channelFM;
		}
	}
    
	
	
	/**
    * Toggle the frequency band of the car radio.
    * When changing from AM band to FM band, the car radio returns to the
	* last frequency tuned while in the FM band. Similarly, when changing
	* from FM band to AM band, the car radio returns to the last frequency
	* tuned while in the AM band.
    *
    * The factory default setting for the frequency band is the AM band.
	*
    */
    public void amfmBtn() {
		
        if( band.contains("AM")){
            
            band = "FM";
            frequency = frequenciesFM;
            channel = channelFM;
            
        }
        else{
            
            band = "AM";
            frequency = frequenciesAM;
            channel = channelAM;
            
		}
	}
    
	
	
	/**
    * Return the car radio display.
	* Returns:
	* A sequence of four strings depicting the current car radio state.
	* Examples:
	*	 ---------------------
	*	 |                   |
	*	 |                   |
	*	 ---------------------
	*
	*	 ---------------------
	*	 |  AM   1610  ****  |
	*	 |  Vol: --          |
	*	 ---------------------
	*
	*	 ---------------------
	*	 |  FM   91.5  WXXI  |
	*	 |  Vol: 10    SET1  |
	*	 ---------------------
	*
	* The format of the display is as follows:
	*
	* If the power is off, then:
	* Line 1: A string of 21 '-' characters.
	* Lines 2 and 3: A string of 21 characters, comprised of a '|' character,
	* 19 spaces, and a '|' character.
	* Line 4: A string of 21 '-' characters.
	* If the power is on, then:
	* Line 1: A string of 21 '-' characters.
	* Line 2: A string of 21 characters, comprised of a '|' character, two spaces,
	* either the string "AM" or the string "FM" (reflecting the current frequency band),
    * sufficient spaces to right justify the current frequency in column 12, the tuned
	* frequency, two spaces, the station id if tuned to a viable station or the string
	* "****" if not tuned to a viable station, two spaces, and a '|' character.
	* Line 3: A string of 21 characters, comprised of a '|' character, two spaces,
	* the string "Vol:", sufficient spaces to right justify the current volume in column
	* 10, the current volume if not muted and the string "--" if muted, four spaces,
	* either four spaces if the radio is unprepared to set a preset frequency or the
    * string "SET1" if the radio is prepared to set a primary preset frequency or the
	* string "SET2" if the radio is prepared to set a secondary preset frequency, two
    * spaces, and a '|' character.
	* Line 4: A string of 21 '-' characters.
	*
    */
    public List<String> display() throws IOException {
		
        CarRadio test = new CarRadio(StationData.RochesterNY);
        FileWriter fw = new FileWriter("output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        scanner = new Scanner(file);
        
        try {
            
            while (scanner.hasNextLine()) {
                test.line = scanner.nextLine();
                
                if(test.line.contains("BostonMA"))
                    bw.write("\n\nLocation :  ");
                else if(test.line.contains("RochesterNY"))
                    bw.write("\n\nLocation :  ");
                else if(test.line.contains("DeathValleyCA"))
                    bw.write("\n\nLocation :  ");
                else if(test.line.contains("NewYorkNY"))
                    bw.write("\n\nLocation :  ");
                else
                    bw.write("\n\nCommand :  ");
                
                
                System.out.println(test.line);
                bw.write(test.line);
                
                if ( test.line.equals("power"))
                    test.powerBtn();
                
                if (test.line.equals("amfm"))
                    test.amfmBtn();
                
                if ( test.line.equals("tuneUp"))
                    test.tuneUpBtn();
                
                if ( test.line.equals("tuneDown"))
                    test.tuneDownBtn();
                
                if ( test.line.equals("seekUp"))
                    test.seekUpBtn();
                
                if ( test.line.equals("seekDown"))
                    test.seekDownBtn();
                
                if ( test.line.equals("volumeUp"))
                    test.volumeUpBtn();
                
                if ( test.line.equals("set"))
                    test.setBtn();
                
                if ( test.line.equals("preset1"))
                    test.preset1Btn();
                
                if ( test.line.equals("preset2"))
                    test.preset2Btn();
                
                if ( test.line.equals("preset3"))
                    test.preset3Btn();
                
                if ( test.line.equals("preset4"))
                    test.preset2Btn();
                
                if ( test.line.equals("preset5"))
                    test.preset2Btn();
                
                if ( test.line.equals("volumeDown"))
                    test.volumeDownBtn();
                
                if(!test.power){
                    bw.write("\n---------------------\n|                   |");
                    bw.write("\n|                   |\n---------------------\n");
                    test.sets.add("\n---------------------\n|                   |"
                                  + "\n|                   |\n---------------------\n");
                }else{
                    bw.write("\n---------------------\n|  " + test.band +
                             test.frequency + "  " + test.channel +"  |");
                    bw.write("\n|  Vol: " + test.volumenT + "    " + test.set +
                             "  |\n---------------------\n");
        			
                    test.sets.add("\n---------------------\n|  " + test.band +  
                                  test.frequency + "  " + test.channel +"  |"
                                  + "\n|  Vol: " + test.volumenT + "    " + test.set + 
                                  "  |\n---------------------\n");
                }
                
            }
            
            bw.close();
            CarRadio.scanner.close();  
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return sets;
	}
	
	
	
	public static void main(String[] args) throws IOException{
	  
		CarRadio test = new CarRadio(StationData.RochesterNY);

        test.display();
	}

}
