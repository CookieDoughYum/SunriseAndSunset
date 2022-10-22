package SunriseAndSunset.presentation;

import SunriseAndSunset.repository.DTO.Results;
import SunriseAndSunset.service.LocService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LocController {
    private final LocService locService;

    public LocController(LocService locService) {
        this.locService = locService;
    }

    @GetMapping("/searchLocResults")
    @ApiOperation(value = "Searches for sunrise and sunset",
            notes = "Response may include multiple Result values.",
            response = String.class,
            responseContainer="List")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found"),
            @ApiResponse(code = 404, message = "Result(s) not found")
    })
    public Results getResults(@RequestParam(value="lat") float latitude,
                              @RequestParam(value="lng") float longitude,
                              @RequestParam(value="date") String date) {
        Results results = locService.getResults(latitude, longitude, date);
        if (results==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found.");
        }
        return results;
    }
}


