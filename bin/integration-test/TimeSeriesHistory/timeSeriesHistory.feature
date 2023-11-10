Feature: get timeSeries History

Background:
  * def myFeature = call read('userDetailsAuthenticationDP.feature')
  * def authToken = myFeature.accessToken
  * url  baseUrl
  * header Authorization = authToken
  * def Output1 = read('HistoricalDataTest.json')
  * def Output2 = read('DataendpointTest.json')
  * def Output3 = read('HistoricalDataDev.json')
  * def Output4 = read('DataendpointDev.json')
@test
Scenario: get timeseries history by passing invalid oauth
  * def query = {tag_name:'KA1061/current'}
  And params query
  And header Authorization = '5erftghyujioopkll'
  When method get
  Then status 401

@test
Scenario: get list of time series historical data with passing tag_name parameter
  * def query = {tag_name:'SADT5027_24h/value'}
  
  And params query
  When method get
  Then status 200
  And match response == Output1
  
@test
Scenario: get list of time series historical data by passing site id as SVI
  * def query = {site_ids:'SVI',tag_name:'KA1061/current'}
  And params query
  When method get
  Then status 200

@test
Scenario: get list of time series historical data by passing site id as GLO
  * def query = {site_ids:'GLO',tag_name:'KSFIC302/setPoint'}
  And params query
  When method get
  Then status 200
@test
Scenario: get list of time series historical data by passing different site id and tag name
  * def query = {site_ids:'PRO',tag_name:'SADT5027_24h/value'}
  And params query
  When method get
  Then status 400

@test
Scenario: get list of time series historical data by passing invalid site id
  * def query = {site_ids:'PRO1',tag_name:'SADT5027_24h/value'}
  And params query
  When method get
  Then status 400
@test
Scenario: get list of time series historical data by passing invalid tagName
  * def query = {tag_name:'SADT5027_24h/value78'}
  And params query
  When method get
  Then status 400

@test
Scenario: get list of time series historical data by passing tagName
  * def query = {tag_name:'LA0002-R1.R1/12'}
  And params query
  When method get
  Then status 200

@test
Scenario: get list of time series historical data by passing limit
  * def query = {tag_name:'SADT5027_24h/value',limit:'10'}
  And params query
  When method get
  Then status 200
  
@test
Scenario: get list of time series historical data by passing site_id,tagName ,limit
  * def query = {site_ids:'GLO',tag_name:'SADT5027_24h/value',limit:'12'}
  And params query
  When method get
  Then status 200
@test
Scenario: get list of time series historical data by passing all the query param
  * def query = {site_ids:'GLO',tag_name:'SADT5027_24h/value',limit:'40',pagination_token:'null'}
   And params query
  When method get
  Then status 200

  
@test
Scenario: get list of time series historical data by passing limit value more than allowed
  * def query = {tag_name:'SADT5027_24h/value',limit:'105'}
  And params query
  When method get
  Then status 200
