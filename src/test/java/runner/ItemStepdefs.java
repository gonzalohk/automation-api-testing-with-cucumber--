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
import util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static configuration.Conf.*;

public class ItemStepdefs {
    ResponseInformation response = new ResponseInformation();
    Map<String,String> globalVariables = new HashMap<>();

    @Given("^I have access to https://todo\\.ly/$")
    public void iHaveAccessToHttpsTodoLy() {
    }

    @When("^I send (POST|PUT|DELETE|GET) request '(.*)' with json$")
    public void iSendPOSTRequestApiItemsJson(String method, String url, String jsonBody) {
        //Building request
        RequestInformation request = new RequestInformation();
        request.setUrl(HOST + this.replaceVariables(url));
        request.setBody(this.replaceVariables(jsonBody));

        //Adding AUTH basic
        request.addHeaders(BASIC_AUTHENTICATION_HEADER,BASIC_AUTHENTICATION);

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
        globalVariables.put(nameVariable, value);
        System.out.println(nameVariable + "("+globalVariables.get(nameVariable)+") was saved");
    }

    private String replaceVariables(String value){
        for (String key: this.globalVariables.keySet()){
            value= value.replace(key,this.globalVariables.get(key));
        }
        return value;
    }
}
