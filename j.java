import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;

class J {
  private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

  public static void main(String[] args) {
    String req = "VYSKEAZZ0011110002h2wb08";
    String salt = "6b1d4458708e27252e5733a65abfe89fa2d312304251c5fa8c0065a552ba9865";

    System.out.println("body " + req + " encrypted: " + encrypt(salt, req));
  }

  private static String getSalt(byte[] salt) {
    int s = 0;
    Calendar cal = Calendar.getInstance();

    for (int i=0; i<salt.length; i++) {
       s += salt[i];
    }

    s += (cal.get(Calendar.DAY_OF_MONTH) + cal.get(Calendar.MONTH) + cal.get(Calendar.YEAR)); 

    return Integer.toString(s);
  }

  public static String encrypt(String salt, String inStr) {
 
    try {
     // create value to hash
     String str = inStr + getSalt(salt.getBytes("US-ASCII")) + salt;

     // compute SHA256 hash
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
     byte[] hash = digest.digest(str.getBytes("UTF-8"));

     // get base64
     String hash2 = DatatypeConverter.printHexBinary(hash);

     return hash2;
 
    } catch (Exception e) {
       return null;
    }
  }
}
