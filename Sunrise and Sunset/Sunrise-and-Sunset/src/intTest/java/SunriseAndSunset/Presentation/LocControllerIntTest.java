package SunriseAndSunset.Presentation;

import SunriseAndSunset.presentation.LocController;
import SunriseAndSunset.repository.DTO.Results;
import SunriseAndSunset.service.LocService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;

@WebMvcTest(LocController.class)
public class LocControllerIntTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocService locService;

    @Test
    public void givenGoodQuery_whenSearchForResults_thenIsOkAndReturnsResults() throws Exception {
        //given
        float latitude = 0;
        float longitude = 0;
        String date = "2022-11-5";
        Results results = new Results();

        when(locService.getResults(latitude, longitude, date))
                .thenReturn(results);
        //when
        //then
        MvcResult mvcResult = mockMvc.perform(get("/searchLocResults?date=" + date + "&lat=" + latitude + "&lng=" + longitude))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }
    @Test
    public void givenBadQuery_whenSearchForResults_thenIsNotFound() throws Exception {
        //given
       float latitude=0;
       float longitude=0;
       String date="2022-11-5";

        //when
        //then
        mockMvc.perform(get("/searchLocResults?date=" + date + "&lat=" + latitude + "&lng=" + longitude))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
