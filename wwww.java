import javax.crypto.*;
import java.util.Base64;

public class Des {
  public static SecretKey key;
  public static String encrypt(String message) {
    try {
      KeyGenerator kg = KeyGenerator.getInstance("DES");
      key = kg.generateKey();
      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] data = message.getBytes();
      byte[] encryptedData = cipher.doFinal(data);
      return Base64.getEncoder().encodeToString(encryptedData);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
  public static String decrypt(String message) {
    try {
      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] encryptedData = Base64.getDecoder().decode(message);
      byte[] decryptedData = cipher.doFinal(encryptedData);
      return new String(decryptedData);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
  public static void main(String[] args) {
    String message = "Hello world!";
    String encrypted = encrypt(message);
    String decrypted = decrypt(encrypted);
    System.out.println("Orginal: " + message);
    System.out.println("Encrypted: " + encrypted);
    System.out.println("Decrypted: " + decrypted);
  }
}