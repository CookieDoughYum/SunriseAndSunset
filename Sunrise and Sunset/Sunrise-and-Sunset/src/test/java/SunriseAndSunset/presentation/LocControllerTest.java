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
        String query="";
        Results results = new Results();
        String date="";
        float latitude=0;
        float longitude=0;

        when(locService.getResults(date, latitude, longitude))
                .thenReturn(results);

        //when
        Results actualResults = locController.getResults(query);

        //then
        assertEquals(results, actualResults);
    }

    @Test
    void givenBadQuery_whenGetResults_thenThrowsException() {
        //given
        String query="";

        //when
        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> locController.getResults(query));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found.\"");
    }
}