@test
Scenario: get id from timeseries api and get data endpoint details for GLO site

  * def query = {tag_name:'SADT5027_24h/value'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response 
 

@test
Scenario: get id from timeseries api and get data endpoint details for SVI site

  * def query = {tag_name:'LA4010-PC/input'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response
  And match response == Output2

@test
Scenario: get id Data end point details with latest value and quality as GOOD

  * def query = {tag_name:'LA4010-PC/input'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true',quality:'GOOD'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response
  * def Quality = response.data[0].quality
  Then match  Quality contains 'GOOD'
@test
Scenario: get id Data end point details with latest value and quality as BAD

  * def query = {tag_name:'LA4010-PC/input'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true',quality:'BAD'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response
  * def Quality = response.data[0].quality
  Then match  Quality contains 'BAD'


@test
Scenario: get id Data end point details with latest value and quality as UNCERTAIN

  * def query = {tag_name:'LA4010-PC/input'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true',quality:'UNCERTAIN'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response
  * def Quality = response.data[0].quality
  Then match  Quality contains 'UNCERTAIN'

@test
Scenario: get id Data end point details with latest value and with date details

    * def dataId = 'SVI_LA4010-PC~input'
  * def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-03-29T09:12:45.001Z',limit:'23'}
  Given path dataId,'/data'
  And params query
    When method get
  Then status 200
  * print response

@test
Scenario: get id Data end point details with latest value and with future  date as end date

  * def dataId = 'SVI_LA4010-PC~input'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-08-29T09:12:45.001Z'}
Given path dataId,'/data'
And params query
When method get
Then status 200
@test
Scenario: get id Data end point details with latest value , date details,sort and  pagination

* def dataId = 'SVI_LA4010-PC~input'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-08-29T09:12:45.001Z',sort:'time'}
Given path dataId,'/data'
And params query
When method get
Then status 200

* def token = response.metadata.next_token
* def dataId = 'SVI_LA4010-PC~input'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-08-29T09:12:45.001Z',sort:'time',pagination_token:'#(token)'}
Given path dataId,'/data'
And header Authorization = authToken
And params query
When method get
Then status 200

@test
Scenario: get id Data end point details with latest value ,sort in ascending order and with date details

* def dataId = 'SVI_LA4010-PC~input'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-03-29T09:12:45.001Z',limit:'21', sort:'time'}
Given path dataId,'/data'
And params query
When method get
Then status 200
* print response

@test
Scenario: get id Data end point details with latest value ,sort in ascending order , BAD Quality and with date details

* def dataId = 'SVI_LA4010-PC~input'
* def query = {latest_value:'false',start_time:'2021-06-29T09:12:45.001Z',end_time:'2022-02-29T09:12:45.001Z',limit:'60', sort:'time',quality:'BAD'}
Given path dataId,'/data'
And params query
When method get
Then status 200
* print response



@test
Scenario: get id Data end point details with latest value ,sort in descending order , UNCERTAIN Quality and without date details

* def dataId = 'SVI_LA4010-PC~input'
* def query = {latest_value:'false',limit:'65', sort:'-time',quality:'UNCERTAIN'}
Given path dataId,'/data'
And params query
When method get
Then status 200
* print response

@test
Scenario: check if proper error is thrown when invalid data id is passed

* def dataId = 'SVI23_LA4010-PC~input12'
* def query = {latest_value:'false',limit:'65', sort:'-time',quality:'UNCERTAIN'}
Given path dataId,'/data'
And params query
When method get
Then status 400

@test
Scenario: check if proper error is thrown when more than allowed limit value is entered

* def dataId = 'SVI23_LA4010-PC~input12'
* def query = {latest_value:'false',limit:'300', sort:'-time',quality:'UNCERTAIN'}
Given path dataId,'/data'
And params query
When method get
Then status 400

@test
Scenario: check if proper error is thrown when invalid pagination token is passed

* def dataId = 'SVI23_LA4010-PC~input12'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-03-29T09:12:45.001Z',sort:'time',pagination_token:'ghjjkli'}
Given path dataId,'/data'
And params query
When method get
Then status 400

@dev
Scenario: get timeseries history by passing invalid oauth
  * def query = {tag_name:'LM060124/value'}
  And params query
  And header Authorization = '5erftghyujioopkll'
  When method get
  Then status 401

@dev1
Scenario: get list of time series historical data with passing tag_name parameter
  * def query = {tag_name:'LM060124/value'}
  And params query
  When method get
  Then status 200
  And match response == Output3
 
@dev
Scenario: get list of time series historical data by passing site id as SVI
  * def query = {site_ids:'slu',tag_name:'LM060124/value'}
  And params query
  When method get
  Then status 200
@dev
Scenario: get list of time series historical data by passing different site id and tag name
  * def query = {site_ids:'PRO',tag_name:'LM060124/value'}
  And params query
  When method get
  Then status 400
@dev
Scenario: get list of time series historical data by passing invalid site id
  * def query = {site_ids:'PRO1',tag_name:'LM060124/value'}
  And params query
  When method get
  Then status 400
@dev
Scenario: get list of time series historical data by passing invalid tagName
  * def query = {tag_name:'LM060124/value78'}
  And params query
  When method get
  Then status 400
@dev
Scenario: get list of time series historical data by passing tagName
  * def query = {tag_name:'80HC1949/input'}
  And params query
  When method get
  Then status 200

@dev
Scenario: get list of time series historical data by passing limit
  * def query = {tag_name:'80HC1949/input',limit:'10'}
  And params query
  When method get
  Then status 200
  
 @dev
Scenario: get list of time series historical data by passing site_id,tagName ,limit
  * def query = {site_ids:'slu',tag_name:'LM060124/value',limit:'12'}
  And params query
  When method get
  Then status 200
@dev
Scenario: get list of time series historical data by passing all the query param
  * def query = {site_ids:'slu',tag_name:'LM060124/value',limit:'40',pagination_token:'null'}
  And params query
  When method get
  Then status 200

@dev
Scenario: get id from timeseries api and get data endpoint details for slu site

  * def query = {tag_name:'LM060124/value'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response 
  And match response == Output4
@dev
Scenario: get id from timeseries api and get data endpoint details for slu site

  * def query = {tag_name:'LM060124/value'}
  And params query
  When method get
  Then status 200

  * def dataId = response.data[0].id
  * def query = {latest_value:'true'}
  Given path dataId,'/data'
  And params query
  And header Authorization = authToken
  When method get
  Then status 200
  * print response


@dev
Scenario: get id Data end point details with latest value and with date details

  * def dataId = 'slu_LM060122~value'
  * def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-03-29T09:12:45.001Z',limit:'23'}
  Given path dataId,'/data'
  And params query
  When method get
  Then status 200
  * print response

@dev
Scenario: get id Data end point details with latest value and with future  date as end date

* def dataId = 'slu_LM060122~value'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-08-29T09:12:45.001Z'}
Given path dataId,'/data'
And params query
When method get
Then status 200
@dev
Scenario: get id Data end point details with latest value , date details,sort and  pagination

* def dataId = 'slu_LM060122~value'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-08-29T09:12:45.001Z',sort:'time'}
Given path dataId,'/data'
And params query
When method get
Then status 200

* def token = response.metadata.next_token
* def dataId = 'slu_LM060122~value'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-08-29T09:12:45.001Z',sort:'time',pagination_token:'#(token)'}
Given path dataId,'/data'
And header Authorization = authToken
And params query
When method get
Then status 200

@dev
Scenario: get id Data end point details with latest value ,sort in ascending order and with date details

* def dataId = 'slu_LM060122~value'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-03-29T09:12:45.001Z',limit:'21', sort:'time'}
Given path dataId,'/data'
And params query
When method get
Then status 200
* print response


@dev
Scenario: get id Data end point details with latest value ,sort in descending order 

* def dataId = 'slu_LM060122~value'
* def query = {latest_value:'false',limit:'65', sort:'-time',quality:'UNCERTAIN'}
Given path dataId,'/data'
And params query
When method get
Then status 200
* print response

@dev
Scenario: check if proper error is thrown when invalid data id is passed

* def dataId = 'SVI23_LA4010-PC~input12'
* def query = {latest_value:'false',limit:'65', sort:'-time',quality:'UNCERTAIN'}
Given path dataId,'/data'
And params query
When method get
Then status 400

@dev
Scenario: check if proper error is thrown when more than allowed limit value is entered

* def dataId = 'SVI23_LA4010-PC~input12'
* def query = {latest_value:'false',limit:'300', sort:'-time',quality:'UNCERTAIN'}
Given path dataId,'/data'
And params query
When method get
Then status 400

@dev
Scenario: check if proper error is thrown when invalid pagination token is passed

* def dataId = 'SVI23_LA4010-PC~input12'
* def query = {latest_value:'false',start_time:'2021-08-29T09:12:45.001Z',end_time:'2022-03-29T09:12:45.001Z',sort:'time',pagination_token:'ghjjkli'}
Given path dataId,'/data'
And params query
When method get
Then status 400




