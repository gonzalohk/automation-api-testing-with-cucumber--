package clientAPI;

public class FactoryRequest {
    public static RequestClient make(String type){
        RequestClient client;
        switch (type){
            case "post":
                client= new POST();
                break;
            case "put":
                client= new PUT();
                break;
            case "delete":
                client= new DELETE();
                break;
            case "get":
            default:
                client= new GET();
                break;
        }
        return client;
    }
}
