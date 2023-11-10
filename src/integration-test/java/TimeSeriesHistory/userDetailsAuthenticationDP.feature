Feature: API Authentication

  Background:
    * def Auth = Java.type('TimeSeriesHistory.secretsconfig')
    * def clienturl = new Auth().getCognitoUrl();
    * url  clienturl
    * def clientDet = new Auth().getcognitoSecretMap();
    * def cognitoClient = clientDet.cognito_client_id
    * def cognitoSecret = clientDet.cognito_client_secret
  Scenario: Authentication test

    Given path '/oauth2/token'
    And form field grant_type = 'client_credentials'
    And form field client_id = cognitoClient
    And form field client_secret = cognitoSecret
    When method POST
    Then status 200

    * def accessToken = response.access_token
    Then print 'accessToken=',accessToken

