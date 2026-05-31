/*
 * CarRadio.java
 *
 * Version:
 *  $Id: CarRadio.java,v 1.2 2013/03/20 03:30:44 pzg8794 Exp $
 * Revisions:
 *  $Log: CarRadio.java,v $
 *  Revision 1.2  2013/03/20 03:30:44  pzg8794
 *  *** empty log message ***
 *
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
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Five Main Pieces of States that belong to this radios  		*
     * 																*
     * @param volume, adjusts the volume of the radio.   			*
     * @param band, switches from AM to FM and the other way around. *
     * @param power, turns the radio on and off 						*
     * @param channel, the frequency whose station is available. 	*
     * @param frequency, contains the current AM or FM frequency	    *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private int volumen;
	private String band;
	private boolean power;
	private String channel;
	private String frequency;
    
    
	
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Five Main Parameters used to perform the public behaviors 	*
     * that belong to this radio.									*
     * 																*
     * @param mute,sets the volume to zero - represented here as"--".*
     * @param unMute, sets the volume back to its previous volume 	*
     * @param power, turns the radio on and off 						*
     * @param volumenT, used as a container to display current volume*
     * @param frequencyAM, used to contain the current AM frequency  *
     * @param frequencyFM, used to contain the current FM frequency  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private boolean mute;
	private String unMute;
	private String volumenT;
	private int frequencyAM;
	private int frequencyFM;
    
    
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Five Main Parameters used to perform the public behaviors 	*
     * that belong to this radio.									*
     * 																*
     * @param set,sets the volume to zero - represented here as"--". *
     * @param channelAM, contains the current AM channel in the radio*
     * @param channelFM, contains the current FM channel in the radio*
     * @param frequenciesAM,container to display current AM frequency*
     * @param frequenciesFM,container to display current FM frequency*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private String set;
	private String channelAM;
	private String channelFM;
	private String frequenciesAM;
	private String frequenciesFM;
	
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Counters to keep track of how many times the set button and the preset  *
     * buttons have been pressed in the AM band that belong to this radio.	  *
     * 																          *
     * @param counterAM,  contains the no. of times button set was pressed     *
     * @param counter1AM, contains the no. of times button preset1 was pressed *
     * @param counter2AM, contains the no. of times button preset2 was pressed *
     * @param counter3AM, contains the no. of times button preset3 was pressed *
     * @param counter4AM, contains the no. of times button preset4 was pressed *
     * @param counter5AM, contains the no. of times button preset5 was pressed *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private int counterAM;
	private int counter1AM;
	private int counter2AM;
	private int counter3AM;
	private int counter4AM;
	private int counter5AM;
    
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Counters to keep track of how many times the set button and the preset  *
     * buttons have been pressed in the FM band	that belong to the radio      *
     * 																          *
     * @param counterFM,  contains the no. of times button set was pressed     *
     * @param counter1FM, contains the no. of times button preset1 was pressed *
     * @param counter2FM, contains the no. of times button preset2 was pressed *
     * @param counter3FM, contains the no. of times button preset3 was pressed *
     * @param counter4FM, contains the no. of times button preset4 was pressed *
     * @param counter5FM, contains the no. of times button preset5 was pressed *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private int counterFM;
	private int counter1FM;
	private int counter2FM;
	private int counter3FM;
	private int counter4FM;
	private int counter5FM;
	
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * variables to keep track of all preset primary frequencies in AM/FM*														      *
     * 																    *
     * @param preset1PrimaryFreq, contains primary frequency of preset1  *
     * @param preset2PrimaryFreq, contains primary frequency of preset2  *
     * @param preset3PrimaryFreq, contains primary frequency of preset3  *
     * @param preset4PrimaryFreq, contains primary frequency of preset4  *
     * @param preset5PrimaryFreq, contains primary frequency of preset5  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private int preset1PrimaryFreq;
	private int preset2PrimaryFreq;
	private int preset3PrimaryFreq;
	private int preset4PrimaryFreq;
	private int preset5PrimaryFreq;
	
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * variables to keep track of all preset secondary frequencies  AM/FM*														      *
     * 																    *
     * @param preset1PrimaryFreq, contains primary frequency of preset1  *
     * @param preset2PrimaryFreq, contains primary frequency of preset2  *
     * @param preset3PrimaryFreq, contains primary frequency of preset3  *
     * @param preset4PrimaryFreq, contains primary frequency of preset4  *
     * @param preset5PrimaryFreq, contains primary frequency of preset5  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private int preset1SecundaryFreq;
	private int preset2SecundaryFreq;
	private int preset3SecundaryFreq;
	private int preset4SecundaryFreq;
	private int preset5SecundaryFreq;
	
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Arrays that contains all primary and secondaries AM frequencies   *
     * 																    *
     * @param primaryFrequenciesAM, contains 5primary frequencies of     *
     * preset 1, 2, 3, 4, and 5.                                         *
     * @param secundaryFrequenciesAM, contains 5 secondary frequencies of*
     * preset 1, 2, 3, 4, and 5.                                         *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private List<Object>primaryFrequenciesAM = new ArrayList<Object>();
	private List<Object>primaryFrequenciesFM = new ArrayList<Object>();
	
	
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Arrays that contains all primary and secondaries FM frequencies   *
     * 																    *
     * @param primaryFrequenciesFM, contains 5primary frequencies of     *
     * preset 1, 2, 3, 4, and 5.                                         *
     * @param secundaryFrequenciesFM, contains 5 secondary frequencies of*
     * preset 1, 2, 3, 4, and 5.                                         *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private List<Object>secundaryFrequenciesAM = new ArrayList<Object>();
	private List<Object>secundaryFrequenciesFM = new ArrayList<Object>();
	
    /*
     * List of Command done by the radio
     */
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
	 * Input and Output files to save the radio response according to the test.
	 */
	private String inputFile;
	private String outputFile;
    
    
	/**
     * Construct a car radio with factory default settings and with the supplied
     * station data.
     *
     * @param sd  The local station data, used to determine viable stations and
     * station ids.
     */
	public CarRadio(StationData sd) {
		
		// initializing power, volume, and frequencies and their
		//depended variables.
		power = false;
		mute = false;
		volumen = 0;
		band = "AM";
		
		volumenT = " 0";
		set = "    ";
        
		
		channel = "****";
		channelAM = "****";
		channelFM = "****";
		
		frequency = "    " + String.valueOf(FreqBand.AM.minFreq());
		frequencyAM = FreqBand.AM.minFreq();
		frequencyFM = FreqBand.FM.minFreq();
		frequenciesAM = "    520";
		frequenciesFM = "   87.9";
		
		//initializing buttons pressed counters.
		counter5AM = 0; counter5FM = 0;
		counter4AM = 0; counter4FM = 0;
		counter3AM = 0; counter3FM = 0;
		counter2AM = 0; counter2FM = 0;
		counter1FM = 0; counter1AM = 0;
		counterFM = 0;  counterAM = 0;
        
		//initializing primary and secondary frequencies of each preset button.
		int i = 0;
		while( i != 6 ){
			primaryFrequenciesAM.add(i, FreqBand.AM.minFreq()) ;
			primaryFrequenciesFM.add(i, FreqBand.FM.minFreq()) ;
			secundaryFrequenciesAM.add(i, FreqBand.AM.minFreq());
			secundaryFrequenciesFM.add(i, FreqBand.FM.minFreq());
            
			i++;
		}
		
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
        
		power = !power;
		
	}
    
	
	
	
	/**
     * Increases the car radio volume by one unit.The car radio has a maximum
     * volume of 20; attempts to increase the volume beyond the maximum volume
     * should not change the volume.The factory default setting for the volume is 0.
     *
     */
	public void volumeUpBtn() {
		
		if(power) { // if power is on adjust the volume.
            
            if( volumen < MAX_VOLUMEN)
                volumen++;
            else
                volumen = MAX_VOLUMEN;
            
            volumenT = String.valueOf(volumen);
            
            if( volumenT.length() < 2)
                volumenT= " " +  volumenT;
		}// if power is off the volume is not modified.
	}
    
	
	
	
	/**
     * Decreases the car radio volume by one unit.The car radio has a minimum
     * volume of 0; attempts to decrease the volume beyond the minimum volume should
     * not change the volume.The factory default setting for the volume is 0.
     *
     */
	public void volumeDownBtn() {
		
		if( volumen > MIN_VOLUMEN) // lower the volume till it reaches minimum.
			volumen--;
		else
			volumen = MIN_VOLUMEN;
		// if minimum volume was reached, volume stays minimum.
		
		volumenT = String.valueOf(volumen);
		// variable to display volume
		
		if( volumenT.length() < 2)
			volumenT= " " +  volumenT; //adjusting container for higher volume.
	}
    
	
    
	
	/**
     * Toggles the mute status of the car radio.When the car radio is muted, the
     * volume may be changed, but the car radio remains muted.The factory default
     * setting for the mute status is unmuted.
     *
     */
	public void muteBtn() {
		mute = !mute;
		
		if( mute){ // if muted was pressed change volume container.
			unMute = volumenT; // save actual volume for future use.
			volumenT = "--";
		}
		else {
			volumenT = unMute; // if unmuted return saved volume to the container.
			
		}
		
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
        
		char band = ' ';
		band = this.band.charAt(0);
        
		switch(band){ // check if the band is AM or FM.
                
			case 'A': // if AM keep track of the times pressed and its frequency
				
				counter5AM++;
				if( counter5AM == 1){
					primaryFrequenciesAM.add(5, primaryFrequenciesAM.get(0));
					preset5PrimaryFreq =  (Integer) primaryFrequenciesAM.get(0);
					// un-prepare the set button.
					set = "    ";
					counterAM = 0;
				}
				else if(counter5AM == 2){
					secundaryFrequenciesAM.add(5,secundaryFrequenciesAM.get(0));
					preset5SecundaryFreq =  (Integer) secundaryFrequenciesAM.get(5);
					// un-prepare the set button.
					set = "    ";
					counterAM = 0;
				}
				else { //Display primary and secondary frequencies
					
					if(counter5AM%2 == 1){
						frequencyAM = preset5PrimaryFreq;
						System.out.println(" Preset5 frequency = " + preset5PrimaryFreq);
					}else{
						
						frequencyAM = preset5SecundaryFreq;
						System.out.println(" Preset5 frequency = " + preset5SecundaryFreq);
						
					}
				}
                
		 	    break;
		 	    
			case 'F':// if FM keep track of the times pressed and its frequency
				
                counter5FM++;
				if( counter5FM == 1){
					primaryFrequenciesFM.add(5, primaryFrequenciesFM.get(0));
					preset5PrimaryFreq =  (Integer) primaryFrequenciesFM.get(5);
					// un-prepare the set button.
					set = "    ";
					counterFM = 0;
				}
				else if(counter5FM == 2){
					secundaryFrequenciesFM.add(5,secundaryFrequenciesFM.get(0));
					preset5SecundaryFreq= (Integer) secundaryFrequenciesFM.get(5);
					// un-prepare the set button.
					set = "    ";
					counterFM = 0;
				}
				else { //Display primary and secondary frequencies
                    
					if(counter5FM%2 == 1){
						frequencyFM = preset5PrimaryFreq;
						// un-prepare the set button.
						set = "    ";
						counterFM = 0;
					}else{
						
						frequencyFM = preset5SecundaryFreq;
						// un-prepare the set button.
						set = "    ";
						counterFM = 0;
					}
				}
                
				break;
			default:
				break;
		}
		
		//reset radio for better frequency display.
		if( frequencyFM > FreqBand.FM.maxFreq() || frequencyAM > FreqBand.AM.maxFreq()) {
			
			seekUpBtn();
			seekDownBtn();
			
		} else {
			
			seekDownBtn();
			seekUpBtn();
		}
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
		
		char band = ' ';
		band = this.band.charAt(0);
        
		switch(band){ // check if the band is AM or FM.
                
			case 'A': // if AM keep track of the times pressed and its frequency
                
				if (counterAM != 0 ){
                    
					counter4AM++;
					if( counter4AM == 1){
						primaryFrequenciesAM.add(4, primaryFrequenciesAM.get(0));
						preset4PrimaryFreq =  (Integer) primaryFrequenciesAM.get(4);
						// un-prepare the set button.
						set = "    ";
						counterAM = 0;
					}
					else if(counter4AM == 2){
						secundaryFrequenciesAM.add(4,secundaryFrequenciesAM.get(0));
						preset4SecundaryFreq= (Integer) secundaryFrequenciesFM.get(4);
						// un-prepare the set button.
						set = "    ";
						counterAM = 0;
					}
					else {
						
						if( counter4AM%2 == 1){
							frequencyAM = preset4PrimaryFreq;
							// un-prepare the set button.
							set = "    ";
							counterAM = 0;
						}else{
							frequencyAM = preset4SecundaryFreq;
							// un-prepare the set button.
							set = "    ";
							counterAM = 0;
						}
					}
				}else {
					frequencyAM = FreqBand.AM.minFreq();
				}
                
				break;
		 	    
			case 'F': // if FM keep track of the times pressed and its frequency
				
				if( counterFM != 0){
					
					counter4FM++;
					if( counter4FM == 1){
						primaryFrequenciesFM.add(4, primaryFrequenciesFM.get(0));
						preset4PrimaryFreq =  (Integer) primaryFrequenciesFM.get(0);
						// un-prepare the set button.
						set = "    ";
						counterFM = 0;
					}
					else if(counter4FM == 2){
						secundaryFrequenciesFM.add(4,secundaryFrequenciesFM.get(0));
						// un-prepare the set button.
						preset4SecundaryFreq = (Integer) secundaryFrequenciesFM.get(4);
                        
						set = "    ";
						counterFM = 0;
					}
					else {
                        
						if( counter4FM%2 == 1){
							frequencyFM = preset4PrimaryFreq;
							// un-prepare the set button.
							set = "    ";
							counterFM = 0;
						}else{
                            
							frequencyFM = preset4PrimaryFreq;
							// un-prepare the set button.
							set = "    ";
							counterFM = 0;
						}
					}
				}else {
					frequencyFM = FreqBand.FM.minFreq();
				}
	 	    	break;
			default:
				break;
		}
		
		//reset radio for better frequency display.
		if( frequencyFM > FreqBand.FM.maxFreq() || frequencyAM > FreqBand.AM.maxFreq()) {
			
			seekUpBtn();
			seekDownBtn();
			
		} else {
			
			seekDownBtn();
			seekUpBtn();
		}
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
		
        
		char band = ' ';
		band = this.band.charAt(0);
        
		switch(band){ // check if band is AM or FM.
                
			case 'A': // if AM keep track of the times pressed and its frequency
				
				counter3AM++;
				if( counter3AM == 1){
					primaryFrequenciesAM.add(3, primaryFrequenciesAM.get(0));
					preset3PrimaryFreq =  (Integer) primaryFrequenciesAM.get(0);
					// un-prepare the set button.
					set = "    ";
					counterAM = 0;
				}
				else if(counter3AM == 2){
					secundaryFrequenciesAM.add(3,secundaryFrequenciesAM.get(0));
					
					System.out.println(" counter3AM = " + counter3AM);
					System.out.println(" Preset3 frequency = " + secundaryFrequenciesAM.get(3));
					// un-prepare the set button.
					set = "    ";
					counterAM = 0;
				}
				else {
					
					if(  counter3AM%2 == 1){
						frequencyAM = preset3PrimaryFreq;
						// un-prepare the set button.
						set = "    ";
						counterAM = 0;
					}else {
						frequencyAM = preset3SecundaryFreq;
						// un-prepare the set button.
						set = "    ";
						counterAM = 0;
					}
				}
                
		 	    break;
                
			case 'F': // if FM keep track of the times pressed and its frequency
				counter3FM++;
				
				if( counter3FM == 1){
					primaryFrequenciesFM.add(3, primaryFrequenciesFM.get(0));
					preset3PrimaryFreq =  (Integer) primaryFrequenciesFM.get(3);
					// un-prepare the set button.
					set = "    ";
					counterFM = 0;
				}
				else if(counter3FM == 2){
					secundaryFrequenciesFM.add(3,secundaryFrequenciesFM.get(0));
					preset3SecundaryFreq = (Integer) secundaryFrequenciesFM.get(3);
					// un-prepare the set button.
					set = "    ";
					counterFM = 0;
				}
				else {
					
					if(  counter3FM%2 == 1){
						frequencyFM = preset3PrimaryFreq;
						// un-prepare the set button.
						set = "    ";
						counterFM = 0;
					}else {
						frequencyFM = preset3SecundaryFreq;
						// un-prepare the set button.
						set = "    ";
						counterFM = 0;
					}
                    
					if( frequencyFM > FreqBand.FM.maxFreq()) {
						
						seekUpBtn();
						seekDownBtn();
						
					} else {
						
						seekDownBtn();
						seekUpBtn();
					}
				}
                
	 	    	break;
			default:
				break;
		}
		
		//reset radio for better frequency display.
		if( frequencyFM > FreqBand.FM.maxFreq() || frequencyAM > FreqBand.AM.maxFreq()) {
			
			seekUpBtn();
			seekDownBtn();
			
		} else {
			
			seekDownBtn();
			seekUpBtn();
		}
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
        
		char band = ' ';
		band = this.band.charAt(0);
        
		switch(band) { // check if band is either AM or FM
				
            case 'A': // if AM keep track of the times pressed and its frequency
                
                counter2AM++;
                if(counter2AM == 1){
                    primaryFrequenciesAM.add(2, primaryFrequenciesAM.get(0));
                    preset2PrimaryFreq =  (Integer) primaryFrequenciesAM.get(2);
                    // un-prepare the set button.
                    set = "    ";
                    counterAM = 0;
                }
                else if(counter2AM == 2){
                    secundaryFrequenciesAM.add(2,secundaryFrequenciesAM.get(0));
                    preset2SecundaryFreq =  (Integer) secundaryFrequenciesAM.get(2);
                    // un-prepare the set button.
                    set = "    ";
                    counterAM = 0;
                }
                else {
                    
                    if(  counter2AM%2 == 1){
                        frequencyAM = preset2PrimaryFreq;
                        // un-prepare the set button.
                        set = "    ";
                        counterAM = 0;
                    }else {
                        frequencyAM = preset2SecundaryFreq;
                        // un-prepare the set button.
                        set = "    ";
                        counterAM = 0;
                    }
                }
                
                break;
                
            case 'F': // if FM keep track of the times pressed and its frequency
                counter2FM++;
                if( counter2FM == 1){
                    primaryFrequenciesFM.add(2, primaryFrequenciesFM.get(0));
                    preset2PrimaryFreq =  (Integer) primaryFrequenciesFM.get(2);
                    // un-prepare the set button.
                    set = "    ";
                    counterFM = 0;
                }
                else if(counter2FM == 2){
                    secundaryFrequenciesFM.add(2,secundaryFrequenciesFM.get(0));
                    preset2SecundaryFreq =  (Integer) secundaryFrequenciesFM.get(2);
                    // un-prepare the set button.
                    set = "    ";
                    counterFM = 0;
                }
                else {
                    
                    if(  counter2FM%2 == 1){
                        frequencyFM = preset2PrimaryFreq;
                        // un-prepare the set button.
                        set = "    ";
                        counterFM = 0;
                    }else {
                        frequencyFM = preset2SecundaryFreq;
                        // un-prepare the set button.
                        set = "    ";
                        counterFM = 0;
                    }
                }
                
                break;
                
			default:
				break;
		}
		
		//reset radio for better frequency display.
		if( frequencyFM > FreqBand.FM.maxFreq() || frequencyAM > FreqBand.AM.maxFreq()) {
			
			seekUpBtn();
			seekDownBtn();
			
		} else {
			
			seekDownBtn();
			seekUpBtn();
		}
		
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
        
		char band = ' ';
		band = this.band.charAt(0);
        
		switch(band){ // check if band is either AM or FM
                
			case 'A': // if AM keep track of the times pressed and its frequency
				counter1AM++;
				
				if( counter1AM == 1){
					primaryFrequenciesAM.add(1, primaryFrequenciesAM.get(0));
					preset1PrimaryFreq =  (Integer) primaryFrequenciesAM.get(1);
					// un-prepare the set button.
					set = "    ";
					counterAM = 0;
				}
				else if(counter1AM == 2){
					secundaryFrequenciesAM.add(1,secundaryFrequenciesAM.get(0));
					preset1SecundaryFreq =  (Integer) secundaryFrequenciesAM.get(1);
					// un-prepare the set button.
					set = "    ";
					counterAM = 0;
				}
				else {
					
					if( counter1AM == 4)
						counter1AM++;
					
					if( counter1AM%2 == 1){
						frequencyAM = preset1PrimaryFreq;
						// un-prepare the set button.
					}else {
						frequencyAM = preset1SecundaryFreq;
						// un-prepare the set button.
					}
				}
                
		 	    break;
		 	    
			case 'F': // if FM keep track of the times pressed and its frequency
				counter1FM++;
				
				if( counter1FM == 1){
					primaryFrequenciesFM.add(1, primaryFrequenciesFM.get(0));
					preset1PrimaryFreq =  (Integer) primaryFrequenciesFM.get(1);
					// un-prepare the set button.
					set = "    ";
					counterFM = 0;
				}
				else if(counter1FM == 2){
					secundaryFrequenciesFM.add(1,secundaryFrequenciesFM.get(0));
					preset1SecundaryFreq = (Integer) secundaryFrequenciesFM.get(1);
					// un-prepare the set button.
					set = "    ";
					counterFM = 0;
				}
				else {
					
					if( counter1FM == 4)
						counter1FM++;
					
					if( counter1FM%2 == 1){
						frequencyFM = preset1PrimaryFreq;
						// un-prepare the set button.
					}else {
						frequencyFM = preset1SecundaryFreq;
						// un-prepare the set button.
					}
					
				}
                
	 	    	break;
			default:
				break;
		}
		
		// reset radio for better frequency display.
		if( frequencyFM > FreqBand.FM.maxFreq() || frequencyAM > FreqBand.AM.maxFreq()) {
			
			seekUpBtn();
			seekDownBtn();
			
		} else {
			
			seekDownBtn();
			seekUpBtn();
		}
        
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
		char band = ' ';
		band = this.band.charAt(0);
        
		
		switch(band){ // check if band is either AM or FM.
                
			case 'A': // if AM prepare the band for preset frequencies
				counterAM++;
				
				if( counterAM == 1){ // preparing fot SET1
					primaryFrequenciesAM.add(0, frequencyAM);
					set = "SET1";
				}
				else if( counterAM == 2){ // preparing for SET2
					secundaryFrequenciesAM.add(0,frequencyAM);
					set = "SET2";
				}
				else
					counterAM = 0; // un-prepare the set button.
				
		 	    break;
		 	    
			case 'F': // if AM prepare the band for preset frequencies
				counterFM++;
				
				if( counterFM == 1){ //Preparing for SET1
					primaryFrequenciesFM.add(0, frequencyFM);
					set = "SET1";
				}
				else if( counterFM == 2){ //Preparing for SET2
					secundaryFrequenciesFM.add(0, frequencyFM);
					set = "SET2";
				}
				else
					counterFM=0; // un-prepare the set button.
				
	 	    	break;
                
			default:
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
		
		tuneDownBtn(); // tune the current frequency.
		
		if(channel == "****" ){ // if the current frequency is not available then,
			
			while( channel == "****") // tune down frequencies till an available one is found.
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
        
		tuneUpBtn();// tune the current frequency.
		
		if(channel == "****" ){ // if the current frequency is not available then,
			
			while( channel == "****") // tune up frequencies till an available one is found.
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
			// if the band is AM save the frequency in frequencyAM and make a
			// nice looking copy of this for a better display.
			
		   	if( frequencyAM > FreqBand.AM.minFreq()){
			   	frequencyAM = frequencyAM - FreqBand.AM.spacing();
				this.frequency = String.valueOf(frequencyAM);
			}else {
				frequencyAM = FreqBand.AM.maxFreq();
				this.frequency = String.valueOf(frequencyAM);
			}
			
		   	// making the current frequency look nice.
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
            
			//looking for the channel corresponding to such frequency.
			channelAM = StationData.BostonMA.lookupFreq(FreqBand.AM, frequencyAM);
			if( channelAM == null)
				channelAM = "****";
			//if none channel is found, frequency is unavailable = "****".
			channel = channelAM;
            
		}
		if(band.contains("FM")){
			// if the band is FM save the frequency in frequencyAM and make a
			// nice looking copy of this for a better display.
			if( frequencyFM > FreqBand.FM.minFreq()){
				frequencyFM = frequencyFM - FreqBand.FM.spacing();
				this.frequency = String.valueOf(frequencyFM);
			} else {
				frequencyFM = FreqBand.FM.maxFreq();
				this.frequency = String.valueOf(frequencyFM);
			}
            
			// making the current frequency look nice.
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
			
			//looking for the channel corresponding to such frequency.
			channelFM = StationData.BostonMA.lookupFreq(FreqBand.FM, frequencyFM);
			
			if( channelFM == null)
				channelFM = "****";
			//if none channel is found, frequency is unavailable = "****".
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
            // if the band is AM save the frequency in frequencyAM and make a
            // nice looking copy of this for a better display.
            if( frequencyAM < FreqBand.AM.maxFreq()){
                frequencyAM = frequencyAM + FreqBand.AM.spacing();
                this.frequency = String.valueOf(frequencyAM);
            } else {
                frequencyAM = FreqBand.AM.minFreq();
                this.frequency = String.valueOf(frequencyAM);
            }
			
            // making current frequency look nice
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
            
            //looking for the channel corresponding to such frequency.
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
            //if none channel is found, frequency is unavailable = "****".
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
		
        // swithing for AM to FM, or the other way around
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
     *@exception IOException to check for error while reading fromt input file.
     */
	public List<String> display() throws IOException {
        
        FileWriter fw = new FileWriter(outputFile);
        // saving radio commands output in a file named output.
        BufferedWriter bw = new BufferedWriter(fw);
        // Location of file to read
        File file = new File(inputFile);
        Scanner scanner = new Scanner(file);
        //container to save commands output into an array.
        String line;
        
        
        
        try {
            
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                
                if(line.contains("BostonMA"))
                    bw.write("\n\nLocation :  ");
                else if(line.contains("RochesterNY"))
                    bw.write("\n\nLocation :  ");
                else if(line.contains("DeathValleyCA"))
                    bw.write("\n\nLocation :  ");
                else if(line.contains("NewYorkNY"))
                    bw.write("\n\nLocation :  ");
                else
                    bw.write("\n\nCommand :  ");
                
                // System.out.println(line);
                bw.write(line);
                
                if ( line.equals("power"))
                    powerBtn();
                
                if (line.equals("amfm"))
                    amfmBtn();
                
                if ( line.equals("tuneUp"))
                    tuneUpBtn();
                
                if ( line.equals("tuneDown"))
                    tuneDownBtn();
                
                if ( line.equals("seekUp"))
                    seekUpBtn();
                
                if ( line.equals("seekDown"))
                    seekDownBtn();
                
                if ( line.equals("volumeUp"))
                    volumeUpBtn();
                
                if ( line.equals("set"))
                    setBtn();
                
                if ( line.equals("preset1")){
                    preset1Btn();
                }
                
                if ( line.equals("preset2"))
                    preset2Btn();
                
                if ( line.equals("preset3"))
                    preset3Btn();
                
                if ( line.equals("preset4"))
                    preset4Btn();
                
                if ( line.equals("preset5"))
                    preset5Btn();
                
                if ( line.equals("volumeDown"))
                    volumeDownBtn();
                
                if ( line.equals("mute"))
                    muteBtn();
                
                if(!power){
                    bw.write("\n---------------------\n|                   |");
                    bw.write("\n|                   |\n---------------------\n");
                    sets.add("\n---------------------\n|                   |"
                             + "\n|                   |\n---------------------\n");
                }else{
                    bw.write("\n---------------------\n|  " + band +
                             frequency + "  " + channel +"  |");
                    bw.write("\n|  Vol: " + volumenT + "    " + set +
                             "  |\n---------------------\n");
        			
                    sets.add("\n---------------------\n|  " + band +
                             frequency + "  " + channel +"  |"
                             + "\n|  Vol: " + volumenT + "    " + set +
                             "  |\n---------------------\n");
                }
            }
            
            bw.close();
            scanner.close();  
            System.out.print(sets.toString());
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return sets;
	}
	
	
	
    /**
     * The main method, it calls the display() method to perform the radio
     * buttons behavior according to the input-file content that is supposed
     * to simulate a sequence of commands given by different button types.
     * @param args command line arguments (ignored).
     */	
	public static void main(String[] args) throws IOException{
        
		CarRadio test = new CarRadio(StationData.RochesterNY);
		Scanner menue1 = new Scanner(System.in);
		
		
        System.out.println("\nWELCOME! \n");
		System.out.println("For A Complete Test I Devided the Test Files As " +
                           "Those Provided For The Lab:");
		System.out.println("\n    InputFiles                OutputFiles\n" +
                           "------------------------------------------------\n" +
                           "1) test-power.input         test-power.output" +
                           "\n2) test-volume1.input       test-volume1.output " +
                           "\n3) test-tune3.input         test-tune3.output " +
                           "\n4) test-all.input           test-all.output");
        
		System.out.println("\nPlease Enter Its Respected Number:");
        
		// performing radio different tests with the given input files given
		// for this lab.
		switch(menue1.nextInt()){
            case 1:
                test.inputFile = "test-power.input.txt";
                test.outputFile = "test-power.output.txt";
                break;
            case 2:
                test.inputFile = "test-volume1.input.txt";
                test.outputFile ="test-volume1.output.txt";
                break;
            case 3:
                test.inputFile = "test-tune3.input.txt";
                test.outputFile ="test-tune3.output.txt";
                break;
            case 4:
                test.inputFile = "test-all.input.txt";
                test.outputFile ="test-all.output.txt";
                break;
                
			default:
				System.out.println("Sorry, Wrong Input File \n Try Again!");
				
		}
        
        test.display();
        System.out.println("\nNote: \nTest Results Have Also Been Saved In A " + 
                           "File Named :" + test.outputFile);
	}
    
}
