package runner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//GLOBAL Summary
//This class will help us to store global variables

public class GS {
    public static Map<String,String> globalVariables = new HashMap<>();
    public static String tokenAuthentication = "";
    public static String basicAuthentication = "";


    public static List<String> nonRequiresAuthenticationEndpoint = Arrays.asList("api/user.json");
}
