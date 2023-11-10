function fn() {  
    var env = karate.env;   
    var config = {
        env: env,
        //baseUrl:'https://api.dp-dev.dpp.yara.com/timeseries-history/'
    };
    if (env == 'dev') {
        // customize
        config.baseUrl='https://api.dp-dev.dpp.yara.com/timeseries-history/'
            } else if (env == 'test') {
        config.baseUrl='https://api.dp-test.dpp.yara.com/timeseries-history/'
            }
    
    return config
}