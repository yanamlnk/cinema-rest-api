package cinema.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonPropertyOrder({
        "total_rows",
        "total_columns",
        "available_seats",
})
class Seats {
    @JsonProperty("total_rows")
    int totalRows;
    @JsonProperty("total_columns")
    int totalColumns;
    @JsonProperty("available_seats")
    List<Seat> availableSeats;
    @JsonIgnore
    List<Seat> allSeats;

    public Seats() {
        totalRows = 9;
        totalColumns = 9;
        allSeats = new ArrayList<>();
        int price;
        for (int i = 1; i <= totalRows; i++) {
            if (i <= 4) {
                price = 10;
            } else {
                price = 8;
            }
            for (int j = 1; j <= totalColumns; j++) {
                allSeats.add(new Seat(i, j, price));
            }
        }
        updateAvailableSeats();
    }

    public Seat returnSeat(String token) {
        for (Seat seat : allSeats) {
            if (seat.getToken().equals(token)) {
                seat.setFree(true);
                seat.setToken("");
                updateAvailableSeats();
                return seat;
            }
        }

        return null;
    }

    public void updateAvailableSeats() {
        availableSeats = allSeats.stream().filter(Seat::isFree).collect(Collectors.toList());
    }

    public Seat purchaseSeat(String id) {
        for (Seat seat : allSeats) {
            if (id.equals(seat.getId()) && seat.isFree()) {
                seat.setFree(false);
                seat.generateToken();
                updateAvailableSeats();
                return seat;
            }
        }

        return null;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}