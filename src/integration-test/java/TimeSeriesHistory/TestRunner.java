package TimeSeriesHistory;


import com.intuit.karate.junit5.Karate;

 class TestRunner {
    @Karate.Test
 
    Karate timeSeriesHistory() {
        return Karate.run("timeSeriesHistory").relativeTo(getClass());
    }
}

