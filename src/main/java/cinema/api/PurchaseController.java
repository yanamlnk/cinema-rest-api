package cinema.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
public class PurchaseController {
    @Autowired
    Seats seats;

    @PostMapping("/purchase")
    public Ticket purchaseSeat(@RequestBody HashMap<String, Integer> purchaseSeat) {
        if (purchaseSeat.get("row") > 9 || purchaseSeat.get("row") < 1 ||
                purchaseSeat.get("column") > 9 || purchaseSeat.get("column") < 1) {
            throw new CinemaException("The number of a row or a column is out of bounds!");
        }
        String id = purchaseSeat.get("row") + "" + purchaseSeat.get("column");
        Seat seat = seats.purchaseSeat(id);
        if (seat == null) {
            throw new CinemaException("The ticket has been already purchased!");
        }

        return new Ticket(seat.getToken(), seat);
    }

    @PostMapping("/return")
    public Map<String, Seat> returnSeat(@RequestBody HashMap<String, String> token) {
        Seat seat = seats.returnSeat(token.get("token"));
        if (seat == null) {
            throw new CinemaException("Wrong token!");
        }

        Map<String, Seat> returnedTicket = new HashMap<>();
        returnedTicket.put("returned_ticket", seat);
        return returnedTicket;
    }

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<ErrorMessage> handleCinemaError(CinemaException e) {

        ErrorMessage error = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
