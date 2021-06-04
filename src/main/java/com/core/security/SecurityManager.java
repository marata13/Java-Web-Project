package com.core.security;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityManager {

    /**
     * @source https://www.geeksforgeeks.org/md5-hash-in-java/
     * @param message To περιεχομενο που πρεπει να κρυπτογραφησει.
     * @return Το κρυπτογραφημενο αποτελεσμα.
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException.
     */
    public static String getHash(String message) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte[] messageDigest = algorithm.digest(message.getBytes());
        BigInteger bigInteger = new BigInteger(1, messageDigest);

        StringBuilder hash = new StringBuilder(bigInteger.toString(16));

        while (hash.length() < 32) {
            hash.insert(0, "0");
        }
        return hash.toString();
    }

}
