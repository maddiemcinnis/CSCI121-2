import java.time.LocalDate;

public class Wedding {
    private LocalDate weddingDate;
    private Couple couple;
    private String location;

    public Wedding(LocalDate weddingDate, Couple couple, String location) {
        this.weddingDate = weddingDate;
        this.couple = couple;
        this.location = location;
    }
    public LocalDate getWeddingDate() {
        return weddingDate;
    }
    public Couple couple() {
        return couple;
    }
    public String location() {
        return location;
    }
}
