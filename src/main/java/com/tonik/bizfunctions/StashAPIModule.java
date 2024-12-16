package com.tonik.bizfunctions;
import com.jcraft.jsch.JSchException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.sql.SQLException;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.Utilities.propertyFileReader;
import static com.tonik.utility.Utilities.waitTime;

public class StashAPIModule extends BaseClass{
    public static RequestSpecification requestSpec;
    public static Response response;
    public static String authorization_token;
    public static String tonik_user_token;
    public static String user_Id="";
    public static String  signedAccountNumber;
    public static String txn_request_id;
    public static String  nonce;
    public static String  time_stamp;
    public static String stash_id;
    public static String device_Id="";
    public static String app=propertyFileReader("MobileType","stash");
    public static String app_Version=propertyFileReader("AppVersion","stash");;
    public static String ip_Address=propertyFileReader("IpAddress","stash");;
    public static String geo_location=propertyFileReader("GeoLocation","stash");;
    public static String channel_Id=propertyFileReader("ChannelId","stash");;
    public static String userNumber=propertyFileReader("OwnerMobileNumber","stash");;
    /**
     * Hmac token API request API body
     * @param signedAccountNumber
     * @return
     */
    public String stashHmacBody(String signedAccountNumber) {
        String stash;
        return stash= "{\n" +
                "    \"payload\": {\n" +
                "    \"savingAccountNo\": \""+signedAccountNumber+"\",\n" +
                "    \"intialStashAmt\": \"20\",\n" +
                "    \"stashTargetAmt\": \"20,000.00\",\n" +
                "    \"stashTargetDate\": \"\",\n" +
                "    \"stashName\": \"Trip\",\n" +
                "    \"image\": \"56c7752e-0862-41ca-947c-89f7f017001\",\n" +
                "    \"imageId\": \"56c7752e-0862-41ca-947c-89f7f0175681\",\n" +
                "    \"stashType\": \"G\",\n" +
                "    \"isTnAndConditionAccepted\": \"Y\"\n" +
                "},\n" +
                "   \"uri\": \"/v0/stash\",\n" +
                "   \"verb\": \"POST\"\n" +
                "}";
    }
    /**
     * Invite stash API body
     * @param stash_id
     * @param mobileNo
     * @return
     */
    public String inviteStashBody(String stash_id,String mobileNo){
        String invite;
        return invite="{\n" +
                "    \"stashId\": \""+stash_id+"\",\n" +
                "    \"stashName\": \"Palawan Fund\",\n" +
                "    \"groupUserList\": [\n" +
                "        {\n" +
                "            \"mobileNo\": \""+mobileNo+"\",\n" +
                "            \"name\": \"Honey PH\",\n" +
                "            \"thumbnailPath\": \"\",\n" +
                "            \"firstchar\": \"H\",\n" +
                "            \"recordID\": \"94118\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
    /**
     * Create Group stash request API body
     * @param signedAccountNumber
     * @return
     */
    public String createStashBody(String signedAccountNumber){
        String create;
        return create="{\n" +
                "    \"savingAccountNo\": \""+signedAccountNumber+"\",\n" +
                "    \"intialStashAmt\": \"20\",\n" +
                "    \"stashTargetAmt\": \"20,000.00\",\n" +
                "    \"stashTargetDate\": \"\",\n" +
                "    \"stashName\": \"Trip\",\n" +
                "    \"image\": \"56c7752e-0862-41ca-947c-89f7f017001\",\n" +
                "    \"imageId\": \"56c7752e-0862-41ca-947c-89f7f0175681\",\n" +
                "    \"stashType\": \"G\",\n" +
                "    \"isTnAndConditionAccepted\": \"Y\"\n" +
                "}";
    }
    /**
     * Get The User ID,Device Id and signed account number from DB
     * @param mobileNumber
     * @throws JSchException
     * @throws SQLException
     */
    public void getUserIdAndDeviceId(String mobileNumber) throws JSchException, SQLException {
        user_Id=selectQuery("customer","SELECT user_id FROM customer.tdbk_customer_mtb where mobile_no = '"+mobileNumber+"';");
        System.out.println("User Id : "+user_Id);
        device_Id=selectQuery("customer","SELECT device_id FROM customer.tdbk_customer_mtb where mobile_no = '"+mobileNumber+"';");
        System.out.println("Device Id : "+device_Id);
        signedAccountNumber=selectQuery("customer","SELECT signup_acct_no FROM customer.tdbk_customer_mtb where mobile_no = '"+mobileNumber+"';");
        System.out.println("signed Account Number : "+signedAccountNumber);
    }
    /**
     * Authorization Token API
     */
    public void authorizationTokenApi(){
        String baseUrl = propertyFileReader("AuthorizationTokenBaseUrl","stash");
        String endpoint = propertyFileReader("AuthorizationTokenEndPoint","stash");
        String username =propertyFileReader("AuthUserName","stash");
        String password =propertyFileReader("AuthUserPassword","stash");
        requestSpec = RestAssured.given()
                .auth().preemptive().basic(username, password)
                .contentType("application/x-www-form-urlencoded");
        response = requestSpec.post(baseUrl + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        authorization_token = response.jsonPath().get("access_token").toString();
        System.out.println("authorization_token: "+authorization_token);
    }
    /**
     * stash Local Token API
     */
    public void stashLocalTokenAPI(){
        String baseUrl = propertyFileReader("LocalTokenBaseUrl","stash");
        String endpoint = propertyFileReader("LocalTokenEndPoint","stash");

        requestSpec = RestAssured.given().params("userId",user_Id,"channelid",channel_Id).when();
        response = requestSpec.get(baseUrl + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        tonik_user_token = response.jsonPath().get("token").toString();
        System.out.println("tonik_user_token : "+tonik_user_token);
    }
    /**
     * stash Hmac token API
     */
    public  void stashHmacTokenAPI(){
        String baseUrl = propertyFileReader("Baseurl","stash");
        String endpoint = propertyFileReader("HmacEndPoint","stash");

        requestSpec = RestAssured.given().log().all()
                .queryParam("stashType","G")
                .header("Authorization", "Bearer " + authorization_token)
                .header("Content-Type","application/json")
                .header("clientinfo","deviceos="+app+"&&devicename=tangy&&appversion="+app_Version+"&&deviceid="+device_Id+"&&ip="+ip_Address+"&&geoloc="+geo_location+"&&channelid="+channel_Id+"&&userId="+user_Id)
                .header("tonikusertoken",tonik_user_token)
                .body(stashHmacBody(signedAccountNumber));
        response = requestSpec.post(baseUrl + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        time_stamp = response.jsonPath().get("data.timestamp").toString();
        System.out.println("timeStamp: "+time_stamp);
        txn_request_id = response.jsonPath().get("data.txnRequestId").toString();
        System.out.println("Txn request ID: "+txn_request_id);
        nonce = response.jsonPath().get("data.nonce").toString();
        System.out.println("Nonce: "+nonce);
    }
    /**
     * Create stash API
     * @param tonik_user_token
     * @param txn_request_id
     * @param nonce
     * @param time_stamp
     */
    public  void createStashAPI(String tonik_user_token,String txn_request_id,String nonce,String time_stamp) {
        String baseUrl = propertyFileReader("Baseurl","stash");
        String endpoint = propertyFileReader("CreateStashEndpoint","stash");
        requestSpec = RestAssured.given().log().all()
                .header("Authorization", "Bearer " + authorization_token)
                .header("Content-Type","application/json")
                .header("clientinfo","deviceos="+app+"&&devicename=tangy&&appversion="+app_Version+"&&deviceid="+device_Id+"&&ip="+ip_Address+"&&geoloc="+geo_location+"&&channelid="+channel_Id+"&&userId="+user_Id)
                .header("tonikusertoken",tonik_user_token)
                .header("txnrequestid",txn_request_id)
                .header("nonce",nonce)
                .header("timestamp",time_stamp)
                .body(createStashBody(signedAccountNumber));
        System.out.println("Response Body: " + requestSpec);
        response = requestSpec.post(baseUrl + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        stash_id=response.jsonPath().get("data.stashId").toString();
        System.out.println("stash_id: "+stash_id);
    }

    /**
     * Invite Group stash API
     * @param tonik_user_token
     * @param txn_request_id
     * @param nonce
     * @param time_stamp
     * @param stash_id
     * @param mobileNo
     */
    public  void inviteStashAPI(String tonik_user_token,String txn_request_id,String nonce,String time_stamp,String stash_id,String mobileNo) {
        String baseUrl = propertyFileReader("Baseurl","stash");
        String endpoint = propertyFileReader("StashInviteEndPoint","stash");
        requestSpec = RestAssured.given().log().all()
                .header("Authorization", "Bearer " + authorization_token)
                .header("Content-Type","application/json")
                .header("clientinfo","deviceos="+app+"&&devicename=tangy&&appversion="+app_Version+"&&deviceid="+device_Id+"&&ip="+ip_Address+"&&geoloc="+geo_location+"&&channelid="+channel_Id+"&&userId="+user_Id)
                .header("tonikusertoken",tonik_user_token)
                .header("txnrequestid",txn_request_id)
                .header("nonce",nonce)
                .header("timestamp",time_stamp)
                .body(inviteStashBody(stash_id,mobileNo));
        response = requestSpec.post(baseUrl + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        String status = response.getBody().jsonPath().get("status.message").toString();
        System.out.println("Loan Status: "+status);
    }

    /**
     * Method to invite group stash request
     * @param mobileNumber
     * @throws Exception
     */
    public void groupStashRequest(String mobileNumber)throws Exception{
        getUserIdAndDeviceId(mobileNumber);
        waitTime(3000);
        authorizationTokenApi();
        waitTime(3000);
        stashLocalTokenAPI();
        waitTime(4000);
        stashHmacTokenAPI();
        waitTime(4000);
        createStashAPI(tonik_user_token,txn_request_id,nonce,time_stamp);
        waitTime(4000);
        inviteStashAPI(tonik_user_token,txn_request_id,nonce,time_stamp,stash_id,userNumber);
    }
}
