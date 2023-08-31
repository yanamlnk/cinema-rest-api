package cinema.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({
        "row",
        "column",
        "price",
})
class Seat {
    @JsonIgnore
    String id;
    int row;
    int column;
    @JsonIgnore
    boolean free;
    int price;
    @JsonIgnore
    String token = "";


    Seat(int row, int column, int price) {
        this.id = row + "" + column;
        this.row = row;
        this.column = column;
        this.price = price;
        free = true;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void generateToken() {
        token = UUID.randomUUID().toString();
    }
}
