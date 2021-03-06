import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;

class J {
  private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

  public static void main(String[] args) {
    String req = args[0];
    String salt = args[1];

    System.out.println("value: " + req + " salt: " + salt + " encrypted: " + encrypt(salt, req));
  }

  private static String getSalt(byte[] salt) {
    int s = 0;
    Calendar cal = Calendar.getInstance();

    for (int i=0; i<salt.length; i++) {
       s += salt[i];
    }

    s += cal.get(Calendar.DAY_OF_MONTH);
    s += cal.get(Calendar.MONTH) + 1;
    s += cal.get(Calendar.YEAR); 

    return Integer.toString(s);
  }

  public static String encrypt(String salt, String inStr) {
 
    try {
     // create value to hash
     String valueAndSalt = inStr + salt;
     String str = inStr + getSalt(valueAndSalt.getBytes("US-ASCII")) + salt;

     // compute SHA256 hash
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
     byte[] hash = digest.digest(str.getBytes("UTF-8"));

     // get base64
     String hash2 = DatatypeConverter.printHexBinary(hash);

     return hash2.toLowerCase();
 
    } catch (Exception e) {
       return null;
    }
  }
}
