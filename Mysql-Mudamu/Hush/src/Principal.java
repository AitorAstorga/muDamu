import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Principal {

	public static void main(String[] args) {
		String s = new String();
		s = "testabcd";
		try {
			s = MungPass(s);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
	}
	
	public static String MungPass(String pass) throws NoSuchAlgorithmException { 
	    MessageDigest m = MessageDigest.getInstance("MD5"); 
	    byte[] data = pass.getBytes(); 
	    m.update(data,0,data.length); 
	    BigInteger i = new BigInteger(1,m.digest()); 
	    return String.format("%1$032X", i); 
	} 

}
