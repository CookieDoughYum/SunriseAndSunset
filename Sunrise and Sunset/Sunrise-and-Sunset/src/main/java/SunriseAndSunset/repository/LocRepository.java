package SunriseAndSunset.repository;

import SunriseAndSunset.repository.DTO.Dto;
import SunriseAndSunset.repository.DTO.Results;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class LocRepository {
    private final WebClient webClient;

    private static final String baseUrl = "https://api.sunrise-sunset.org/json";
    public LocRepository() {
        webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }

        public Results getResults(float latitude, float longitude, String date) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("lat", "latitude")
                            .queryParam("lng", "longitude")
                            .queryParam("date", "date")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(Dto.class)
                    .block()
                    .getResults();

        }
    }


