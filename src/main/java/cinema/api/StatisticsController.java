package cinema.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {
    @Autowired
    Statistics statistics;

    @GetMapping("/stats")
    public Statistics getStatistics(@RequestParam(required = false) String password) {
        if (password == null) {
            throw new CinemaException("The password is wrong!");
        }
        if (password.equals("super_secret")) {
            statistics.updateStats();
            statistics.updateIncome();
            return statistics;
        }
        throw new CinemaException("The password is wrong!");
    }

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<ErrorMessage> handleCinemaError(CinemaException e) {

        ErrorMessage error = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
