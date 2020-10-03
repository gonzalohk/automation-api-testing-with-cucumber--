package clientAPI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PUT extends RequestClient{

    @Override
    public ResponseInformation sendRequest(RequestInformation request) {
        System.out.println("\nPUT -> " + request.getUrl());
        System.out.println("Body -> " + request.getBody());

        Response response = this.client.target(request.getUrl())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .headers(request.getHeaders())
                .put(Entity.json(request.getBody()));

        ResponseInformation responseInformation = new ResponseInformation(response.readEntity(String.class), response.getStatus());
        response.close();

        return responseInformation;
    }
}
