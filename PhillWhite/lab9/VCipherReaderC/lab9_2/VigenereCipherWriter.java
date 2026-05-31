import java.io.IOException;
import java.io.Writer;

/**
 *@author Ashita Khetan
 * id: ayk5245
 * 
 * @author Mitali Satam
 * id:mms6887
 * 
 * A Vigenere cipher writer.
 */
public class VigenereCipherWriter extends Writer{
    
    private Writer w;
    private String key;
    private int cnt = 0;
    
    /**
     * Creates a new Vigenere cipher writer.
     * 
     * @param wr
     * @param keyword
     * @throws IllegalArgumentException 
     */
    public VigenereCipherWriter(Writer wr, String keyword)
                     throws IllegalArgumentException{
        w = wr;
        key = keyword;
    }
    
    /**
     * Encodes a single character and writes it to the underlying Writer.
     * 
     * @param ci
     * @throws IOException 
     */
    @Override
    public void write(int ci) throws IOException{
        int str;
        int k;
        if(97<=ci && ci<=122){
            str = ci - 97;
            k = (int)(key.charAt(cnt%key.length()))-97;
            str = ((str + k)%26) + 97;
            w.write((char)str);
//            System.out.print((char)str);
        }
        else if(65<=ci && ci<=90){
            str = ci - 65;
            k = (int)(key.charAt(cnt%key.length()))-97;
//            System.out.print(k);
            str = ((str + k)%26)+ 65;
            w.write((char)str);
//            System.out.print((char)str);
        }
        else if((char)ci == ' '){
            w.write((char)ci);
        }
        else{
            w.write((char)ci);
        }
        cnt++;
    }
    
    /**
     * Encodes a portion of an array and writes the encoded portion to the 
     * underlying OutputStream.
     * 
     * @param cbuf
     * @param off
     * @param len
     * @throws IOException 
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException{
        for(int i=0; i<len; i++){
            write(cbuf[off+i]);
        }        
    }
    
    /**
     * Flushes the stream.
     * 
     * @throws IOException 
     */
    @Override
    public void flush() throws IOException{
        w.flush();
    }
    
    /**
     * Closes the stream, flushing it first.
     * 
     * @throws IOException 
     */
    @Override
    public void close() throws IOException{
        w.close();
    }
}
