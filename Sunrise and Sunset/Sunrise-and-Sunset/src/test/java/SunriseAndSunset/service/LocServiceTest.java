package SunriseAndSunset.service;

import SunriseAndSunset.repository.DTO.Results;
import SunriseAndSunset.repository.LocRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LocServiceTest {

    private LocService locService;

    @Mock
    private LocRepository locRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        locService = new LocService(locRepository);
    }

    @Test
    void whenGetResults_thenReturnListOfResults() {
        //given
        float latitude = 0;
        float longitude = 0;
        String date = "2022-11-5";
        Results results = new Results();

        when(locService.getResults(date, latitude, longitude))
                .thenReturn(results);

        //when
        Results actualResults = locService.getResults(date, latitude, longitude);

        //then
        assertEquals(results, actualResults);
    }

}