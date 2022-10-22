package SunriseAndSunset.service;

import SunriseAndSunset.repository.DTO.Results;
import SunriseAndSunset.repository.LocRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocService {
    private final LocRepository locRepository;

    public LocService(LocRepository locRepository) {
        this.locRepository = locRepository;
    }

    public Results getResults(float latitude, float longitude, String date){
        return locRepository.getResults(latitude, longitude, date);
    }

}
