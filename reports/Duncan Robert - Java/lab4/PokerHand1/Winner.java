/*
 * Winner.java
 *
 * Version:
 * $Id: Winner.java,v 1.1 2013/04/07 18:45:29 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Winner.java,v $
 * Revision 1.1  2013/04/07 18:45:29  pzg8794
 *
 */

import java.util.Scanner;

/**
 * A class used to contain a list of human(s) and computer(s) players
 * that have already won porker hands.
 *
 * @author pzg: Piter Garcia
 */
 public class Winner extends Player {

     /**
      * constructor to initialize hand poker winners.
      */
      public Winner(String name, int value) {
		super(name, value);
      }

}
