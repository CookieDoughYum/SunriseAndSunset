package SunriseAndSunset.presentation;

import SunriseAndSunset.service.LocService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import SunriseAndSunset.repository.DTO.Results;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class LocControllerTest {

    private LocController locController;

    @Mock
    private LocService locService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        locController = new LocController(locService);
    }

    @Test
    void givenGoodQuery_whenGetResults_thenReturnListOfResults() {
        //given
        float latitude = 0;
        float longitude = 0;
        String date = "2022-10-22";
        Results results = new Results();
        List<Results> expectedResults = Collections.singletonList(results);

        when(locService.getResults(latitude, longitude, date))
                .thenReturn(expectedResults);

        //when
        List<Results> actualResults = locController.getResults(latitude, longitude, date);

        //then
        assertEquals(expectedResults, actualResults);
    }
}