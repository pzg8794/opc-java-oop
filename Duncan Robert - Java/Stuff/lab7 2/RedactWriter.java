/* redact.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;

public class RedactWriter{
		
		private Writer wr;
		private BufferedWriter buffer;	    
		private String Word = "";
		private LinkedList<String> keywords = new LinkedList<String>();
		
		/**
		 * puts ignore words into a list.
		 * @param redacts
		 * @param w
		 */
		RedactWriter(Writer w, Collection<String> redacts) {
			this.wr = w;
		    keywords.addAll(redacts);
		}
		
		/**
		 * closes out buff
		 */
		public void	close() {
			this.flush();
			try {
				this.buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
		/**
		 * flush out buff
		 */
		public void	flush() {
			try {
				this.buffer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
		
		
		/**
		 * The purpose of this method is to compare strings and will write over
		 *  with x's when it is key word.
		 * @throws (IOException)
		 * @param ch, a character read in
		 * 
		 * 
		 */
		public void	write(int ch) throws IOException {
			char x = (char) ch;
			if(Character.isLetter(ch)){
				Word +=  x;
			}
			else{
				if( keywords.contains(Word)){
					for( int i=0; i< Word.length(); i++){
						wr.write("X");
					}
				}else{
						
					wr.write(Word);
				}
					
				wr.write((char)ch);
				wr.flush();
				Word = "";
			}
		}

	}
	
