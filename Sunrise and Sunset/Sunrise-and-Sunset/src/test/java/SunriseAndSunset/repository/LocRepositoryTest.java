package SunriseAndSunset.repository;

import SunriseAndSunset.repository.DTO.Dto;
import SunriseAndSunset.repository.DTO.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LocRepositoryTest {

    private LocRepository locRepository;

    @Mock
    WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<Dto> LocResponseMonoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        locRepository = new LocRepository(webClientMock);
    }

    @Test
    void whenGetResults_thenReturnLocResponse() {
        //given
        float latitude = 0;
        float longitude = 0;
        String date = "2022-11-5";
        Dto locResponse = new Dto();
        Results results = new Results();
        locResponse.setResults(results);

        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(Dto.class))
                .thenReturn(LocResponseMonoMock);
        when(LocResponseMonoMock.block())
                .thenReturn(locResponse);

        //when
        Results actualLocResults = locRepository.getResults(latitude, longitude, date);

        //then
        assertEquals(results, actualLocResults);
    }
}