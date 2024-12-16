package com.tonik.bizfunctions;
import com.jcraft.jsch.JSchException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.sql.SQLException;
import static com.tonik.utility.DB_Utilites.selectQuery;
import static com.tonik.utility.Utilities.*;
public class ShopInstallmentLoanAPIModule extends BaseClass {
    public ShopInstallmentLoanAPIModule() {
        super();
    }
    public static RequestSpecification requestSpec;
    public static Response response;
    public static String promoter_access_Token;
    public static String authorization_token;
    public static String tonik_user_token;
    public static String qr_code_id;
    public static String digital_loan_id;
    public static String user_Id="";
    public static String device_Id="";
    public static String app=propertyFileReader("MobileType","ShopInstallmentLoan");
    public static String app_Version=propertyFileReader("AppVersion","ShopInstallmentLoan");;
    public static String ip_Address=propertyFileReader("IpAddress","ShopInstallmentLoan");;
    public static String geo_location=propertyFileReader("GeoLocation","ShopInstallmentLoan");;
    public static String channel_Id=propertyFileReader("ChannelId","ShopInstallmentLoan");;
    /**
     * BNPL API Request Body
     * @param id
     * @param name
     * @param purpleKey
     * @param amount
     * @param downPaymentAmount
     * @param tenure
     * @param monthlyAmount
     * @param loanableAmount
     * @param vasFlag
     * @return
     */
    public String createBNPLBody(String id,String name,String purpleKey,String amount,String downPaymentAmount,String tenure,String monthlyAmount,String loanableAmount,String vasFlag){
        String body;
        return body ="{\n" +
                "  \"promoter\": {\n" +
                "    \"id\": \""+id+"\",\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"purpleKey\": \""+purpleKey+"\"\n" +
                "  },\n" +
                "  \"categories\": [\n" +
                "    {\n" +
                "      \"categoryId\": \"1747\",\n" +
                "      \"categoryName\": \"Mobile Phones\",\n" +
                "      \"brand\": \"samsung\",\n" +
                "      \"skuNo\": \"test\",\n" +
                "      \"amount\": \""+amount+"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"downPaymentAmount\": "+downPaymentAmount+",\n" +
                "  \"tenureId\": \"20\",\n" +
                "  \"tenure\": \""+tenure+"\",\n" +
                "  \"monthlyAmount\": "+monthlyAmount+",\n" +
                "  \"monthlyInterestRate\": \"9.11\",\n" +
                "  \"loanableAmount\": "+loanableAmount+",\n" +
                "  \"total\": 10000,\n" +
                "  \"defaultDPamt\": 0,\n" +
                "  \"defaultDP\": 30,\n" +
                "  \"increasedDPamt\": 0,\n" +
                "  \"assesmentCode\": 0,\n" +
                "  \"idPresentConcern\": \"I confirm the ID presented is genuine.\",\n" +
                "  \"physicalPresentConcern\": \"I confirm the client is physically present.\",\n" +
                "  \"vasFlag\": "+vasFlag+",\n" +
                "  \"vas\": {\n" +
                "    \"typeId\": 2,\n" +
                "    \"vasFee\": 143.49,\n" +
                "    \"feeTenureId\": 20\n" +
                "  },\n" +
                "  \"segmentId\": 3,\n" +
                "  \"addOnRate\": 2.5\n" +
                "}";
    }
    /**
     * Initiat QR API Request Body
     * @param qr_code_id
     * @return
     */
    public String initiatQrBody(String qr_code_id){
        String qrCode;
        return qrCode = "{\n" +
                "    \"qrCode\": \"{\\\"id\\\":\\\""+qr_code_id+"\\\"}\",\n" +
                "    \"loanType\": \"BNPL\"\n" +
                "}";
    }
    /**
     * Apply Loan API Request Body
     * @param digitalLoanID
     * @param vasFlag
     * @return
     */
    public String applyLoanBody(String digitalLoanID,String vasFlag){
        String applyLoan;
        return applyLoan="{\n" +
                "    \"loanDigitalId\": \""+digitalLoanID+"\",\n" +
                "    \"loanType\": \"BNPL\",\n" +
                "    \"loanAppliedDetails\": {\n" +
                "        \"isEligiblityApplied\": \"ACCEPT\"\n" +
                "    },\n" +
                "    \"screenTrace\": {\n" +
                "        \"currentScreenId\": \"SCRF903\",\n" +
                "        \"previousScreenId\": \"SCRF902\",\n" +
                "        \"nextScreenId\": \"SCRF904\"\n" +
                "    },\n" +
                "    \"segmentTrace\": {\n" +
                "        \"segmentCompleted\": \"loanoverview\"\n" +
                "    },\n" +
                "    \"channelId\": \"MOBILEAPP\",\n" +
                "    \"vasFlag\": "+vasFlag+",\n" +
                "    \"vas\": {\n" +
                "        \"typeId\": 2,\n" +
                "        \"vasFee\": 143.49,\n" +
                "        \"feeTenureId\": 20\n" +
                "    }\n" +
                "}";
    }
    //======================================================================================
    /**
     * Get The User ID and Device Id from DB
     * @param mobileNumber
     * @throws JSchException
     * @throws SQLException
     */
    public void getUserIdAndDeviceId(String mobileNumber) throws JSchException, SQLException {
        user_Id=selectQuery("customer","SELECT user_id FROM customer.tdbk_customer_mtb where mobile_no = '"+mobileNumber+"';");
        logger.info("User Id : "+user_Id);
        device_Id=selectQuery("customer","SELECT device_id FROM customer.tdbk_customer_mtb where mobile_no = '"+mobileNumber+"';");
        logger.info("Device Id : "+device_Id);
    }
    /**
     * Promoter Acccess token API
     */
    public void promoterAccessTokenAPi() {
        String baseUrl = propertyFileReader("PromoterAccessTokenBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("PromoterAccessTokenEndPoint","ShopInstallmentLoan");
        String username =propertyFileReader("PromoterAccessUserName","ShopInstallmentLoan");
        String password =propertyFileReader("PromoterAccessPassword","ShopInstallmentLoan");
        requestSpec = RestAssured.given()
                .auth().preemptive().basic(username, password)
                .contentType("application/x-www-form-urlencoded");
        response = requestSpec.post(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        promoter_access_Token = response.jsonPath().get("access_token").toString();
        logger.info("PromoterAccess Token : "+promoter_access_Token);
    }
    /**
     * Authorization Token API
     */
    public void authorizationTokenApi(){
        String baseUrl = propertyFileReader("AuthorizationTokenBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("AuthorizationTokenEndPoint","ShopInstallmentLoan");
        String username =propertyFileReader("AuthUserName","ShopInstallmentLoan");
        String password =propertyFileReader("AuthUserPassword","ShopInstallmentLoan");
        requestSpec = RestAssured.given()
                .auth().preemptive().basic(username, password)
                .contentType("application/x-www-form-urlencoded");
        response = requestSpec.post(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        authorization_token = response.jsonPath().get("access_token").toString();
        logger.info("authorization_token: "+authorization_token);
    }

    /**
     * Loan Local Token API
     */
    public void loanLocalTokenAPI(){
        String baseUrl = propertyFileReader("LocalTokenBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("LocalTokenEndPoint","ShopInstallmentLoan");

        requestSpec = RestAssured.given().params("userId",user_Id,"channelid",channel_Id).when();
        response = requestSpec.get(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        tonik_user_token = response.jsonPath().get("token").toString();
        logger.info("tonik_user_token : "+tonik_user_token);
    }

    /**
     * Create BNPL API
     * @param vasFlag
     * @param monthlyInstallment
     */
    public  void createBNPLAPI(String vasFlag,String monthlyInstallment) {
        String baseUrl = propertyFileReader("CreateBNPLBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("CreateBNPLEndPoint","ShopInstallmentLoan");
        requestSpec = RestAssured.given().log().all()
                .contentType("application/json")
                .body(createBNPLBody(propertyFileReader("ID","ShopInstallmentLoan"),propertyFileReader("Name","ShopInstallmentLoan"),
                        propertyFileReader("PurpleKey","ShopInstallmentLoan"),propertyFileReader("Amount","ShopInstallmentLoan"),
                        propertyFileReader("DownPayment","ShopInstallmentLoan"),propertyFileReader("Tenure","ShopInstallmentLoan"),
                        monthlyInstallment,propertyFileReader("LoanableAmount","ShopInstallmentLoan"),vasFlag));
        response = requestSpec.post(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        qr_code_id=response.jsonPath().get("data.id").toString();
        logger.info("qr_code_id: "+qr_code_id);
    }
    /**
     * Initiate VerifyQR API
     */
    public  void initiateVerifyQrAPI(){
        String baseUrl = propertyFileReader("VerifyQrBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("VerifyQrEndPoint","ShopInstallmentLoan");

        requestSpec = RestAssured.given().log().all()
                .header("Authorization", "Bearer " + authorization_token)
                .header("Content-Type","application/json")
                .header("clientinfo","deviceos="+app+"&&devicename=tangy&&appversion="+app_Version+"&&deviceid="+device_Id+"&&ip="+ip_Address+"&&geoloc="+geo_location+"&&channelid="+channel_Id+"&&userId="+user_Id)
                .header("tonikusertoken",tonik_user_token)
                .body(initiatQrBody(qr_code_id));
        response = requestSpec.post(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        digital_loan_id = response.jsonPath().get("data.loanDigitalId").toString();
        logger.info("Digital LoanID: "+digital_loan_id);
    }

    /**
     * Get Loan API
     */
    public  void getLoanAPI(){
        String baseUrl = propertyFileReader("GetLoanBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("GetLoanEndPoint","ShopInstallmentLoan");

        requestSpec = RestAssured.given().log().all()
                .param("loanDigitalId",digital_loan_id)
                .header("Authorization", "Bearer " + authorization_token)
                .header("clientinfo","deviceos="+app+"&&devicename=tangy&&appversion="+app_Version+"&&deviceid="+device_Id+"&&ip="+ip_Address+"&&geoloc="+geo_location+"&&channelid="+channel_Id+"&&userId="+user_Id)
                .header("tonikusertoken",tonik_user_token);
        response = requestSpec.get(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        String status = response.getBody().jsonPath().get("status.message").toString();
        logger.info("Loan Status: "+status);

    }

    /**
     * Apply Loan API
     * @param vasFlag
     */
    public  void applyLoanAPI(String vasFlag){
        String baseUrl = propertyFileReader("ApplyLoanBaseUrl","ShopInstallmentLoan");
        String endpoint = propertyFileReader("ApplyLoanEndPoint","ShopInstallmentLoan");
        requestSpec = RestAssured.given().log().all()
                .header("Authorization", "Bearer " + authorization_token)
                .header("Content-Type","application/json")
                .header("clientinfo","deviceos="+app+"&&devicename=tangy&&appversion="+app_Version+"&&deviceid="+device_Id+"&&ip="+ip_Address+"&&geoloc="+geo_location+"&&channelid="+channel_Id+"&&userId="+user_Id)
                .header("tonikusertoken",tonik_user_token)
                .body(applyLoanBody(digital_loan_id,vasFlag));
        response = requestSpec.patch(baseUrl + endpoint);
        logger.info("Response Status Code: " + response.getStatusCode());
        logger.info("Response Body : "+response.getBody().asString());
        String status = response.getBody().jsonPath().get("status.message").toString();
        logger.info("Loan Status: "+status);
    }
    /**
     * This Methos Is To Create SIL Loan
     * @param mobileNumber
     * @param vasFlag
     * @param monthlyInstallment
     * @throws Exception
     */
    public  void createSILLoan(String mobileNumber,String vasFlag,String monthlyInstallment) throws Exception {
        getUserIdAndDeviceId(mobileNumber);
        promoterAccessTokenAPi();
        waitTime(2000);
        createBNPLAPI(vasFlag,monthlyInstallment);
        waitTime(3000);
        authorizationTokenApi();
        waitTime(3000);
        loanLocalTokenAPI();
        waitTime(4000);
        initiateVerifyQrAPI();
        waitTime(4000);
        getLoanAPI();
        waitTime(4000);
        applyLoanAPI(vasFlag);
        waitTime(3000);
    }
}
