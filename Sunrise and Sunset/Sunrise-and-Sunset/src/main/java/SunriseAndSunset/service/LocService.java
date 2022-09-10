package SunriseAndSunset.service;

import SunriseAndSunset.repository.LocRepository;
import org.springframework.stereotype.Service;

@Service
public class LocService {
    private final LocRepository locRepository;

    public LocService(LocRepository locRepository) {
        this.locRepository = locRepository;
    }

    public String getResults(String query){
        return "Searching for books related to " + query;
    }

}
