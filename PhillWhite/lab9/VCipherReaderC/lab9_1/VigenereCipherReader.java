import java.io.IOException;
import java.io.Reader;

/**
 * @author Ashita Khetan
 * id: ayk5245
 * 
 * @author Mitali Satam
 * id:mms6887
 * 
 * A Vigenere cipher reader.
 */
public class VigenereCipherReader extends Reader{
    private Reader r;
    private String key;
    private int cnt = 0;
    /**
     * Creates a new Vigenere cipher reader.
     * 
     * @param rd
     * @param keyword
     * @throws IllegalArgumentException 
     */
    public VigenereCipherReader(Reader rd, String keyword)
            throws IllegalArgumentException, IOException{
        r = rd;
        key = keyword;
//        int c;
//        while ((c = r.read()) != -1) {
//                System.out.print((char)c);
//            }
    }
    
    
    /**
     * Reads a single character from the underlying Reader, decodes it, 
     * and returns it.
     * 
     * @return
     * @throws IOException 
     */
    @Override
    public int read() throws IOException{
        int c = this.r.read();
        int k;
        k = (int)(key.charAt(cnt%key.length()))-97;
        if(97<=c && c<=122){
                c = c-97;
                c = (c-k)%26;
                if(c<0){
                    c = c + 26;
                }
                c = c + 97;
                cnt++;
                return c;
            }
            else if(65<=c && c<=90){
                c = c-65;
                c = (c-k)%26;
                if(c<0){
                    c = c + 26;
                }
                c = c + 65;
                cnt++;
                return c;
            }
            else if((char)c == ' '){
                cnt++;
                return c;
            }
            else{
                cnt++;
                return c;
            }
        
        }
  
            
    
    /**
     * Reads characters from the underlying Reader into a portion of an array 
     * and decodes the read portion.
     * 
     * @param cbuf
     * @param off
     * @param len
     * @return
     * @throws IOException 
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException{
        
        return 0;
        
    }
    
    
    /**
     * Tells whether this stream is ready to be read.
     * 
     * @return
     * @throws IOException 
     */
    @Override
    public boolean ready() throws IOException{
        return r.ready();
        
    }
    
    /**
     * Tells whether this stream supports the mark operation.
     * 
     * @return 
     */
    @Override
    public boolean markSupported(){
        return false;
        
    }
    
    /**
     * Marks the present position in the stream.
     * 
     * @param readAheadLimit
     * @throws IOException 
     */
    @Override
    public void mark(int readAheadLimit) throws IOException{
        
    }
    
    /**
     * Resets the stream.
     * 
     * @throws IOException 
     */
    @Override
    public void reset() throws IOException{
        
    }
    
    /**
     * Closes the stream and releases any system resources associated with it.
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException{
        r.close();
    }
    
}
