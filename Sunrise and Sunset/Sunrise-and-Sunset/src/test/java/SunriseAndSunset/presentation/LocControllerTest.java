package SunriseAndSunset.presentation;

import SunriseAndSunset.service.LocService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import SunriseAndSunset.repository.DTO.Results;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        String date = "2022-11-5";
        Results results = new Results();

        when(locService.getResults(latitude, longitude, date))
                .thenReturn(results);

        //when
        Results actualResults = locController.getResults(latitude, longitude, date);

        //then
        assertEquals(results, actualResults);
    }

    @Test
    void givenBadQuery_whenGetResults_thenThrowsException() {
        //given
        float latitude = 0;
        float longitude = 0;
        String date = "2022-11-5";

        //when
        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> locController.getResults(latitude, longitude, date));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found.\"");
    }
}