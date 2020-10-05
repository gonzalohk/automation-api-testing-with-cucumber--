package util;

import clientAPI.FactoryRequest;
import clientAPI.RequestInformation;
import clientAPI.ResponseInformation;
import org.json.JSONException;

import static configuration.Conf.AUTHENTICATION_HEADER;
import static configuration.Conf.HOST;

public class AuthenticationUtils {

    public static String getTokenAuthentication(String method, String url,String user, String password) throws JSONException {
        //Building request
        RequestInformation request = new RequestInformation();
        request.setUrl(HOST + url);

        //Adding AUTH basic
        request.addHeaders(AUTHENTICATION_HEADER,getBasicAuthenticationValue(user,password));

        //Sending
        ResponseInformation response = FactoryRequest.make(method.toLowerCase()).sendRequest(request);
        System.out.println(response.getResponseBody());
        String tokenAuthentication = JsonUtils.getValueFromJSON(response.getResponseBody(), "TokenString");
        return tokenAuthentication;
    }

    public static String getBasicAuthenticationValue(String user,String password){
        return "Basic " + Base64Utils.encodeBase64(user+":"+password);
    }
}
