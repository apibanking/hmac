import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import java.security.GeneralSecurityException;

class H {
  private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

  public static void main(String[] args) {
    String req = "{ \"amount\": 10000, \"txnRefId\": \"P16111764973885\", \"accountNumber\": \"SHOPXDT051624\" }";
    System.out.println("body " + req + " hamc " + calculateHMAC("2a5f1f65145c4d5daad65c902c19b021", req));
  }

	private static String calculateMD5(String contentToEncode) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(contentToEncode.getBytes());
		String result = new String(DatatypeConverter.printHexBinary(digest.digest()));
		return result;
	}

        private static String calculateHMAC(String secret, String data) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(),	HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			String result = new String(DatatypeConverter.printHexBinary(rawHmac));
			return result;
		} catch (GeneralSecurityException e) {
			throw new IllegalArgumentException();
		}
	}
}
