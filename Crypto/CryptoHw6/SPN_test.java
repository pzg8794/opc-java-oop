import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

// SPNtest: test SPN encryption
public class SPN_test {
	public static void main(String[] args) {
		// for 128-bit key, use 16, 16, and 4 below
		// for 192-bit key, use 16, 24 and 6 below
		// for 256-bit key, use 16, 32 and 8 below
		InputRandom inRan = new InputRandom();

		int keySize = (int) (8*(1/(0.5*0.5)));
		int size = keySize/4;
		try {
			inRan.generateKeySet(size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		KeyRandomGen keyG = new KeyRandomGen();
		int keySize2 = 80;
		size = keySize2/4;
		try {
			keyG.generateKeySet(size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();

		GetBytes getInput = new GetBytes("input.txt", 16);
		byte[] in = getInput.getBytes();
		GetBytes getKey = new GetBytes("key.txt", 80);
		byte[] key = getKey.getBytes();
		SPNencrypt SPN = new SPNencrypt(key, 4);
		Print.printArray("Plaintext: ", in);
		Print.printArray("Key: ", key);
		byte[] out = new byte[16];
		SPN.Cipher(in, out);
		Print.printArray("Ciphertext: ", out);
	}
}