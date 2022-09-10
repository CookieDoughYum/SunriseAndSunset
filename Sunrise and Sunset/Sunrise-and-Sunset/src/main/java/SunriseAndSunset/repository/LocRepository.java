package SunriseAndSunset.repository;

import org.springframework.stereotype.Repository;

@Repository
public class LocRepository {
    private static final baseUrl="https://sunrise-sunset.org/api";

    public String getResults(String query) {
        return "Searching for books related to " + query;
    }

}
