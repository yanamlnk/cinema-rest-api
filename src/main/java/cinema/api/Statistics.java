package cinema.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@JsonPropertyOrder({
        "current_income",
        "number_of_available_seats",
        "number_of_purchased_tickets",
})
public class Statistics {
    @JsonProperty("current_income")
    int income = 0;
    @JsonProperty("number_of_available_seats")
    int availableSeats = 81;
    @JsonProperty("number_of_purchased_tickets")
    int purchasedTickets = 0;
    @JsonIgnore
    @Autowired
    Seats seats;

    public Statistics() {
    }

    public void updateStats() {
        this.availableSeats = seats.availableSeats.size();
        this.purchasedTickets = seats.allSeats.size() - seats.availableSeats.size();
    }

    public void updateIncome() {
        income = seats.allSeats.stream().filter(seat -> !seat.isFree()).mapToInt(Seat::getPrice).sum();
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(int purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
