package cinema.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
class SeatsController {
    @Autowired
    Seats seats;


    @GetMapping("/seats")
    public Seats getSeats() {
        return seats;
    }
}