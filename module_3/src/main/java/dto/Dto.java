package dto;

import java.time.Instant;

public class Dto {
    private long id;
    private long amount;
    private String description;
    private Instant timestamp;
    private long balanceId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public long getBalanceId() {
        return balanceId;
    }
    public void setBalanceId(long balanceId) {
        this.balanceId = balanceId;
    }
}