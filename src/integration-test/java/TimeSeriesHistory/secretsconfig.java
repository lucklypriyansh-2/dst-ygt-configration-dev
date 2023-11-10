package TimeSeriesHistory;


import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;


public class secretsconfig {
   private SdkHttpClient client =  ApacheHttpClient.builder().build();
    private SecretsManagerClient secretsManagerClient =
            SecretsManagerClient.builder().region(Region.EU_WEST_1).httpClient(client).build();
   private SsmClient ssmClient = SsmClient.builder().region(Region.EU_WEST_1).httpClient(client).build();

    private ObjectMapper objectMapper = new ObjectMapper();



    public Map<String,String> getcognitoSecretMap() throws JsonProcessingException {
        GetSecretValueResponse response = secretsManagerClient.getSecretValue(GetSecretValueRequest.builder().secretId("dpp-dp-timeseries-history-api-cognito-secret").build());
        Map<String,String> cognitoSecretMap = objectMapper.readValue(response.secretString(), new TypeReference<Map<String, String>>() {
        });

       return cognitoSecretMap;
    }
    

public String getCognitoUrl() {
    GetParameterResponse response = ssmClient.getParameter(GetParameterRequest.builder().name("/dap/ingestion/api/cognito/url").build());
    String cognitoUrl = response.parameter().value();
    return cognitoUrl;
 
}}