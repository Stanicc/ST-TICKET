package stanic.stticket.factory.model;

public class PlayerInfo {

    private String player;
    private Integer amount;
    private Boolean available;

    public PlayerInfo(String player, Integer amount, Boolean available) {
        this.player = player;
        this.amount = amount;
        this.available = available;
    }

    public String getPlayer() {
        return player;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getAvailable() { return available; }

    public void setAvailable(Boolean available) { this.available = available; }

}