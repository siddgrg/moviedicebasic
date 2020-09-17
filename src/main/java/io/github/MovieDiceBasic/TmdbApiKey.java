package io.github.MovieDiceBasic;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

final class TmdbApiKey {

    public static final String API_KEY = /* redacted */;
    public static final String API_READ_ACCESS_TOKEN = /* redacted */;;
    private static UserAccountInfo userAccountInfo;

    private static final String appMainDirectory = System.getProperty("user.home") + "/MovieDice/keys/";
    private static final String userInfoFilePath = appMainDirectory + "encrypted_user_info.ser";
    private static final String secretKeyFilePath = appMainDirectory + "secret_key_user_info.ser";

    public static void serialiseUserAccountInfo(String accountId, String userAccessToken, String sessionId, String username) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException {
        userAccountInfo = new UserAccountInfo(accountId, userAccessToken, sessionId, username);

        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey aesKey = keygen.generateKey();

        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, aesKey);

        SealedObject so = new SealedObject(userAccountInfo, c);

        new File(appMainDirectory).mkdirs();

        File userInfoFile = new File(userInfoFilePath);
        userInfoFile.createNewFile();
        FileOutputStream fos1 = new FileOutputStream(userInfoFilePath);
        BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
        ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
        oos1.writeObject(so);
        oos1.close();

        File secretKeyFile = new File(secretKeyFilePath);
        secretKeyFile.createNewFile();
        FileOutputStream fos2 = new FileOutputStream(secretKeyFilePath);
        BufferedOutputStream bos2 = new BufferedOutputStream(fos2);
        ObjectOutputStream oos2 = new ObjectOutputStream(bos2);
        oos2.writeObject(aesKey);
        oos2.close();
    }

    public static void deserialiseUserAccountInfo() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ClassNotFoundException {
        FileInputStream fis1 = new FileInputStream(secretKeyFilePath);
        BufferedInputStream bis1 = new BufferedInputStream(fis1);
        ObjectInputStream ois1 = new ObjectInputStream(bis1);

        Key key = (Key) ois1.readObject();

        FileInputStream fis2 = new FileInputStream(userInfoFilePath);
        BufferedInputStream bis2 = new BufferedInputStream(fis2);
        ObjectInputStream ois2 = new ObjectInputStream(bis2);

        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        SealedObject so;
        try {
            so = (SealedObject) ois2.readObject();
            userAccountInfo = (UserAccountInfo) so.getObject(key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        bis1.close();
        bis2.close();
    }

    protected static String getAccountId() {
        return userAccountInfo.getAccountId();
    }

    protected static String getUserAccessToken() {
        return userAccountInfo.getUserAccessToken();
    }

    protected static String getSessionId() { return userAccountInfo.getSessionId(); }

    protected static String getUsername() { return userAccountInfo.getUsername(); }

    public static String getUserInfoFilePath() {
        return userInfoFilePath;
    }

    static class UserAccountInfo implements Serializable {
        private final String accountId;
        private final String userAccessToken;
        private final String sessionId;
        private final String username;

        protected UserAccountInfo(String accountId, String userAccessToken, String sessionId, String username) {
            this.accountId = accountId;
            this.userAccessToken = userAccessToken;
            this.sessionId = sessionId;
            this.username = username;
        }

        protected String getAccountId() {
            return accountId;
        }

        protected String getUserAccessToken() {
            return userAccessToken;
        }

        protected String getSessionId() {
            return sessionId;
        }

        protected String getUsername() {
            return username;
        }
    }
}
