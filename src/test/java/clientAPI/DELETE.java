package clientAPI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DELETE extends RequestClient{


    @Override
    public ResponseInformation sendRequest(RequestInformation request) {
        System.out.println("\nDELETE -> " + request.getUrl());

        Response response = this.client.target(request.getUrl())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .headers(request.getHeaders())
                .delete();

        ResponseInformation responseInformation = new ResponseInformation(response.readEntity(String.class), response.getStatus());
        System.out.println("\nDELETE Response -> " + request.getBody());
        response.close();

        return responseInformation;
    }
}
