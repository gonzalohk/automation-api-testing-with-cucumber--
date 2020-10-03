package clientAPI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class POST extends RequestClient{

    @Override
    public ResponseInformation sendRequest(RequestInformation request) {
        System.out.println("\nPOST -> " + request.getUrl());
        System.out.println("Body -> " + request.getBody());
        Response response = this.client.target(request.getUrl())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .headers(request.getHeaders())
                .post(Entity.json(request.getBody()));

        ResponseInformation responseInformation = new ResponseInformation(response.readEntity(String.class), response.getStatus());
        response.close();

        return responseInformation;
    }
}
