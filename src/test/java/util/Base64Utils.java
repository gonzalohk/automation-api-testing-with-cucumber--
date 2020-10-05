package util;

import java.util.Base64;

public class Base64Utils {

    public static String encodeBase64(String string){
        return new String(Base64.getEncoder().encode(string.getBytes()));
    }
}
