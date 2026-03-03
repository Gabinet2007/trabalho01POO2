package util;

import java.io.*;
import java.security.*;

public class HashUtil {

    public static String gerarHash(String caminho) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        try (InputStream is = new FileInputStream(caminho)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1)
                md.update(buffer, 0, bytesRead);
        }

        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest)
            sb.append(String.format("%02x", b));

        return sb.toString();
    }
}