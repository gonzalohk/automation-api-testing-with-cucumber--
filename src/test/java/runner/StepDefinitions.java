package runner;

import clientAPI.FactoryRequest;
import clientAPI.RequestInformation;
import clientAPI.ResponseInformation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.junit.Assert;
import util.AuthenticationUtils;
import util.JsonUtils;


import static configuration.Conf.*;

public class StepDefinitions {
    ResponseInformation response = new ResponseInformation();

    @Given("^I have access to https://todo\\.ly/$")
    public void iHaveAccessToHttpsTodoLy() {
    }

    @When("^I send (POST|PUT|DELETE|GET) request '(.*)' with json$")
    public void iSendRequestApiItemsJson(String method, String url, String jsonBody) throws JSONException {
        //Building request
        RequestInformation request = new RequestInformation();
        request.setUrl(HOST + this.replaceVariables(url));
        request.setBody(this.replaceVariables(jsonBody));

        //Checking Token AUTH
        //Workaround
        if (!url.equals("api/user.json")) {
            if (GS.tokenAuthentication.isEmpty()) {
                //Setting tokenAuthentication if it is empty
                GS.tokenAuthentication = AuthenticationUtils.getTokenAuthentication(
                        "GET",
                        "api/authentication/token.json",
                        GS.globalVariables.get("EMAIL_USER"),
                        GS.globalVariables.get("PASSWORD_USER")
                );
            }
            request.addHeaders(TOKEN_HEADER,GS.tokenAuthentication);
        }

        //Sending
        response = FactoryRequest.make(method.toLowerCase()).sendRequest(request);
    }

    @Then("^I expect the response code (\\d+)$")
    public void iExpectTheResponseCode(int expectedResponseCode) {
        Assert.assertEquals("Error!, wrong response code ", expectedResponseCode, response.getResponseCode());
    }

    @And("^I expect the response body is equal$")
    public void iExpectTheResponseBodyIsEqual(String expectedResponseBody) throws JSONException {
        Assert.assertTrue("Error!, wrong response data",
                JsonUtils.areEqualJSON(this.replaceVariables(expectedResponseBody),response.getResponseBody()));
    }

    @And("^I get the property value '(.*)' and save on (.*)")
    public void iGetThePropertyValueIdAndSaveOnID_ITEM(String property, String nameVariable ) throws JSONException {
        String value = JsonUtils.getValueFromJSON(response.getResponseBody(), property);
        GS.globalVariables.put(nameVariable, value);
        System.out.println(nameVariable + "("+GS.globalVariables.get(nameVariable)+") was saved");
    }

    @And("^I save '(.*)' on (.*)")
    public void iSaveValueOnID_ITEM(String property, String nameVariable ) throws JSONException {
        GS.globalVariables.put(nameVariable, property);
        System.out.println(nameVariable + "("+GS.globalVariables.get(nameVariable)+") was saved");
    }

    private String replaceVariables(String value){
        for (String key: GS.globalVariables.keySet()){
            value= value.replace(key,GS.globalVariables.get(key));
        }
        return value;
    }

}
