package SunriseAndSunset.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

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

        public String getResults(String query) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("lat", "latitude")
                            .queryParam("lng", "longitude")
                            .queryParam("date", "date")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
    }


